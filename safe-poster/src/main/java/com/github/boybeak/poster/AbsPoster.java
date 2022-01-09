package com.github.boybeak.poster;

import com.github.boybeak.poster.lifecycle.ILifecycleWatcher;

abstract class AbsPoster implements IPoster {

    private TaskWrapper postedTask;
    private ILifecycleWatcher watcher;
    private Object watchingObject;
    private boolean isPosted = false;

    @Override
    public void setILifecycleWatcherAndWatchingObject(ILifecycleWatcher watcher, Object object) {
        this.watcher = watcher;
        this.watchingObject = object;
    }

    @Override
    public void post(Runnable task, OnThrow onThrow) {
        if (isPosted) {
            throw new IllegalStateException("Do not reuse Poster");
        }
        watcher.watch(watchingObject, this);
        TaskWrapper taskWrapper = new TaskWrapper(task, onThrow, this);

        doPost(taskWrapper);

        postedTask = taskWrapper;
        isPosted = true;

    }

    @Override
    public void postDelayed(long delayedInMills, Runnable task, OnThrow onThrow) {
        if (isPosted) {
            throw new IllegalStateException("Do not reuse Poster");
        }
        watcher.watch(watchingObject, this);
        TaskWrapper taskWrapper = new TaskWrapper(task, onThrow, this);

        doPostDelayed(delayedInMills, taskWrapper);

        postedTask = taskWrapper;
        isPosted = true;
    }

    @Override
    public void onTerminated() {
        doRemoveCallback(postedTask);
        clear();
    }

    @Override
    public void onTaskComplete() {
        if (watcher != null && watchingObject != null) {
            watcher.unwatch(watchingObject, AbsPoster.this);
        }
        clear();
    }

    private void clear() {
        postedTask = null;
        watcher = null;
        watchingObject = null;
        doClear();
    }

    abstract void doPost(TaskWrapper task);
    abstract void doPostDelayed(long delayedInMills, TaskWrapper task);
    abstract void doRemoveCallback(TaskWrapper task);
    abstract void doClear();

}
