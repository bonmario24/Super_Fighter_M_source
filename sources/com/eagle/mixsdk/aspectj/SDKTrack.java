package com.eagle.mixsdk.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SDKTrack {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface GameEvent {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InitEagle {
        int result();
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InitSDK {
        int result();
    }
}
