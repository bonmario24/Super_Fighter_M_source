package com.xhuyu.component.mvp.model;

import java.io.Serializable;
import java.util.List;

public class NoticeBean implements Serializable {
    private List<NoticeData> exit;
    private List<NoticeData> init;
    private List<NoticeData> logined;

    public @interface GoToType {
        public static final int EXTERNAL_APP = 2;
        public static final int EXTERNAL_WEB = 1;
    }

    public @interface NoticeType {
        public static final int IMAGE = 2;
        public static final int TEXT = 1;
    }

    public @interface PopUpType {
        public static final int AFTER_LOGGING = 2;
        public static final int BEFORE_EXIT_APP = 3;
        public static final int BEFORE_LOGIN = 1;
        public static final int WITHOUT = 0;
    }

    public List<NoticeData> getInit() {
        return this.init;
    }

    public void setInit(List<NoticeData> init2) {
        this.init = init2;
    }

    public List<NoticeData> getLogined() {
        return this.logined;
    }

    public List<NoticeData> getExit() {
        return this.exit;
    }

    public void setExit(List<NoticeData> exit2) {
        this.exit = exit2;
    }

    public void setLogined(List<NoticeData> logined2) {
        this.logined = logined2;
    }

    public static class NoticeData implements Serializable {
        private int goto_type;
        private String image_url;
        private String notice_content;
        private String notice_title;
        private int notice_type;
        private String redirect_url;

        public int getGoto_type() {
            return this.goto_type;
        }

        public void setGoto_type(int goto_type2) {
            this.goto_type = goto_type2;
        }

        public int getNotice_type() {
            return this.notice_type;
        }

        public void setNotice_type(int notice_type2) {
            this.notice_type = notice_type2;
        }

        public String getNotice_title() {
            return this.notice_title;
        }

        public void setNotice_title(String notice_title2) {
            this.notice_title = notice_title2;
        }

        public String getNotice_content() {
            return this.notice_content;
        }

        public void setNotice_content(String notice_content2) {
            this.notice_content = notice_content2;
        }

        public String getImage_url() {
            return this.image_url;
        }

        public void setImage_url(String image_url2) {
            this.image_url = image_url2;
        }

        public String getRedirect_url() {
            return this.redirect_url;
        }

        public void setRedirect_url(String redirect_url2) {
            this.redirect_url = redirect_url2;
        }
    }
}
