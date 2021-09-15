package com.eagle.mixsdk.track;

import java.util.ArrayList;
import java.util.List;

public class EagleTrackManager implements IEagleTrack {
    private List<IEagleTrack> tracks;

    private static class SingletonHolder {
        public static EagleTrackManager instance = new EagleTrackManager();

        private SingletonHolder() {
        }
    }

    private EagleTrackManager() {
        this.tracks = new ArrayList();
    }

    public static EagleTrackManager getInstance() {
        return SingletonHolder.instance;
    }

    public void addTracks(IEagleTrack track) {
        if (track != null && !this.tracks.contains(track)) {
            this.tracks.add(track);
        }
    }

    public void onGameEvent(Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onGameEvent(object);
        }
    }

    public void onStartLogin() {
        for (IEagleTrack track : this.tracks) {
            track.onStartLogin();
        }
    }

    public void onShowLoginPage() {
        for (IEagleTrack track : this.tracks) {
            track.onShowLoginPage();
        }
    }

    public void onNotifyLogin() {
        for (IEagleTrack track : this.tracks) {
            track.onNotifyLogin();
        }
    }

    public void onLogout() {
        for (IEagleTrack track : this.tracks) {
            track.onLogout();
        }
    }

    public void onInitSDK(int code, String msg) {
        for (IEagleTrack track : this.tracks) {
            track.onInitSDK(code, msg);
        }
    }

    public void onInitEagle(int code, String msg) {
        for (IEagleTrack track : this.tracks) {
            track.onInitEagle(code, msg);
        }
    }

    public void onEagleLogin(int code, String msg) {
        for (IEagleTrack track : this.tracks) {
            track.onEagleLogin(code, msg);
        }
    }

    public void onSDKLogin(int code, String msg) {
        for (IEagleTrack track : this.tracks) {
            track.onSDKLogin(code, msg);
        }
    }

    public void onStartOrder(Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onStartOrder(object);
        }
    }

    public void onOrderResult(int code, Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onOrderResult(code, object);
        }
    }

    public void onStartPay(Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onStartPay(object);
        }
    }

    public void onPayResult(int code, Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onPayResult(code, object);
        }
    }

    public void onStartPayVerify(Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onStartPayVerify(object);
        }
    }

    public void onPayVerifyResult(int code, Object object) {
        for (IEagleTrack track : this.tracks) {
            track.onPayVerifyResult(code, object);
        }
    }

    public void onStartPlayerAntiAddictionInfo() {
        for (IEagleTrack track : this.tracks) {
            track.onStartPlayerAntiAddictionInfo();
        }
    }

    public void onPlayerAntiAddictionInfoResult() {
        for (IEagleTrack track : this.tracks) {
            track.onPlayerAntiAddictionInfoResult();
        }
    }
}
