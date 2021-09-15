package com.xsdk.doraemon.event;

interface IEventDispatcher {
    boolean dispatch(Object... objArr);
}
