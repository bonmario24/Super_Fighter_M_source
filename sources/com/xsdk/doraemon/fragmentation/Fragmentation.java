package com.xsdk.doraemon.fragmentation;

import com.xsdk.doraemon.fragmentation.helper.ExceptionHandler;

public class Fragmentation {
    public static final int BUBBLE = 2;
    static volatile Fragmentation INSTANCE = null;
    public static final int NONE = 0;
    public static final int SHAKE = 1;
    private boolean debug;
    private ExceptionHandler handler;
    private int mode = 2;

    public static Fragmentation getDefault() {
        if (INSTANCE == null) {
            synchronized (Fragmentation.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Fragmentation(new FragmentationBuilder());
                }
            }
        }
        return INSTANCE;
    }

    Fragmentation(FragmentationBuilder builder) {
        this.debug = builder.debug;
        if (this.debug) {
            this.mode = builder.mode;
        } else {
            this.mode = 0;
        }
        this.handler = builder.handler;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug2) {
        this.debug = debug2;
    }

    public ExceptionHandler getHandler() {
        return this.handler;
    }

    public void setHandler(ExceptionHandler handler2) {
        this.handler = handler2;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode2) {
        this.mode = mode2;
    }

    public static FragmentationBuilder builder() {
        return new FragmentationBuilder();
    }

    public static class FragmentationBuilder {
        /* access modifiers changed from: private */
        public boolean debug;
        /* access modifiers changed from: private */
        public ExceptionHandler handler;
        /* access modifiers changed from: private */
        public int mode;

        public FragmentationBuilder debug(boolean debug2) {
            this.debug = debug2;
            return this;
        }

        public FragmentationBuilder stackViewMode(int mode2) {
            this.mode = mode2;
            return this;
        }

        public FragmentationBuilder handleException(ExceptionHandler handler2) {
            this.handler = handler2;
            return this;
        }

        public Fragmentation install() {
            Fragmentation.INSTANCE = new Fragmentation(this);
            return Fragmentation.INSTANCE;
        }
    }
}
