package com.xhuyu.component.widget.ucenter;

import android.view.ViewGroup;
import java.util.ArrayList;

class ViewStack extends ArrayList<IViewWrapper> {
    private ViewGroup container;

    ViewStack() {
    }

    public void attach(ViewGroup container2) {
        this.container = container2;
    }

    public void detach() {
        this.container = null;
    }

    public boolean checkEnvir() {
        return this.container != null;
    }

    public void push(IViewWrapper view) {
        if (checkEnvir()) {
            if (view.getStatus() != 1) {
                throw new RuntimeException("push view stack error status " + view.getStatus());
            }
            IViewWrapper last = top();
            add(view);
            this.container.addView(view.getBaseView());
            view.onViewStart();
            if (last != null && last.getStatus() != 3) {
                last.onViewStop();
            }
        }
    }

    public IViewWrapper pop() {
        IViewWrapper view = null;
        if (checkEnvir() && !isEmpty()) {
            view = (IViewWrapper) remove(size() - 1);
            this.container.removeView(view.getBaseView());
            if (view.getStatus() != 4) {
                view.onViewDestroy();
            }
            IViewWrapper top = top();
            if (!(top == null || top.getStatus() == 2)) {
                top.onViewStart();
            }
        }
        return view;
    }

    public void removeView(IViewWrapper view) {
        if (checkEnvir() && !isEmpty() && contains(view)) {
            remove(view);
            this.container.removeView(view.getBaseView());
            if (view.getStatus() != 4) {
                view.onViewDestroy();
            }
            IViewWrapper top = top();
            if (top != null && top.getStatus() != 2) {
                top.onViewStart();
            }
        }
    }

    public void clearStack() {
        if (!isEmpty()) {
            do {
            } while (pop() != null);
        }
    }

    public IViewWrapper top() {
        if (isEmpty()) {
            return null;
        }
        return (IViewWrapper) get(size() - 1);
    }
}
