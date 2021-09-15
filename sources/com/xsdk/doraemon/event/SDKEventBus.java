package com.xsdk.doraemon.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SDKEventBus {
    private static SDKEventBus defaultInstance;
    private AcceptEventListener acceptObserverListener = new AcceptEventListener() {
        public void acceptObserver(Object subscriber, int eventKey, Object... params) {
            SDKEventBus.this.scanSubscriber(subscriber);
            boolean unused = SDKEventBus.this.handle(subscriber, eventKey, params);
        }
    };
    private CopyOnWriteArrayList<Object> mContainer = new CopyOnWriteArrayList<>();
    private Map<Object, Map<Integer, IEventDispatcher>> mapEvents = new ConcurrentHashMap();

    interface AcceptEventListener<T> {
        void acceptObserver(T t, int i, Object... objArr);
    }

    private SDKEventBus() {
    }

    public static SDKEventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (SDKEventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new SDKEventBus();
                }
            }
        }
        return defaultInstance;
    }

    public synchronized void register(Object subscriber) {
        boolean exists = false;
        if (this.mContainer.size() > 0) {
            Iterator<Object> it = this.mContainer.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next() == subscriber) {
                        exists = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (!exists) {
            this.mContainer.add(subscriber);
        }
    }

    public synchronized void unregister(Object subscriber) {
        Iterator<Object> it = this.mContainer.iterator();
        while (it.hasNext()) {
            Object object = it.next();
            if (object == subscriber) {
                this.mContainer.remove(object);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scanSubscriber(Object subscriber) {
        List<Method> methodList;
        int[] eventArr;
        if (!this.mapEvents.containsKey(subscriber) && (methodList = searchDeclaredMethods(subscriber)) != null && !methodList.isEmpty()) {
            Map<Integer, IEventDispatcher> dispatcherMap = new HashMap<>();
            for (Method method : methodList) {
                Subscribe annotation = (Subscribe) method.getAnnotation(Subscribe.class);
                if (!(annotation == null || (eventArr = annotation.event()) == null || eventArr.length <= 0)) {
                    EventDispatcher dispatcher = new EventDispatcher(subscriber, method);
                    for (int event : eventArr) {
                        if (!dispatcherMap.containsKey(Integer.valueOf(event))) {
                            dispatcherMap.put(Integer.valueOf(event), dispatcher);
                        }
                    }
                }
            }
            if (dispatcherMap.size() > 0) {
                this.mapEvents.put(subscriber, dispatcherMap);
            }
        }
    }

    private static List<Method> searchDeclaredMethods(Object subscriber) {
        Method[] methods = subscriber.getClass().getDeclaredMethods();
        List<Method> methodList = null;
        if (methods != null && methods.length > 0) {
            methodList = new ArrayList<>(methods.length);
            methodList.addAll(Arrays.asList(methods));
        }
        return methodList == null ? Collections.emptyList() : methodList;
    }

    /* access modifiers changed from: private */
    public boolean handle(Object subscriber, int eventKey, Object... params) {
        if (this.mapEvents.containsKey(subscriber)) {
            Map<Integer, IEventDispatcher> dispatcherMap = this.mapEvents.get(subscriber);
            if (dispatcherMap.containsKey(Integer.valueOf(eventKey))) {
                dispatcherMap.get(Integer.valueOf(eventKey)).dispatch(params);
                return true;
            }
        }
        return false;
    }

    public void post(int eventKey, Object... params) {
        postEvent(eventKey, params);
    }

    private void postEvent(int eventKey, Object... params) {
        accept(this.acceptObserverListener, eventKey, params);
    }

    private void accept(AcceptEventListener<Object> listener, int eventKey, Object... params) {
        Iterator<Object> it = this.mContainer.iterator();
        while (it.hasNext()) {
            listener.acceptObserver(it.next(), eventKey, params);
        }
    }
}
