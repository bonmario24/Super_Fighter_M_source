package com.star.libtrack.obserable;

import com.star.libtrack.event.Event;
import com.star.libtrack.observer.BaseObserver;
import java.util.ArrayList;
import java.util.List;

public class BaseObserable {
    private List<BaseObserver> observers = new ArrayList();

    public void addObserver(BaseObserver observer) {
        if (observer != null && !hasObserver(observer)) {
            this.observers.add(observer);
        }
    }

    private boolean hasObserver(BaseObserver observer) {
        if (observer == null) {
            return false;
        }
        for (int i = 0; i < this.observers.size(); i++) {
            if (this.observers.get(i) == observer) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void dispatch(Event event) {
        for (int i = 0; i < this.observers.size(); i++) {
            BaseObserver observer = this.observers.get(i);
            if (observer != null) {
                observer.handEvent(event);
            }
        }
    }
}
