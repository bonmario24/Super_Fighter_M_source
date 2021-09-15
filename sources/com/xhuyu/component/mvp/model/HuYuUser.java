package com.xhuyu.component.mvp.model;

import android.text.TextUtils;
import com.xhuyu.component.utils.RSAUtil;
import java.io.Serializable;

public class HuYuUser implements Serializable {
    private String access_token;
    private String area_abbreviation = "";
    private String area_code = "";
    private String email = "";
    private IdentityInfo identity_info;
    private int identity_status = 1;
    private String last_login_time;
    private String mobile;
    private int mobile_status = 1;
    private String password;
    private int password_status;
    private String phone;
    private int pwd_status;
    private int register_flag;
    private String star_token;
    private int status;
    private String time = (System.currentTimeMillis() + "");
    private String token;
    private long uid;
    private long user_wallet_amount;
    private String username;

    public HuYuUser() {
    }

    public HuYuUser(XUserBuilder builder) {
        this.username = builder.account;
        this.uid = builder.uid.longValue();
        this.mobile = builder.mobile;
        this.password = builder.password;
        this.access_token = builder.access_token;
        this.token = builder.token;
        this.time = builder.time;
        this.register_flag = builder.register_flag;
        this.user_wallet_amount = builder.user_wallet_amount;
        this.password_status = builder.password_status;
        this.phone = builder.phone;
        this.area_code = builder.area_code;
        this.area_abbreviation = builder.area_abbreviation;
        this.email = builder.email;
    }

    public String getArea_code() {
        return this.area_code;
    }

    public void setArea_code(String area_code2) {
        this.area_code = area_code2;
    }

    public String getArea_abbreviation() {
        return this.area_abbreviation;
    }

    public void setArea_abbreviation(String area_abbreviation2) {
        this.area_abbreviation = area_abbreviation2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public long getUser_wallet_amount() {
        return this.user_wallet_amount;
    }

    public void setUser_wallet_amount(long user_wallet_amount2) {
        this.user_wallet_amount = user_wallet_amount2;
    }

    public int getRegister_flag() {
        return this.register_flag;
    }

    public void setRegister_flag(int register_flag2) {
        this.register_flag = register_flag2;
    }

    public String getStar_token() {
        return this.star_token;
    }

    public void setStar_token(String star_token2) {
        this.star_token = star_token2;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status2) {
        this.status = status2;
    }

    public String getLast_login_time() {
        return this.last_login_time;
    }

    public void setLast_login_time(String last_login_time2) {
        this.last_login_time = last_login_time2;
    }

    public int getPassword_status() {
        return this.password_status;
    }

    public void setPassword_status(int password_status2) {
        this.password_status = password_status2;
    }

    public int getPwd_status() {
        return this.pwd_status;
    }

    public void setPwd_status(int pwd_status2) {
        this.pwd_status = pwd_status2;
    }

    public int getIdentity_status() {
        return this.identity_status;
    }

    public void setIdentity_status(int identity_status2) {
        this.identity_status = identity_status2;
    }

    public int getMobile_status() {
        return this.mobile_status;
    }

    public void setMobile_status(int mobile_status2) {
        this.mobile_status = mobile_status2;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }

    public HuYuUser(Long user_id) {
        this.uid = user_id.longValue();
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile2) {
        this.mobile = mobile2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token2) {
        this.access_token = access_token2;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone2) {
        this.phone = phone2;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time2) {
        this.time = time2;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long uid2) {
        this.uid = uid2;
    }

    public IdentityInfo getIdentity_info() {
        return this.identity_info;
    }

    public void setIdentity_info(IdentityInfo identity_info2) {
        this.identity_info = identity_info2;
    }

    public String getRSAPassword() {
        if (TextUtils.isEmpty(this.password)) {
            return "";
        }
        if (this.password.length() < 30) {
            return RSAUtil.publicEncrypt(this.password);
        }
        return this.password;
    }

    public String toString() {
        return this.username + "---" + this.password + "----" + this.time;
    }

    public static class XUserBuilder {
        /* access modifiers changed from: private */
        public String access_token;
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public String area_abbreviation;
        /* access modifiers changed from: private */
        public String area_code;
        /* access modifiers changed from: private */
        public String email = "";
        /* access modifiers changed from: private */
        public String mobile;
        /* access modifiers changed from: private */
        public String password;
        /* access modifiers changed from: private */
        public int password_status;
        /* access modifiers changed from: private */
        public String phone;
        /* access modifiers changed from: private */
        public int register_flag;
        /* access modifiers changed from: private */
        public String time = (System.currentTimeMillis() + "");
        /* access modifiers changed from: private */
        public String token;
        /* access modifiers changed from: private */
        public Long uid = 0L;
        /* access modifiers changed from: private */
        public long user_wallet_amount;

        public void setEmail(String email2) {
            this.email = email2;
        }

        public void setArea_code(String area_code2) {
            this.area_code = area_code2;
        }

        public void setArea_abbreviation(String area_abbreviation2) {
            this.area_abbreviation = area_abbreviation2;
        }

        public void setPhone(String phone2) {
            this.phone = phone2;
        }

        public XUserBuilder setAccount(String account2) {
            this.account = account2;
            return this;
        }

        public XUserBuilder setUser_id(Long user_id) {
            this.uid = user_id;
            return this;
        }

        public XUserBuilder setMobile(String mobile2) {
            this.mobile = mobile2;
            return this;
        }

        public XUserBuilder setPassword(String password2) {
            this.password = password2;
            return this;
        }

        public XUserBuilder setAccess_token(String access_token2) {
            this.access_token = access_token2;
            return this;
        }

        public void setPassword_status(int password_status2) {
            this.password_status = password_status2;
        }

        public XUserBuilder setToken(String token2) {
            this.token = token2;
            return this;
        }

        public XUserBuilder setTime(String time2) {
            this.time = time2;
            return this;
        }

        public void setUser_wallet_amount(long user_wallet_amount2) {
            this.user_wallet_amount = user_wallet_amount2;
        }

        public void setRegister_flag(int register_flag2) {
            this.register_flag = register_flag2;
        }

        public HuYuUser build() {
            return new HuYuUser(this);
        }
    }

    public static class IdentityInfo {
        private String identity;
        private String name;
        private String type;

        public IdentityInfo(String name2, String identity2, String type2) {
            this.name = name2;
            this.identity = identity2;
            this.type = type2;
        }

        public IdentityInfo() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name2) {
            this.name = name2;
        }

        public String getIdentity() {
            return this.identity;
        }

        public void setIdentity(String identity2) {
            this.identity = identity2;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }
    }
}
