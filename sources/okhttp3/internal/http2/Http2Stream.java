package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Header;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = (!Http2Stream.class.desiredAssertionStatus());
    long bytesLeftInWriteWindow;
    final Http2Connection connection;
    ErrorCode errorCode = null;
    private boolean hasResponseHeaders;
    /* access modifiers changed from: private */
    public Header.Listener headersListener;
    /* access modifiers changed from: private */
    public final Deque<Headers> headersQueue = new ArrayDeque();

    /* renamed from: id */
    final int f328id;
    final StreamTimeout readTimeout = new StreamTimeout();
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    final StreamTimeout writeTimeout = new StreamTimeout();

    Http2Stream(int id, Http2Connection connection2, boolean outFinished, boolean inFinished, @Nullable Headers headers) {
        if (connection2 == null) {
            throw new NullPointerException("connection == null");
        }
        this.f328id = id;
        this.connection = connection2;
        this.bytesLeftInWriteWindow = (long) connection2.peerSettings.getInitialWindowSize();
        this.source = new FramingSource((long) connection2.okHttpSettings.getInitialWindowSize());
        this.sink = new FramingSink();
        this.source.finished = inFinished;
        this.sink.finished = outFinished;
        if (headers != null) {
            this.headersQueue.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        } else if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int getId() {
        return this.f328id;
    }

    public synchronized boolean isOpen() {
        boolean z = false;
        synchronized (this) {
            if (this.errorCode == null) {
                if ((!this.source.finished && !this.source.closed) || ((!this.sink.finished && !this.sink.closed) || !this.hasResponseHeaders)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean isLocallyInitiated() {
        boolean streamIsClient;
        if ((this.f328id & 1) == 1) {
            streamIsClient = true;
        } else {
            streamIsClient = false;
        }
        return this.connection.client == streamIsClient;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    /* JADX INFO: finally extract failed */
    public synchronized Headers takeHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!this.headersQueue.isEmpty()) {
        } else {
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void writeHeaders(List<Header> responseHeaders, boolean out) throws IOException {
        boolean flushHeaders;
        if (!$assertionsDisabled && Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (responseHeaders == null) {
            throw new NullPointerException("headers == null");
        } else {
            boolean outFinished = false;
            boolean flushHeaders2 = false;
            synchronized (this) {
                this.hasResponseHeaders = true;
                if (!out) {
                    this.sink.finished = true;
                    flushHeaders2 = true;
                    outFinished = true;
                }
            }
            if (!flushHeaders) {
                synchronized (this.connection) {
                    flushHeaders = this.connection.bytesLeftInWriteWindow == 0;
                }
            }
            this.connection.writeSynReply(this.f328id, outFinished, responseHeaders);
            if (flushHeaders) {
                this.connection.flush();
            }
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode rstStatusCode) throws IOException {
        if (closeInternal(rstStatusCode)) {
            this.connection.writeSynReset(this.f328id, rstStatusCode);
        }
    }

    public void closeLater(ErrorCode errorCode2) {
        if (closeInternal(errorCode2)) {
            this.connection.writeSynResetLater(this.f328id, errorCode2);
        }
    }

    private boolean closeInternal(ErrorCode errorCode2) {
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.errorCode != null) {
                    return false;
                }
                if (this.source.finished && this.sink.finished) {
                    return false;
                }
                this.errorCode = errorCode2;
                notifyAll();
                this.connection.removeStream(this.f328id);
                return true;
            }
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public void receiveHeaders(List<Header> headers) {
        boolean open;
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.hasResponseHeaders = true;
                this.headersQueue.add(Util.toHeaders(headers));
                open = isOpen();
                notifyAll();
            }
            if (!open) {
                this.connection.removeStream(this.f328id);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public void receiveData(BufferedSource in, int length) throws IOException {
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            this.source.receive(in, (long) length);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public void receiveFin() {
        boolean open;
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.source.finished = true;
                open = isOpen();
                notifyAll();
            }
            if (!open) {
                this.connection.removeStream(this.f328id);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public synchronized void receiveRstStream(ErrorCode errorCode2) {
        if (this.errorCode == null) {
            this.errorCode = errorCode2;
            notifyAll();
        }
    }

    public synchronized void setHeadersListener(Header.Listener headersListener2) {
        this.headersListener = headersListener2;
        if (!this.headersQueue.isEmpty() && headersListener2 != null) {
            notifyAll();
        }
    }

    private final class FramingSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = (!Http2Stream.class.desiredAssertionStatus());
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer = new Buffer();
        private final Buffer receiveBuffer = new Buffer();

        FramingSource(long maxByteCount2) {
            this.maxByteCount = maxByteCount2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: okhttp3.Headers} */
        /* JADX WARNING: CFG modification limit reached, blocks count: 163 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(okio.Buffer r15, long r16) throws java.io.IOException {
            /*
                r14 = this;
                r8 = 0
                int r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
                if (r5 >= 0) goto L_0x0034
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>()
                java.lang.String r9 = "byteCount < 0: "
                java.lang.StringBuilder r8 = r8.append(r9)
                r0 = r16
                java.lang.StringBuilder r8 = r8.append(r0)
                java.lang.String r8 = r8.toString()
                r5.<init>(r8)
                throw r5
            L_0x0021:
                boolean r5 = r14.finished     // Catch:{ all -> 0x0059 }
                if (r5 != 0) goto L_0x008d
                if (r2 != 0) goto L_0x008d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                r5.waitForIo()     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream$StreamTimeout r5 = r5.readTimeout     // Catch:{ all -> 0x0062 }
                r5.exitAndThrowIfTimedOut()     // Catch:{ all -> 0x0062 }
                monitor-exit(r8)     // Catch:{ all -> 0x0062 }
            L_0x0034:
                r4 = 0
                r3 = 0
                r6 = -1
                r2 = 0
                okhttp3.internal.http2.Http2Stream r8 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r8)
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream$StreamTimeout r5 = r5.readTimeout     // Catch:{ all -> 0x0062 }
                r5.enter()     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.ErrorCode r5 = r5.errorCode     // Catch:{ all -> 0x0059 }
                if (r5 == 0) goto L_0x004d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.ErrorCode r2 = r5.errorCode     // Catch:{ all -> 0x0059 }
            L_0x004d:
                boolean r5 = r14.closed     // Catch:{ all -> 0x0059 }
                if (r5 == 0) goto L_0x0065
                java.io.IOException r5 = new java.io.IOException     // Catch:{ all -> 0x0059 }
                java.lang.String r9 = "stream closed"
                r5.<init>(r9)     // Catch:{ all -> 0x0059 }
                throw r5     // Catch:{ all -> 0x0059 }
            L_0x0059:
                r5 = move-exception
                okhttp3.internal.http2.Http2Stream r9 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream$StreamTimeout r9 = r9.readTimeout     // Catch:{ all -> 0x0062 }
                r9.exitAndThrowIfTimedOut()     // Catch:{ all -> 0x0062 }
                throw r5     // Catch:{ all -> 0x0062 }
            L_0x0062:
                r5 = move-exception
                monitor-exit(r8)     // Catch:{ all -> 0x0062 }
                throw r5
            L_0x0065:
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                java.util.Deque r5 = r5.headersQueue     // Catch:{ all -> 0x0059 }
                boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0059 }
                if (r5 != 0) goto L_0x009d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Header$Listener r5 = r5.headersListener     // Catch:{ all -> 0x0059 }
                if (r5 == 0) goto L_0x009d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                java.util.Deque r5 = r5.headersQueue     // Catch:{ all -> 0x0059 }
                java.lang.Object r5 = r5.removeFirst()     // Catch:{ all -> 0x0059 }
                r0 = r5
                okhttp3.Headers r0 = (okhttp3.Headers) r0     // Catch:{ all -> 0x0059 }
                r4 = r0
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Header$Listener r3 = r5.headersListener     // Catch:{ all -> 0x0059 }
            L_0x008d:
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream$StreamTimeout r5 = r5.readTimeout     // Catch:{ all -> 0x0062 }
                r5.exitAndThrowIfTimedOut()     // Catch:{ all -> 0x0062 }
                monitor-exit(r8)     // Catch:{ all -> 0x0062 }
                if (r4 == 0) goto L_0x00ef
                if (r3 == 0) goto L_0x00ef
                r3.onHeaders(r4)
                goto L_0x0034
            L_0x009d:
                okio.Buffer r5 = r14.readBuffer     // Catch:{ all -> 0x0059 }
                long r10 = r5.size()     // Catch:{ all -> 0x0059 }
                r12 = 0
                int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r5 <= 0) goto L_0x0021
                okio.Buffer r5 = r14.readBuffer     // Catch:{ all -> 0x0059 }
                okio.Buffer r9 = r14.readBuffer     // Catch:{ all -> 0x0059 }
                long r10 = r9.size()     // Catch:{ all -> 0x0059 }
                r0 = r16
                long r10 = java.lang.Math.min(r0, r10)     // Catch:{ all -> 0x0059 }
                long r6 = r5.read(r15, r10)     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                long r10 = r5.unacknowledgedBytesRead     // Catch:{ all -> 0x0059 }
                long r10 = r10 + r6
                r5.unacknowledgedBytesRead = r10     // Catch:{ all -> 0x0059 }
                if (r2 != 0) goto L_0x008d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                long r10 = r5.unacknowledgedBytesRead     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Connection r5 = r5.connection     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Settings r5 = r5.okHttpSettings     // Catch:{ all -> 0x0059 }
                int r5 = r5.getInitialWindowSize()     // Catch:{ all -> 0x0059 }
                int r5 = r5 / 2
                long r12 = (long) r5     // Catch:{ all -> 0x0059 }
                int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r5 < 0) goto L_0x008d
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Connection r5 = r5.connection     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r9 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                int r9 = r9.f328id     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r10 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                long r10 = r10.unacknowledgedBytesRead     // Catch:{ all -> 0x0059 }
                r5.writeWindowUpdateLater(r9, r10)     // Catch:{ all -> 0x0059 }
                okhttp3.internal.http2.Http2Stream r5 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0059 }
                r10 = 0
                r5.unacknowledgedBytesRead = r10     // Catch:{ all -> 0x0059 }
                goto L_0x008d
            L_0x00ef:
                r8 = -1
                int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r5 == 0) goto L_0x00f9
                r14.updateConnectionFlowControl(r6)
            L_0x00f8:
                return r6
            L_0x00f9:
                if (r2 == 0) goto L_0x0101
                okhttp3.internal.http2.StreamResetException r5 = new okhttp3.internal.http2.StreamResetException
                r5.<init>(r2)
                throw r5
            L_0x0101:
                r6 = -1
                goto L_0x00f8
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        private void updateConnectionFlowControl(long read) {
            if ($assertionsDisabled || !Thread.holdsLock(Http2Stream.this)) {
                Http2Stream.this.connection.updateConnectionFlowControl(read);
                return;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void receive(BufferedSource in, long byteCount) throws IOException {
            boolean finished2;
            boolean flowControlError;
            if ($assertionsDisabled || !Thread.holdsLock(Http2Stream.this)) {
                while (byteCount > 0) {
                    synchronized (Http2Stream.this) {
                        finished2 = this.finished;
                        flowControlError = this.readBuffer.size() + byteCount > this.maxByteCount;
                    }
                    if (flowControlError) {
                        in.skip(byteCount);
                        Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (finished2) {
                        in.skip(byteCount);
                        return;
                    } else {
                        long read = in.read(this.receiveBuffer, byteCount);
                        if (read == -1) {
                            throw new EOFException();
                        }
                        byteCount -= read;
                        synchronized (Http2Stream.this) {
                            boolean wasEmpty = this.readBuffer.size() == 0;
                            this.readBuffer.writeAll(this.receiveBuffer);
                            if (wasEmpty) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
            if (r0 <= 0) goto L_0x0051;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
            updateConnectionFlowControl(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
            r8.this$0.cancelStreamIfNecessary();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
            if (r3 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
            r6 = r4.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
            if (r6.hasNext() == false) goto L_0x006f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
            r3.onHeaders(r6.next());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r8 = this;
                r4 = 0
                r3 = 0
                okhttp3.internal.http2.Http2Stream r7 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r7)
                r6 = 1
                r8.closed = r6     // Catch:{ all -> 0x006c }
                okio.Buffer r6 = r8.readBuffer     // Catch:{ all -> 0x006c }
                long r0 = r6.size()     // Catch:{ all -> 0x006c }
                okio.Buffer r6 = r8.readBuffer     // Catch:{ all -> 0x006c }
                r6.clear()     // Catch:{ all -> 0x006c }
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x006c }
                java.util.Deque r6 = r6.headersQueue     // Catch:{ all -> 0x006c }
                boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x006c }
                if (r6 != 0) goto L_0x0042
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x006c }
                okhttp3.internal.http2.Header$Listener r6 = r6.headersListener     // Catch:{ all -> 0x006c }
                if (r6 == 0) goto L_0x0042
                java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x006c }
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x006c }
                java.util.Deque r6 = r6.headersQueue     // Catch:{ all -> 0x006c }
                r5.<init>(r6)     // Catch:{ all -> 0x006c }
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0070 }
                java.util.Deque r6 = r6.headersQueue     // Catch:{ all -> 0x0070 }
                r6.clear()     // Catch:{ all -> 0x0070 }
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x0070 }
                okhttp3.internal.http2.Header$Listener r3 = r6.headersListener     // Catch:{ all -> 0x0070 }
                r4 = r5
            L_0x0042:
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this     // Catch:{ all -> 0x006c }
                r6.notifyAll()     // Catch:{ all -> 0x006c }
                monitor-exit(r7)     // Catch:{ all -> 0x006c }
                r6 = 0
                int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                if (r6 <= 0) goto L_0x0051
                r8.updateConnectionFlowControl(r0)
            L_0x0051:
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this
                r6.cancelStreamIfNecessary()
                if (r3 == 0) goto L_0x006f
                java.util.Iterator r6 = r4.iterator()
            L_0x005c:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x006f
                java.lang.Object r2 = r6.next()
                okhttp3.Headers r2 = (okhttp3.Headers) r2
                r3.onHeaders(r2)
                goto L_0x005c
            L_0x006c:
                r6 = move-exception
            L_0x006d:
                monitor-exit(r7)     // Catch:{ all -> 0x006c }
                throw r6
            L_0x006f:
                return
            L_0x0070:
                r6 = move-exception
                r4 = r5
                goto L_0x006d
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.close():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelStreamIfNecessary() throws IOException {
        boolean cancel;
        boolean open;
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                cancel = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
                open = isOpen();
            }
            if (cancel) {
                close(ErrorCode.CANCEL);
            } else if (!open) {
                this.connection.removeStream(this.f328id);
            }
        } else {
            throw new AssertionError();
        }
    }

    final class FramingSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = (!Http2Stream.class.desiredAssertionStatus());
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final Buffer sendBuffer = new Buffer();

        FramingSink() {
        }

        public void write(Buffer source, long byteCount) throws IOException {
            if ($assertionsDisabled || !Thread.holdsLock(Http2Stream.this)) {
                this.sendBuffer.write(source, byteCount);
                while (this.sendBuffer.size() >= 16384) {
                    emitFrame(false);
                }
                return;
            }
            throw new AssertionError();
        }

        /* JADX INFO: finally extract failed */
        private void emitFrame(boolean outFinished) throws IOException {
            long toWrite;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (Http2Stream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                    try {
                        Http2Stream.this.waitForIo();
                    } catch (Throwable th) {
                        Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                toWrite = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                Http2Stream.this.bytesLeftInWriteWindow -= toWrite;
            }
            Http2Stream.this.writeTimeout.enter();
            try {
                Http2Stream.this.connection.writeData(Http2Stream.this.f328id, outFinished && toWrite == this.sendBuffer.size(), this.sendBuffer, toWrite);
            } finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        public void flush() throws IOException {
            if ($assertionsDisabled || !Thread.holdsLock(Http2Stream.this)) {
                synchronized (Http2Stream.this) {
                    Http2Stream.this.checkOutNotClosed();
                }
                while (this.sendBuffer.size() > 0) {
                    emitFrame(false);
                    Http2Stream.this.connection.flush();
                }
                return;
            }
            throw new AssertionError();
        }

        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
            if (r6.this$0.sink.finished != false) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
            if (r6.sendBuffer.size() <= 0) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
            if (r6.sendBuffer.size() <= 0) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
            emitFrame(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
            r6.this$0.connection.writeData(r6.this$0.f328id, true, (okio.Buffer) null, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
            r1 = r6.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            r6.closed = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0055, code lost:
            r6.this$0.connection.flush();
            r6.this$0.cancelStreamIfNecessary();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r6 = this;
                r4 = 0
                r2 = 1
                boolean r0 = $assertionsDisabled
                if (r0 != 0) goto L_0x0015
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                boolean r0 = java.lang.Thread.holdsLock(r0)
                if (r0 == 0) goto L_0x0015
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            L_0x0015:
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r1)
                boolean r0 = r6.closed     // Catch:{ all -> 0x003f }
                if (r0 == 0) goto L_0x001e
                monitor-exit(r1)     // Catch:{ all -> 0x003f }
            L_0x001d:
                return
            L_0x001e:
                monitor-exit(r1)     // Catch:{ all -> 0x003f }
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Stream$FramingSink r0 = r0.sink
                boolean r0 = r0.finished
                if (r0 != 0) goto L_0x004e
                okio.Buffer r0 = r6.sendBuffer
                long r0 = r0.size()
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x0042
            L_0x0031:
                okio.Buffer r0 = r6.sendBuffer
                long r0 = r0.size()
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x004e
                r6.emitFrame(r2)
                goto L_0x0031
            L_0x003f:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x003f }
                throw r0
            L_0x0042:
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r0 = r0.connection
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this
                int r1 = r1.f328id
                r3 = 0
                r0.writeData(r1, r2, r3, r4)
            L_0x004e:
                okhttp3.internal.http2.Http2Stream r1 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r1)
                r0 = 1
                r6.closed = r0     // Catch:{ all -> 0x0062 }
                monitor-exit(r1)     // Catch:{ all -> 0x0062 }
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                okhttp3.internal.http2.Http2Connection r0 = r0.connection
                r0.flush()
                okhttp3.internal.http2.Http2Stream r0 = okhttp3.internal.http2.Http2Stream.this
                r0.cancelStreamIfNecessary()
                goto L_0x001d
            L_0x0062:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0062 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSink.close():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void addBytesToWriteWindow(long delta) {
        this.bytesLeftInWriteWindow += delta;
        if (delta > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        } else if (this.sink.finished) {
            throw new IOException("stream finished");
        } else if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    /* access modifiers changed from: package-private */
    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        /* access modifiers changed from: protected */
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        /* access modifiers changed from: protected */
        public IOException newTimeoutException(IOException cause) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (cause != null) {
                socketTimeoutException.initCause(cause);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException((IOException) null);
            }
        }
    }
}
