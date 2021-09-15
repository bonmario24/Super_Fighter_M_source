package com.eagle.mixsdk.sdk.impl;

import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.UserExtraData;
import com.eagle.mixsdk.sdk.test.listeners.ISDKListener;
import com.eagle.mixsdk.sdk.test.widgets.ExtendDialog;

class DefaultSDKPlatform$5 implements ISDKListener {
    final /* synthetic */ DefaultSDKPlatform this$0;
    final /* synthetic */ UserExtraData val$data;

    DefaultSDKPlatform$5(DefaultSDKPlatform defaultSDKPlatform, UserExtraData userExtraData) {
        this.this$0 = defaultSDKPlatform;
        this.val$data = userExtraData;
    }

    public void onFailed(int i) {
    }

    public void onSuccess() {
        EagleSDK.getInstance().runOnMainThread(new Runnable() {
            public void run() {
                ExtendDialog.createDialog(EagleSDK.getInstance().getContext(), "你的提交数据为:dataType(上报类型)(必传):" + DefaultSDKPlatform$5.this.val$data.getDataType() + "\n roleID(角色ID)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleID() + "\n roleName(角色名称)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleName() + "\n roleLevel(角色等级)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleLevel() + "\n serverID(服务器ID)(必传):" + DefaultSDKPlatform$5.this.val$data.getServerID() + "\n serverName(服务名称)(必传):" + DefaultSDKPlatform$5.this.val$data.getServerName() + "\n moneyNum(角色金币数)(必传):" + DefaultSDKPlatform$5.this.val$data.getMoneyNum() + "\n roleCreateTime(角色创建时间)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleCreateTime() + "\n roleLevelUpTime(角色等级升级时间)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleLevelUpTime() + "\n vip(VIP等级)(必传):" + DefaultSDKPlatform$5.this.val$data.getVip() + "\n roleGender(性别)(必传):" + DefaultSDKPlatform$5.this.val$data.getRoleGender() + "\n professionID(职业ID)(选传):" + DefaultSDKPlatform$5.this.val$data.getProfessionID() + "\n professionName(职业名称)(选传):" + DefaultSDKPlatform$5.this.val$data.getProfessionName() + "\n power(战力)(选传):" + DefaultSDKPlatform$5.this.val$data.getPower() + "\n partyID(工会ID)(选传):" + DefaultSDKPlatform$5.this.val$data.getPartyID() + "\n partyName(工会名称)v:" + DefaultSDKPlatform$5.this.val$data.getPartyName() + "\n partyMasterID(工会会长ID)(选传):" + DefaultSDKPlatform$5.this.val$data.getPartyMasterID() + "\n partyMasterName(工会会长名称)(选传):" + DefaultSDKPlatform$5.this.val$data.getPartyMasterName(), (ExtendDialog.ExtendDialogListener) null);
            }
        });
    }
}
