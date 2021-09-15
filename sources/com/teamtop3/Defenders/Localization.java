package com.teamtop3.Defenders;

public class Localization {
    public static String TuiSong_content0;
    public static String TuiSong_content1;
    public static String TuiSong_content2;
    public static String TuiSong_content3;
    public static String TuiSong_content4;
    public static String TuiSong_content5;
    public static String TuiSong_title0;
    public static String TuiSong_title1;
    public static String TuiSong_title2;
    public static String TuiSong_title3;
    public static String TuiSong_title4;
    public static String TuiSong_title5;
    public static String m_cancel = null;
    public static String m_paycal = null;
    public static String m_payfail = null;
    public static String m_paysucce = null;
    public static String m_shouhuzhe = null;
    public static String m_sure = null;

    /* renamed from: me */
    UnityPlayerActivity f89me = null;

    public Localization(UnityPlayerActivity unityPlayerActivity) {
        this.f89me = unityPlayerActivity;
        m_sure = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("queding", "string", unityPlayerActivity.getPackageName()));
        int identifier = unityPlayerActivity.getResources().getIdentifier("quxiao", "string", unityPlayerActivity.getPackageName());
        m_cancel = (String) this.f89me.getResources().getText(identifier);
        int identifier2 = unityPlayerActivity.getResources().getIdentifier("Shouhuzhemen", "string", unityPlayerActivity.getPackageName());
        m_shouhuzhe = (String) this.f89me.getResources().getText(identifier);
        TuiSong_title0 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title0", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content0 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content0", "string", unityPlayerActivity.getPackageName()));
        TuiSong_title1 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title1", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content1 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content1", "string", unityPlayerActivity.getPackageName()));
        TuiSong_title2 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title2", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content2 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content2", "string", unityPlayerActivity.getPackageName()));
        TuiSong_title3 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title3", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content3 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content3", "string", unityPlayerActivity.getPackageName()));
        TuiSong_title4 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title4", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content4 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content4", "string", unityPlayerActivity.getPackageName()));
        TuiSong_title5 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_title5", "string", unityPlayerActivity.getPackageName()));
        TuiSong_content5 = (String) this.f89me.getResources().getText(unityPlayerActivity.getResources().getIdentifier("TuiSong_content5", "string", unityPlayerActivity.getPackageName()));
    }
}
