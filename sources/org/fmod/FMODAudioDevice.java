package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {

    /* renamed from: h */
    private static int f911h = 0;

    /* renamed from: i */
    private static int f912i = 1;

    /* renamed from: j */
    private static int f913j = 2;

    /* renamed from: k */
    private static int f914k = 3;

    /* renamed from: a */
    private volatile Thread f915a = null;

    /* renamed from: b */
    private volatile boolean f916b = false;

    /* renamed from: c */
    private AudioTrack f917c = null;

    /* renamed from: d */
    private boolean f918d = false;

    /* renamed from: e */
    private ByteBuffer f919e = null;

    /* renamed from: f */
    private byte[] f920f = null;

    /* renamed from: g */
    private volatile C1759a f921g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.f917c != null) {
            if (this.f917c.getState() == 1) {
                this.f917c.stop();
            }
            this.f917c.release();
            this.f917c = null;
        }
        this.f919e = null;
        this.f920f = null;
        this.f918d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* access modifiers changed from: package-private */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f915a != null && this.f915a.isAlive();
    }

    public void run() {
        int i;
        int i2 = 3;
        while (this.f916b) {
            if (this.f918d || i2 <= 0) {
                i = i2;
            } else {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f911h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f912i);
                int fmodGetInfo3 = fmodGetInfo(f913j);
                if (fmodGetInfo2 * fmodGetInfo3 * 4 > round) {
                    round = fmodGetInfo3 * fmodGetInfo2 * 4;
                }
                this.f917c = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                this.f918d = this.f917c.getState() == 1;
                if (this.f918d) {
                    this.f919e = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f920f = new byte[this.f919e.capacity()];
                    this.f917c.play();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f917c.getState() + ")");
                    releaseAudioTrack();
                    i = i2 - 1;
                }
            }
            if (!this.f918d) {
                i2 = i;
            } else if (fmodGetInfo(f914k) == 1) {
                fmodProcess(this.f919e);
                this.f919e.get(this.f920f, 0, this.f919e.capacity());
                this.f917c.write(this.f920f, 0, this.f919e.capacity());
                this.f919e.position(0);
                i2 = i;
            } else {
                releaseAudioTrack();
                i2 = i;
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f915a != null) {
            stop();
        }
        this.f915a = new Thread(this, "FMODAudioDevice");
        this.f915a.setPriority(10);
        this.f916b = true;
        this.f915a.start();
        if (this.f921g != null) {
            this.f921g.mo20018b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f921g == null) {
            this.f921g = new C1759a(this, i, i2);
            this.f921g.mo20018b();
        }
        return this.f921g.mo20017a();
    }

    public synchronized void stop() {
        while (this.f915a != null) {
            this.f916b = false;
            try {
                this.f915a.join();
                this.f915a = null;
            } catch (InterruptedException e) {
            }
        }
        if (this.f921g != null) {
            this.f921g.mo20019c();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.f921g != null) {
            this.f921g.mo20019c();
            this.f921g = null;
        }
    }
}
