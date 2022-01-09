package com.github.boybeak.poster;

import android.os.Handler;
import android.os.Looper;

class HandlerPoster extends AbsPoster {

    private Handler poster;

    public HandlerPoster(Handler poster) {
        this.poster = poster;
    }

    public HandlerPoster(Looper looper) {
        this(new Handler(looper));
    }

    @Override
    void doPost(TaskWrapper task) {
        poster.post(task);
    }

    @Override
    void doPostDelayed(long delayedInMills, TaskWrapper task) {
        poster.postDelayed(task, delayedInMills);
    }

    @Override
    void doRemoveCallback(TaskWrapper task) {
        poster.removeCallbacks(task);
    }

    @Override
    void doClear() {
        poster = null;
    }
}
