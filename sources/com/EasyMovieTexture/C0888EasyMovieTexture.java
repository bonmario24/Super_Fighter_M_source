package com.EasyMovieTexture;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLES20;
import android.util.Log;
import android.view.Surface;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.teamtop3.Defenders.HttpResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.EasyMovieTexture.EasyMovieTexture */
public class C0888EasyMovieTexture implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, SurfaceTexture.OnFrameAvailableListener {
    private static final int GL_TEXTURE_EXTERNAL_OES = 36197;
    public static ArrayList<C0888EasyMovieTexture> m_objCtrl = new ArrayList<>();
    private MediaPlayer m_MediaPlayer = null;
    private Surface m_Surface = null;
    private SurfaceTexture m_SurfaceTexture = null;
    private Activity m_UnityActivity = null;
    private boolean m_bRockchip = true;
    private boolean m_bSplitOBB = false;
    public boolean m_bUpdate = false;
    private int m_iCurrentSeekPercent = 0;
    private int m_iCurrentSeekPosition = 0;
    MEDIAPLAYER_STATE m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
    private int m_iErrorCode;
    private int m_iErrorCodeExtra;
    public int m_iNativeMgrID;
    private int m_iSurfaceTextureID = -1;
    private int m_iUnityTextureID = -1;
    private String m_strFileName;
    private String m_strOBBName;

    /* renamed from: com.EasyMovieTexture.EasyMovieTexture$MEDIAPLAYER_STATE */
    public enum MEDIAPLAYER_STATE {
        NOT_READY(0),
        READY(1),
        END(2),
        PLAYING(3),
        PAUSED(4),
        STOPPED(5),
        ERROR(6);
        
        private int iValue;

        private MEDIAPLAYER_STATE(int i) {
            this.iValue = i;
        }

        public int GetValue() {
            return this.iValue;
        }
    }

    static {
        System.loadLibrary("BlueDoveMediaRender");
    }

    public static C0888EasyMovieTexture GetObject(int i) {
        for (int i2 = 0; i2 < m_objCtrl.size(); i2++) {
            if (m_objCtrl.get(i2).m_iNativeMgrID == i) {
                return m_objCtrl.get(i2);
            }
        }
        return null;
    }

