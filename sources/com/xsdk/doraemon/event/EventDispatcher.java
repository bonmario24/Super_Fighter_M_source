package com.xsdk.doraemon.event;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class EventDispatcher<T> implements IEventDispatcher {
    private boolean isStatic = Modifier.isStatic(this.mMethod.getModifiers());
    private Method mMethod;
    private WeakReference<T> mObserverRef;
    private boolean noneParameterTypes;

    EventDispatcher(T observer, Method method) {
        boolean z = true;
        this.mObserverRef = new WeakReference<>(observer);
        this.mMethod = method;
        this.mMethod.setAccessible(true);
        Class<?>[] parameterTypes = this.mMethod.getParameterTypes();
        if (!(parameterTypes == null || parameterTypes.length == 0)) {
            z = false;
        }
        this.noneParameterTypes = z;
    }

    public boolean dispatch(Object... params) {
        try {
            if (!this.isStatic) {
                if (this.mObserverRef.get() != null) {
                    if (this.noneParameterTypes) {
                        this.mMethod.invoke(this.mObserverRef.get(), new Object[0]);
                        return true;
                    }
                    this.mMethod.invoke(this.mObserverRef.get(), params);
                    return true;
                }
                return false;
            } else if (this.noneParameterTypes) {
                this.mMethod.invoke((Object) null, new Object[0]);
                return true;
            } else {
                this.mMethod.invoke((Object) null, params);
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }
}
