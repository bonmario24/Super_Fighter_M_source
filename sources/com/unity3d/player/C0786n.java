package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.n */
public final class C0786n extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static boolean f275a = false;

    /* renamed from: b */
    private final Context f276b;

    /* renamed from: c */
    private final SurfaceView f277c;

    /* renamed from: d */
    private final SurfaceHolder f278d;

    /* renamed from: e */
    private final String f279e;

    /* renamed from: f */
    private final int f280f;

    /* renamed from: g */
    private final int f281g;

    /* renamed from: h */
    private final boolean f282h;

    /* renamed from: i */
    private final long f283i;

    /* renamed from: j */
    private final long f284j;

    /* renamed from: k */
    private final FrameLayout f285k;

    /* renamed from: l */
    private final Display f286l;

    /* renamed from: m */
    private int f287m;

    /* renamed from: n */
    private int f288n;

    /* renamed from: o */
    private int f289o;

    /* renamed from: p */
    private int f290p;

    /* renamed from: q */
    private MediaPlayer f291q;

    /* renamed from: r */
    private MediaController f292r;

    /* renamed from: s */
    private boolean f293s = false;

    /* renamed from: t */
    private boolean f294t = false;

    /* renamed from: u */
    private int f295u = 0;

    /* renamed from: v */
    private boolean f296v = false;

    /* renamed from: w */
    private boolean f297w = false;

    /* renamed from: x */
    private C0787a f298x;

    /* renamed from: y */
    private C0788b f299y;

    /* renamed from: z */
    private volatile int f300z = 0;

    /* renamed from: com.unity3d.player.n$a */
    public interface C0787a {
        /* renamed from: a */
        void mo12551a(int i);
    }

    /* renamed from: com.unity3d.player.n$b */
    public class C0788b implements Runnable {

        /* renamed from: b */
        private C0786n f302b;

        /* renamed from: c */
        private boolean f303c = false;

        public C0788b(C0786n nVar) {
            this.f302b = nVar;
        }

        /* renamed from: a */
        public final void mo12552a() {
            this.f303c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!this.f303c) {
                if (C0786n.f275a) {
                    C0786n.m160b("Stopping the video player due to timeout.");
                }
                this.f302b.CancelOnPrepare();
            }
        }
    }

    protected C0786n(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0787a aVar) {
        super(context);
        this.f298x = aVar;
        this.f276b = context;
        this.f285k = this;
        this.f277c = new SurfaceView(context);
        this.f278d = this.f277c.getHolder();
        this.f278d.addCallback(this);
        this.f278d.setType(3);
        this.f285k.setBackgroundColor(i);
        this.f285k.addView(this.f277c);
        this.f286l = ((WindowManager) this.f276b.getSystemService("window")).getDefaultDisplay();
        this.f279e = str;
        this.f280f = i2;
        this.f281g = i3;
        this.f282h = z;
        this.f283i = j;
        this.f284j = j2;
        if (f275a) {
            m160b("fileName: " + this.f279e);
        }
        if (f275a) {
            m160b("backgroundColor: " + i);
        }
        if (f275a) {
            m160b("controlMode: " + this.f280f);
        }
        if (f275a) {
            m160b("scalingMode: " + this.f281g);
        }
        if (f275a) {
            m160b("isURL: " + this.f282h);
        }
        if (f275a) {
            m160b("videoOffset: " + this.f283i);
        }
        if (f275a) {
            m160b("videoLength: " + this.f284j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m158a(int i) {
        this.f300z = i;
        if (this.f298x != null) {
            this.f298x.mo12551a(this.f300z);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m160b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* renamed from: c */
    private void m162c() {
        if (this.f291q != null) {
            this.f291q.setDisplay(this.f278d);
            if (!this.f296v) {
                if (f275a) {
                    m160b("Resuming playback");
                }
                this.f291q.start();
                return;
            }
            return;
        }
        m158a(0);
        doCleanUp();
        try {
            this.f291q = new MediaPlayer();
            if (this.f282h) {
                this.f291q.setDataSource(this.f276b, Uri.parse(this.f279e));
            } else if (this.f284j != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f279e);
                this.f291q.setDataSource(fileInputStream.getFD(), this.f283i, this.f284j);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f279e);
                    this.f291q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f279e);
                    this.f291q.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.f291q.setDisplay(this.f278d);
            this.f291q.setScreenOnWhilePlaying(true);
            this.f291q.setOnBufferingUpdateListener(this);
            this.f291q.setOnCompletionListener(this);
            this.f291q.setOnPreparedListener(this);
            this.f291q.setOnVideoSizeChangedListener(this);
            this.f291q.setAudioStreamType(3);
            this.f291q.prepareAsync();
            this.f299y = new C0788b(this);
            new Thread(this.f299y).start();
        } catch (Exception e2) {
            if (f275a) {
                m160b("error: " + e2.getMessage() + e2);
            }
            m158a(2);
        }
    }

    /* renamed from: d */
    private void m163d() {
        if (!isPlaying()) {
            m158a(1);
            if (f275a) {
                m160b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f296v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        m158a(2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo12528a() {
        return this.f296v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f275a) {
            m160b("destroyPlayer");
        }
        if (!this.f296v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        if (this.f299y != null) {
            this.f299y.mo12552a();
            this.f299y = null;
        }
        if (this.f291q != null) {
            this.f291q.release();
            this.f291q = null;
        }
        this.f289o = 0;
        this.f290p = 0;
        this.f294t = false;
        this.f293s = false;
    }

    public final int getBufferPercentage() {
        if (this.f282h) {
            return this.f295u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        if (this.f291q == null) {
            return 0;
        }
        return this.f291q.getCurrentPosition();
    }

    public final int getDuration() {
        if (this.f291q == null) {
            return 0;
        }
        return this.f291q.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f294t && this.f293s;
        return this.f291q == null ? !z : this.f291q.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f275a) {
            m160b("onBufferingUpdate percent:" + i);
        }
        this.f295u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f275a) {
            m160b("onCompletion called");
        }
        destroyPlayer();
        m158a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f280f != 2 || i == 0 || keyEvent.isSystem())) {
            return this.f292r != null ? this.f292r.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        }
        destroyPlayer();
        m158a(3);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f275a) {
            m160b("onPrepared called");
        }
        if (this.f299y != null) {
            this.f299y.mo12552a();
            this.f299y = null;
        }
        if (this.f280f == 0 || this.f280f == 1) {
            this.f292r = new MediaController(this.f276b);
            this.f292r.setMediaPlayer(this);
            this.f292r.setAnchorView(this);
            this.f292r.setEnabled(true);
            this.f292r.show();
        }
        this.f294t = true;
        if (this.f294t && this.f293s) {
            m163d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f280f != 2 || action != 0) {
            return this.f292r != null ? this.f292r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        m158a(3);
        return true;
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f275a) {
            m160b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f293s = true;
            this.f289o = i;
            this.f290p = i2;
            if (this.f294t && this.f293s) {
                m163d();
            }
        } else if (f275a) {
            m160b("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.f291q != null) {
            if (this.f297w) {
                this.f291q.pause();
            }
            this.f296v = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f291q != null) {
            this.f291q.seekTo(i);
        }
    }

    public final void start() {
        if (f275a) {
            m160b("Start");
        }
        if (this.f291q != null) {
            if (this.f297w) {
                this.f291q.start();
            }
            this.f296v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f275a) {
            m160b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f287m != i2 || this.f288n != i3) {
            this.f287m = i2;
            this.f288n = i3;
            if (this.f297w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f275a) {
            m160b("surfaceCreated called");
        }
        this.f297w = true;
        m162c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f275a) {
            m160b("surfaceDestroyed called");
        }
        this.f297w = false;
    }

    /* access modifiers changed from: protected */
    public final void updateVideoLayout() {
        if (f275a) {
            m160b("updateVideoLayout");
        }
        if (this.f291q != null) {
            if (this.f287m == 0 || this.f288n == 0) {
                WindowManager windowManager = (WindowManager) this.f276b.getSystemService("window");
                this.f287m = windowManager.getDefaultDisplay().getWidth();
                this.f288n = windowManager.getDefaultDisplay().getHeight();
            }
            int i = this.f287m;
            int i2 = this.f288n;
            if (this.f293s) {
                float f = ((float) this.f289o) / ((float) this.f290p);
                float f2 = ((float) this.f287m) / ((float) this.f288n);
                if (this.f281g == 1) {
                    if (f2 <= f) {
                        i2 = (int) (((float) this.f287m) / f);
                    } else {
                        i = (int) (((float) this.f288n) * f);
                    }
                } else if (this.f281g == 2) {
                    if (f2 >= f) {
                        i2 = (int) (((float) this.f287m) / f);
                    } else {
                        i = (int) (((float) this.f288n) * f);
                    }
                } else if (this.f281g == 0) {
                    i = this.f289o;
                    i2 = this.f290p;
                }
            } else if (f275a) {
                m160b("updateVideoLayout: Video size is not known yet");
            }
            if (this.f287m != i || this.f288n != i2) {
                if (f275a) {
                    m160b("frameWidth = " + i + "; frameHeight = " + i2);
                }
                this.f285k.updateViewLayout(this.f277c, new FrameLayout.LayoutParams(i, i2, 17));
            }
        }
    }
}
