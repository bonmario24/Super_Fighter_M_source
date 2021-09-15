package com.xsdk.doraemon.fragmentation.helper;

import androidx.annotation.NonNull;

public interface ExceptionHandler {
    void onException(@NonNull Exception exc);
}
