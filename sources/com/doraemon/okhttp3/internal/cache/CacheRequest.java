package com.doraemon.okhttp3.internal.cache;

import com.doraemon.okio.Sink;
import java.io.IOException;

public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
