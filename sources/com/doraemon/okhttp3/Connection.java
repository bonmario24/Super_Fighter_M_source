package com.doraemon.okhttp3;

import com.doraemon.ext.Nullable;
import java.net.Socket;

public interface Connection {
    @Nullable
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
