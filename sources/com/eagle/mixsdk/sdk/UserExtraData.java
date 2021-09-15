package com.eagle.mixsdk.sdk;

import com.facebook.appevents.AppEventsConstants;

public class UserExtraData {
    public static final int TYPE_CREATE_ROLE = 2;
    public static final int TYPE_ENTER_COPY = 6;
    public static final int TYPE_ENTER_GAME = 3;
    public static final int TYPE_EXIT_COPY = 7;
    public static final int TYPE_EXIT_GAME = 5;
    public static final int TYPE_LEVEL_UP = 4;
    public static final int TYPE_SELECT_SERVER = 1;
    public static final int TYPE_VIP_LEVELUP = 8;
    private int dataType;
    private int moneyNum;
    private String partyID = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private String partyMasterID = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private String partyMasterName = "无";
    private String partyName = "无";
    private String power = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private String professionID = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private String professionName = "无";
    private long roleCreateTime;
    private int roleGender;
    private String roleID = "";
    private String roleLevel = "";
    private long roleLevelUpTime;
    private String roleName = "";
    private int serverID;
    private String serverName = "";
    private String vip = "";

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType2) {
        this.dataType = dataType2;
    }

    public String getRoleID() {
        return this.roleID;
    }

    public void setRoleID(String roleID2) {
        this.roleID = roleID2;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName2) {
        this.roleName = roleName2;
    }

    public String getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(String roleLevel2) {
        this.roleLevel = roleLevel2;
    }

    public int getServerID() {
        return this.serverID;
    }

    public void setServerID(int serverID2) {
        this.serverID = serverID2;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName2) {
        this.serverName = serverName2;
    }

    public int getMoneyNum() {
        return this.moneyNum;
    }

    public void setMoneyNum(int moneyNum2) {
        this.moneyNum = moneyNum2;
    }

    public long getRoleCreateTime() {
        return this.roleCreateTime;
    }

    public void setRoleCreateTime(long roleCreateTime2) {
        this.roleCreateTime = roleCreateTime2;
    }

    public long getRoleLevelUpTime() {
        return this.roleLevelUpTime;
    }

    public void setRoleLevelUpTime(long roleLevelUpTime2) {
        this.roleLevelUpTime = roleLevelUpTime2;
    }

    public String getVip() {
        return this.vip;
    }

    public void setVip(String vip2) {
        this.vip = vip2;
    }

    public int getRoleGender() {
        return this.roleGender;
    }

    public void setRoleGender(int roleGender2) {
        this.roleGender = roleGender2;
    }

    public String getProfessionID() {
        return this.professionID;
    }

    public void setProfessionID(String professionID2) {
        this.professionID = professionID2;
    }

    public String getProfessionName() {
        return this.professionName;
    }

    public void setProfessionName(String professionName2) {
        this.professionName = professionName2;
    }

    public String getPower() {
        return this.power;
    }

    public void setPower(String power2) {
        this.power = power2;
    }

    public String getPartyID() {
        return this.partyID;
    }

    public void setPartyID(String partyID2) {
        this.partyID = partyID2;
    }

    public String getPartyName() {
        return this.partyName;
    }

    public void setPartyName(String partyName2) {
        this.partyName = partyName2;
    }

    public String getPartyMasterID() {
        return this.partyMasterID;
    }

    public void setPartyMasterID(String partyMasterID2) {
        this.partyMasterID = partyMasterID2;
    }

    public String getPartyMasterName() {
        return this.partyMasterName;
    }

    public void setPartyMasterName(String partyMasterName2) {
        this.partyMasterName = partyMasterName2;
    }
}
