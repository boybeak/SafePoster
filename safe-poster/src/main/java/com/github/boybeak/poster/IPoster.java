package com.github.boybeak.poster;

import com.github.boybeak.poster.lifecycle.ILifecycleWatcher;

interface IPoster extends ILifecycleWatcher.OnTerminateListener, TaskWrapper.OnTaskListener {
    void setILifecycleWatcherAndWatchingObject(ILifecycleWatcher watcher, Object object);

    void post(Runnable task, OnThrow onThrow);

    void postDelayed(long delayedInMills, Runnable task, OnThrow onThrow);
}
