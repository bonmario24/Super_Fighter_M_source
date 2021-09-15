package com.star.libtrack.observer;

import com.star.libtrack.event.Event;

public abstract class BaseObserver {
    public abstract void handEvent(Event event);
}
