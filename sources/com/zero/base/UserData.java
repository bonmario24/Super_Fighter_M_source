package com.zero.base;

import com.android.billingclient.api.BillingFlowParams;
import com.facebook.appevents.AppEventsConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData {
    private static UserData data;
    private String accountId = "";
    private String accountName = "";
    private int fightVal = 0;
    private String gangName = "";
    private long gold = 0;
    private String isNew = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private int money = 0;
    private String profession = "";
    private int roleId = 0;
    private int roleLv = 0;
    private String roleName = "";
    private int vipLevel = 0;
    private int zoneId = 0;
    private String zoneName = "";

    private static UserData init() {
        if (data == null) {
            data = new UserData();
        }
        return data;
    }

    public static UserData resolve(String str) {
        UserData init = init();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("roleId")) {
                init.roleId = jSONObject.getInt("roleId");
            }
            if (jSONObject.has("roleName")) {
                init.roleName = jSONObject.getString("roleName");
            }
            if (jSONObject.has("roleLevel")) {
                init.roleLv = jSONObject.getInt("roleLevel");
            }
            if (jSONObject.has("zoneId")) {
                init.zoneId = jSONObject.getInt("zoneId");
            }
            if (jSONObject.has("zoneName")) {
                init.zoneName = jSONObject.getString("zoneName");
            }
            if (jSONObject.has("money")) {
                init.money = jSONObject.getInt("money");
            }
            if (jSONObject.has("gangName")) {
                init.gangName = jSONObject.getString("gangName");
            }
            if (jSONObject.has(BillingFlowParams.EXTRA_PARAM_KEY_ACCOUNT_ID)) {
                init.accountId = jSONObject.getString(BillingFlowParams.EXTRA_PARAM_KEY_ACCOUNT_ID);
            }
            if (jSONObject.has("accountName")) {
                init.accountName = jSONObject.getString("accountName");
            }
            if (jSONObject.has("gold")) {
                init.gold = (long) jSONObject.getInt("gold");
            }
            if (jSONObject.has("fightVal")) {
                init.fightVal = jSONObject.getInt("fightVal");
            }
            if (!jSONObject.has("vipLevel")) {
                return init;
            }
            init.vipLevel = jSONObject.getInt("vipLevel");
            return init;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public int getFightVal() {
        return this.fightVal;
    }

    public String getGangName() {
        return this.gangName;
    }

    public long getGold() {
        return this.gold;
    }

    public String getIsNew() {
        return getRoleLv() <= 1 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public int getMoney() {
        return this.money;
    }

    public String getProfession() {
        return this.profession;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public int getRoleLv() {
        return this.roleLv;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public int getVipLevel() {
        return this.vipLevel;
    }

    public int getZoneId() {
        return this.zoneId;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setAccountId(String str) {
        this.accountId = str;
    }

    public void setAccountName(String str) {
        this.accountName = str;
    }

    public void setFightVal(int i) {
        this.fightVal = i;
    }

    public void setGangName(String str) {
        this.gangName = str;
    }

    public void setGold(long j) {
        this.gold = j;
    }

    public void setIsNew(String str) {
        this.isNew = str;
    }

    public void setMoney(int i) {
        this.money = i;
    }

    public void setProfession(String str) {
        this.profession = str;
    }

    public void setRoleId(int i) {
        this.roleId = i;
    }

    public void setRoleLv(int i) {
        this.roleLv = i;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public void setVipLevel(int i) {
        this.vipLevel = i;
    }

    public void setZoneId(int i) {
        this.zoneId = i;
    }

    public void setZoneName(String str) {
        this.zoneName = str;
    }
}
