package com.xhuyu.component.mvp.model;

public class ShareResult {
    String failureMsg = "";
    String postId = "";
    private int resultCode;

    public ShareResult() {
    }

    public ShareResult(int resultCode2, String postId2, String failureMsg2) {
        this.resultCode = resultCode2;
        this.postId = postId2;
        this.failureMsg = failureMsg2;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode2) {
        this.resultCode = resultCode2;
    }

    public String getPostId() {
        return this.postId;
    }

    public void setPostId(String postId2) {
        this.postId = postId2;
    }

    public String getFailureMsg() {
        return this.failureMsg;
    }

    public void setFailureMsg(String failureMsg2) {
        this.failureMsg = failureMsg2;
    }
}
