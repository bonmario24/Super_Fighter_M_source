package com.eagle.mixsdk.sdk.utils.domain;

import android.text.TextUtils;
import com.doraemon.p027eg.CheckUtils;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.okhttp.model.DomainBean;
import com.eagle.mixsdk.sdk.utils.StoreUtils;
import java.util.ArrayList;
import java.util.List;

public class DomainsCacheUtil {
    public static final String CURRENT_DOMAIN_KEY = "eagle_current_domain";

    public static void saveNewDomain(String domainHostname) {
        StoreUtils.putString(CURRENT_DOMAIN_KEY, domainHostname);
    }

    public static String getCurrentCacheDomainHostname() {
        return StoreUtils.getString(CURRENT_DOMAIN_KEY);
    }

    public static String getCurrentUseDomainHostname(boolean hasResetAction) {
        List<DomainBean> domainsList = EagleSDK.getInstance().getDomainsList();
        if (hasResetAction) {
            resetAutoDomainListUseState(domainsList);
        }
        DomainBean cacheDomain = domainsList.get(0);
        if (cacheDomain.isUse()) {
            return cacheDomain.getDomainUrl();
        }
        return "";
    }

    private static void resetAutoDomainListUseState(List<DomainBean> domainsList) {
        String hostname = getCurrentCacheDomainHostname();
        DomainBean doBean = domainsList.get(0);
        if (CheckUtils.isNullOrEmpty(hostname) || !hostname.equals(doBean.getDomainUrl()) || !doBean.isUse()) {
            int index = -1;
            int i = 0;
            while (true) {
                if (i >= domainsList.size()) {
                    break;
                } else if (domainsList.get(i).isUse()) {
                    index = i;
                    break;
                } else {
                    i++;
                }
            }
            if (index != -1) {
                DomainBean domainBean = domainsList.get(index);
                domainsList.remove(domainBean);
                domainsList.add(0, domainBean);
            }
            for (DomainBean domainBean2 : domainsList) {
                domainBean2.setUse(true);
            }
            saveNewDomain(domainsList.get(0).getDomainUrl());
        }
    }

    public static void updateDomainState(String hostname, boolean isResetDomain) {
        List<DomainBean> domainsList = EagleSDK.getInstance().getDomainsList();
        if (!CheckUtils.isNullOrEmpty(hostname) && domainsList != null) {
            DomainBean tempBean = null;
            DomainBean domainBean = domainsList.get(0);
            if (hostname.equals(domainBean.getDomainUrl())) {
                domainBean.setUse(false);
                tempBean = domainBean;
                domainsList.remove(domainBean);
            }
            if (tempBean != null) {
                domainsList.add(tempBean);
            }
            if (isResetDomain) {
                for (DomainBean bean : domainsList) {
                    if (!hostname.equals(bean.getDomainUrl())) {
                        bean.setUse(true);
                    }
                }
            }
            saveNewDomain(domainsList.get(0).getDomainUrl());
        }
    }

    public static List<String> disposeServerUrl(String backupUrl) {
        if (TextUtils.isEmpty(backupUrl)) {
            return null;
        }
        List<String> urls = new ArrayList<>();
        String[] splitUrl = backupUrl.split(",");
        for (int i = 0; i < splitUrl.length; i++) {
            String url = splitUrl[i];
            if (!CheckUtils.isNullOrEmpty(url)) {
                if (url.endsWith("/")) {
                    splitUrl[i] = url.substring(0, url.length() - 1);
                }
                if (url.startsWith("http")) {
                    urls.add(splitUrl[i]);
                }
            }
        }
        if (urls.isEmpty()) {
            urls = null;
        }
        return urls;
    }
}
