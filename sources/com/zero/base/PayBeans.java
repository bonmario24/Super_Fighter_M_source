package com.zero.base;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class PayBeans {
    private static PayBeans bean;
    private float ammount = 1.0f;
    private String orderId = "";
    private String pro_id = "";
    private String pro_name = "";
    private String sdk_pro_id = "";

    public static PayBeans getBean() {
        return bean;
    }

    private static PayBeans init() {
        if (bean == null) {
            bean = new PayBeans();
        }
        return bean;
    }

    public static PayBeans resolve(String str) {
        PayBeans init = init();
        Log.e("Unity", "jsonstr= " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("orderId")) {
                init.orderId = jSONObject.getString("orderId");
            }
            if (jSONObject.has("ammount")) {
                init.ammount = Float.parseFloat(jSONObject.getString("ammount"));
            }
            if (jSONObject.has("pro_id")) {
                init.pro_id = jSONObject.getString("pro_id");
            }
            if (jSONObject.has("pro_name")) {
                init.pro_name = jSONObject.getString("pro_name");
            }
            if (!jSONObject.has("sdkProId")) {
                return init;
            }
            init.sdk_pro_id = jSONObject.getString("sdkProId");
            return init;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setBean(PayBeans payBeans) {
        bean = payBeans;
    }

    public float getAmmount() {
        return this.ammount;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getPro_id() {
        return this.pro_id;
    }

    public String getPro_name() {
        return this.pro_name;
    }

    public String getSdk_pro_id() {
        return this.sdk_pro_id;
    }

    public void setAmmount(float f) {
        this.ammount = f;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setPro_id(String str) {
        this.pro_id = str;
    }

    public void setPro_name(String str) {
        this.pro_name = str;
    }
}
