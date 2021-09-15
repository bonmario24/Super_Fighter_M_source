package com.thinkfly.cloudlader.data;

import org.json.JSONObject;

public class DBData {
    private int _id = -1;
    private JSONObject json;
    private int priority;
    private String timestamp;

    public DBData(JSONObject json2, int priority2, int _id2, String timestamp2) {
        this.json = json2;
        this.priority = priority2;
        this.timestamp = timestamp2;
        this._id = _id2;
    }

    public JSONObject getJson() {
        return this.json;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public int getId() {
        return this._id;
    }
}
