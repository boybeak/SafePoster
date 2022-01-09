package com.github.boybeak.poster.lifecycle;

import android.view.View;

public class ViewLifecycleWatcher extends AbsLifecycleWatcher {

    private final View.OnAttachStateChangeListener viewAttachWatcher = new View.OnAttachStateChangeListener() {
        @Override
        public void onViewAttachedToWindow(View v) {

        }

        @Override
        public void onViewDetachedFromWindow(View v) {
            terminate(v);
        }
    };


    @Override
    public void onWatch(Object key, int size) {
        View v = (View)key;
        if (!v.isAttachedToWindow()) {
            throw new IllegalStateException("The view must attached to a window");
        }
        if (size == 0) {
            v.addOnAttachStateChangeListener(viewAttachWatcher);
        }
    }

    @Override
    public void onUnwatch(Object key, int size) {
        if (size == 0) {
            ((View)key).removeOnAttachStateChangeListener(viewAttachWatcher);
        }
    }
}
