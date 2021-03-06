package com.doraemon.okhttp3;

import com.doraemon.ext.Nullable;
import com.doraemon.okhttp3.internal.Util;
import com.doraemon.okio.BufferedSink;
import com.doraemon.okio.ByteString;
import com.doraemon.okio.Okio;
import com.doraemon.okio.Source;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class RequestBody {
    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static RequestBody create(@Nullable MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = Util.UTF_8;
            contentType = MediaType.parse(contentType + "; charset=utf-8");
        }
        return create(contentType, content.getBytes(charset));
    }

    public static RequestBody create(@Nullable final MediaType contentType, final ByteString content) {
        return new RequestBody() {
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            public long contentLength() throws IOException {
                return (long) content.size();
            }

            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType contentType, byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    public static RequestBody create(@Nullable final MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount((long) content.length, (long) offset, (long) byteCount);
        return new RequestBody() {
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            public long contentLength() {
                return (long) byteCount;
            }

            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content, offset, byteCount);
            }
        };
    }

    public static RequestBody create(@Nullable final MediaType contentType, final File file) {
        if (file != null) {
            return new RequestBody() {
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                public long contentLength() {
                    return file.length();
                }

                public void writeTo(BufferedSink sink) throws IOException {
                    Source source = null;
                    try {
                        source = Okio.source(file);
                        sink.writeAll(source);
                    } finally {
                        Util.closeQuietly((Closeable) source);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
