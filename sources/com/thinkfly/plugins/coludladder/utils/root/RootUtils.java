package com.thinkfly.plugins.coludladder.utils.root;

import com.doraemon.p027eg.CommonUtil;

public class RootUtils {
    public static int isDeviceRooted() {
        return CommonUtil.getDeviceRootState();
    }
}
