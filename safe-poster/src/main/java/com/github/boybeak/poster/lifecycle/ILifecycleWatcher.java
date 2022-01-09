package com.github.boybeak.poster.lifecycle;

public interface ILifecycleWatcher {
    void watch(Object t, OnTerminateListener listener);
    void unwatch(Object t, OnTerminateListener listener);

    interface OnTerminateListener {
        void onTerminated();
    }
}
