package com.xhuyu.component.mvp.model;

public class PaymentResult {
    private String message;
    private int resultCode;

    public PaymentResult(int resultCode2, String message2) {
        this.resultCode = resultCode2;
        this.message = message2;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode2) {
        this.resultCode = resultCode2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public String toString() {
        return "PaymentResult{resultCode=" + this.resultCode + ", message='" + this.message + '\'' + '}';
    }
}
