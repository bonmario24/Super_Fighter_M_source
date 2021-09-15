package com.xhuyu.component.utils;

import com.android.vending.billing.IInAppBillingService;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtil {
    public static void doSetPayloadToBillingClient(Object billingClientObj, final String developerPayload) {
        try {
            Field field = billingClientObj.getClass().getDeclaredField("mService");
            field.setAccessible(true);
            final Object originObj = field.get(billingClientObj);
            field.set(billingClientObj, (IInAppBillingService) Proxy.newProxyInstance(IInAppBillingService.class.getClassLoader(), new Class[]{IInAppBillingService.class}, new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    String methodName = method.getName();
                    char c = 65535;
                    switch (methodName.hashCode()) {
                        case -1441906678:
                            if (methodName.equals("getBuyIntentExtraParams")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -1087269364:
                            if (methodName.equals("getBuyIntent")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -653676637:
                            if (methodName.equals("getBuyIntentToReplaceSkus")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            args[4] = developerPayload;
                            break;
                        case 1:
                            args[5] = developerPayload;
                            break;
                        case 2:
                            args[4] = developerPayload;
                            break;
                    }
                    return method.invoke(originObj, args);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