    public void Destroy() {
        if (this.m_iSurfaceTextureID != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.m_iSurfaceTextureID}, 0);
            this.m_iSurfaceTextureID = -1;
        }
        SetManagerID(this.m_iNativeMgrID);
        QuitApplication();
        m_objCtrl.remove(this);
    }

    public int GetCurrentSeekPercent() {
        return this.m_iCurrentSeekPercent;
    }

    public int GetDuration() {
        if (this.m_MediaPlayer != null) {
            return this.m_MediaPlayer.getDuration();
        }
        return -1;
    }

    public int GetError() {
        return this.m_iErrorCode;
    }

    public int GetErrorExtra() {
        return this.m_iErrorCodeExtra;
    }

    public native int GetManagerID();

    public int GetSeekPosition() {
        if (this.m_MediaPlayer != null && (this.m_iCurrentState == MEDIAPLAYER_STATE.READY || this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING || this.m_iCurrentState == MEDIAPLAYER_STATE.PAUSED)) {
            try {
                this.m_iCurrentSeekPosition = this.m_MediaPlayer.getCurrentPosition();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
        }
        return this.m_iCurrentSeekPosition;
    }

    public int GetStatus() {
        return this.m_iCurrentState.GetValue();
    }

    public int GetVideoHeight() {
        if (this.m_MediaPlayer != null) {
            return this.m_MediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public int GetVideoWidth() {
        if (this.m_MediaPlayer != null) {
            return this.m_MediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public native int InitApplication();

    public native int InitExtTexture();

    public void InitJniManager() {
        SetManagerID(this.m_iNativeMgrID);
        InitApplication();
    }

    public native int InitNDK(Object obj);

    public int InitNative(C0888EasyMovieTexture easyMovieTexture) {
        this.m_iNativeMgrID = InitNDK(easyMovieTexture);
        m_objCtrl.add(this);
        return this.m_iNativeMgrID;
    }

    public boolean IsUpdateFrame() {
        return this.m_bUpdate;
    }

    public boolean Load() throws SecurityException, IllegalStateException, IOException {
        UnLoad();
        this.m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
        this.m_MediaPlayer = new MediaPlayer();
        this.m_MediaPlayer.setAudioStreamType(3);
        this.m_bUpdate = false;
        if (this.m_strFileName.contains("file://")) {
            File file = new File(this.m_strFileName.replace("file://", ""));
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                this.m_MediaPlayer.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            }
        } else if (this.m_strFileName.contains("://")) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("rtsp_transport", "tcp");
                hashMap.put("max_analyze_duration", "500");
                if (this.m_strFileName.contains("@")) {
                    int indexOf = this.m_strFileName.indexOf("://");
                    String substring = this.m_strFileName.substring(indexOf + 3);
                    int indexOf2 = substring.indexOf(":");
                    String substring2 = substring.substring(0, indexOf2);
                    String substring3 = substring.substring(indexOf2 + 1);
                    int indexOf3 = substring3.indexOf("@");
                    String substring4 = substring3.substring(0, indexOf3);
                    String substring5 = substring3.substring(indexOf3 + 1);
                    hashMap.put("SERVER_KEY_TO_PARSE_USER_NAME", substring2);
                    hashMap.put("SERVER_KEY_TO_PARSE_PASSWORD", substring4);
                    Log.e("Unity", String.valueOf(substring2) + " " + substring4 + " " + substring5);
                    this.m_MediaPlayer.setDataSource(this.m_UnityActivity, Uri.parse(String.valueOf(this.m_strFileName.substring(0, indexOf + 3)) + substring5), hashMap);
                } else {
                    this.m_MediaPlayer.setDataSource(this.m_UnityActivity, Uri.parse(this.m_strFileName), hashMap);
                }
            } catch (IOException e) {
                Log.e("Unity", "Error m_MediaPlayer.setDataSource() : " + this.m_strFileName);
                e.printStackTrace();
                this.m_iCurrentState = MEDIAPLAYER_STATE.ERROR;
                return false;
            }
        } else if (this.m_bSplitOBB) {
            try {
                ZipResourceFile zipResourceFile = new ZipResourceFile(this.m_strOBBName);
                Log.e("unity", String.valueOf(this.m_strOBBName) + " " + this.m_strFileName);
                AssetFileDescriptor assetFileDescriptor = zipResourceFile.getAssetFileDescriptor("assets/" + this.m_strFileName);
                ZipResourceFile.ZipEntryRO[] allEntries = zipResourceFile.getAllEntries();
                for (ZipResourceFile.ZipEntryRO zipEntryRO : allEntries) {
                    Log.e("unity", zipEntryRO.mFileName);
                }
                Log.e("unity", assetFileDescriptor + " ");
                this.m_MediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            } catch (IOException e2) {
                this.m_iCurrentState = MEDIAPLAYER_STATE.ERROR;
                e2.printStackTrace();
                return false;
            }
        } else {
            try {
                AssetFileDescriptor openFd = this.m_UnityActivity.getAssets().openFd(this.m_strFileName);
                this.m_MediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                openFd.close();
            } catch (IOException e3) {
                Log.e("Unity", "Error m_MediaPlayer.setDataSource() : " + this.m_strFileName);
                e3.printStackTrace();
                this.m_iCurrentState = MEDIAPLAYER_STATE.ERROR;
                return false;
            }
        }
        if (this.m_iSurfaceTextureID == -1) {
            this.m_iSurfaceTextureID = InitExtTexture();
        }
        this.m_SurfaceTexture = new SurfaceTexture(this.m_iSurfaceTextureID);
        this.m_SurfaceTexture.setOnFrameAvailableListener(this);
        this.m_Surface = new Surface(this.m_SurfaceTexture);
        this.m_MediaPlayer.setSurface(this.m_Surface);
        this.m_MediaPlayer.setOnPreparedListener(this);
        this.m_MediaPlayer.setOnCompletionListener(this);
        this.m_MediaPlayer.setOnErrorListener(this);
        this.m_MediaPlayer.prepareAsync();
        return true;
    }

    public void NDK_SetFileName(String str) {
        this.m_strFileName = str;
    }

    public void Pause() {
        if (this.m_MediaPlayer != null && this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING) {
            this.m_MediaPlayer.pause();
            this.m_iCurrentState = MEDIAPLAYER_STATE.PAUSED;
        }
    }

    public void Play(int i) {
        if (this.m_MediaPlayer == null) {
            return;
        }
        if (this.m_iCurrentState == MEDIAPLAYER_STATE.READY || this.m_iCurrentState == MEDIAPLAYER_STATE.PAUSED || this.m_iCurrentState == MEDIAPLAYER_STATE.END) {
            this.m_MediaPlayer.start();
            this.m_iCurrentState = MEDIAPLAYER_STATE.PLAYING;
        }
    }

    public native void QuitApplication();

    public void RePlay() {
        if (this.m_MediaPlayer != null && this.m_iCurrentState == MEDIAPLAYER_STATE.PAUSED) {
            this.m_MediaPlayer.start();
            this.m_iCurrentState = MEDIAPLAYER_STATE.PLAYING;
        }
    }

    public native void RenderScene(float[] fArr, int i, int i2);

    public void Reset() {
        if (this.m_MediaPlayer != null && this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING) {
            this.m_MediaPlayer.reset();
        }
        this.m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
    }

    public native void SetAssetManager(AssetManager assetManager);

    public void SetLooping(boolean z) {
        if (this.m_MediaPlayer != null) {
            this.m_MediaPlayer.setLooping(z);
        }
    }

    public native void SetManagerID(int i);

    public void SetNotReady() {
        this.m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
    }

    public void SetRockchip(boolean z) {
        this.m_bRockchip = z;
    }

    public void SetSeekPosition(int i) {
        if (this.m_MediaPlayer == null) {
            return;
        }
        if (this.m_iCurrentState == MEDIAPLAYER_STATE.READY || this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING || this.m_iCurrentState == MEDIAPLAYER_STATE.PAUSED) {
            this.m_MediaPlayer.seekTo(i);
        }
    }

    @TargetApi(23)
    public void SetSpeed(float f) {
        this.m_MediaPlayer.setPlaybackParams(this.m_MediaPlayer.getPlaybackParams().setSpeed(f));
    }

    public void SetSplitOBB(boolean z, String str) {
        this.m_bSplitOBB = z;
        this.m_strOBBName = str;
    }

    public void SetUnityActivity(Activity activity) {
        SetManagerID(this.m_iNativeMgrID);
        this.m_UnityActivity = activity;
        SetAssetManager(this.m_UnityActivity.getAssets());
    }

    public void SetUnityTexture(int i) {
        this.m_iUnityTextureID = i;
        SetManagerID(this.m_iNativeMgrID);
        SetUnityTextureID(this.m_iUnityTextureID);
    }

    public native void SetUnityTextureID(int i);

    public void SetUnityTextureID(Object obj) {
    }

    public void SetVolume(float f) {
        if (this.m_MediaPlayer != null) {
            this.m_MediaPlayer.setVolume(f, f);
        }
    }

    public void SetWindowSize() {
        SetManagerID(this.m_iNativeMgrID);
        SetWindowSize(GetVideoWidth(), GetVideoHeight(), this.m_iUnityTextureID, this.m_bRockchip);
    }

    public native void SetWindowSize(int i, int i2, int i3, boolean z);

    public void Stop() {
        if (this.m_MediaPlayer != null && this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING) {
            this.m_MediaPlayer.stop();
        }
        this.m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
    }

    public void UnLoad() {
        if (this.m_MediaPlayer != null) {
            if (this.m_iCurrentState != MEDIAPLAYER_STATE.NOT_READY) {
                try {
                    this.m_MediaPlayer.stop();
                    this.m_MediaPlayer.release();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                }
                this.m_MediaPlayer = null;
            } else {
                try {
                    this.m_MediaPlayer.release();
                } catch (SecurityException e3) {
                    e3.printStackTrace();
                } catch (IllegalStateException e4) {
                    e4.printStackTrace();
                }
                this.m_MediaPlayer = null;
            }
            if (this.m_Surface != null) {
                this.m_Surface.release();
                this.m_Surface = null;
            }
            if (this.m_SurfaceTexture != null) {
                this.m_SurfaceTexture.release();
                this.m_SurfaceTexture = null;
            }
            if (this.m_iSurfaceTextureID != -1) {
                GLES20.glDeleteTextures(1, new int[]{this.m_iSurfaceTextureID}, 0);
                this.m_iSurfaceTextureID = -1;
            }
        }
        this.m_iCurrentState = MEDIAPLAYER_STATE.NOT_READY;
    }

    public void UpdateVideoTexture() {
        if (!this.m_bUpdate || this.m_MediaPlayer == null) {
            return;
        }
        if (this.m_iCurrentState == MEDIAPLAYER_STATE.PLAYING || this.m_iCurrentState == MEDIAPLAYER_STATE.PAUSED) {
            SetManagerID(this.m_iNativeMgrID);
            boolean[] zArr = new boolean[1];
            GLES20.glGetBooleanv(2929, zArr, 0);
            GLES20.glDisable(2929);
            this.m_SurfaceTexture.updateTexImage();
            float[] fArr = new float[16];
            this.m_SurfaceTexture.getTransformMatrix(fArr);
            RenderScene(fArr, this.m_iSurfaceTextureID, this.m_iUnityTextureID);
            if (zArr[0]) {
                GLES20.glEnable(2929);
            }
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (mediaPlayer == this.m_MediaPlayer) {
            this.m_iCurrentSeekPercent = i;
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mediaPlayer == this.m_MediaPlayer) {
            this.m_iCurrentState = MEDIAPLAYER_STATE.END;
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (mediaPlayer != this.m_MediaPlayer) {
            return false;
        }
        switch (i) {
            case 1:
                break;
            case 100:
                break;
            case HttpResponse.f88OK:
                break;
            default:
                String str = "Unknown error " + i;
                break;
        }
        this.m_iErrorCode = i;
        this.m_iErrorCodeExtra = i2;
        this.m_iCurrentState = MEDIAPLAYER_STATE.ERROR;
        return true;
    }

    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.m_bUpdate = true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (mediaPlayer == this.m_MediaPlayer) {
            this.m_iCurrentState = MEDIAPLAYER_STATE.READY;
            SetManagerID(this.m_iNativeMgrID);
            this.m_iCurrentSeekPercent = 0;
            this.m_MediaPlayer.setOnBufferingUpdateListener(this);
        }
    }
}
