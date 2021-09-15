package com.star.libtrack.core;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

public class PageRecorder extends ArrayList<String> {
    private static String TAG = "xsdk";

    public boolean add(String s) {
        return !contains(s) && super.add(s);
    }

    public String getLast() {
        if (isEmpty()) {
            return "";
        }
        return (String) get(size() - 1);
    }

    public boolean removeLast(String last) {
        if (isEmpty() || TextUtils.isEmpty(last) || !last.equals(getLast())) {
            Log.d(TAG, "page remove last fail no match " + last);
            return false;
        }
        remove(size() - 1);
        return true;
    }

    public void printPageStack() {
        if (!isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size(); i++) {
                sb.append((String) get(i)).append("--");
            }
            Log.d(TAG, sb.toString());
        }
    }
}
