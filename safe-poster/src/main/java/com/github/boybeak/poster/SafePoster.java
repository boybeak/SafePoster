package com.github.boybeak.poster;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.github.boybeak.poster.lifecycle.ActivityLifecycleWatcher;
import com.github.boybeak.poster.lifecycle.ILifecycleWatcher;
import com.github.boybeak.poster.lifecycle.ViewLifecycleWatcher;

public class SafePoster {
    public static SafePoster by (View view) {
        return new SafePoster(view);
    }
    public static SafePoster by (Handler handler) {
        return new SafePoster(handler);
    }
    public static SafePoster by (Looper looper) {
        return new SafePoster(looper);
    }
    public static SafePoster byDefault() {
        return by(Looper.getMainLooper());
    }

    private static final ViewLifecycleWatcher viewLifecycleWatcher = new ViewLifecycleWatcher();
    private static final ActivityLifecycleWatcher activityLifecycleWatcher = new ActivityLifecycleWatcher();

    private IPoster poster;
    private Object watchingObject;
    private ILifecycleWatcher watcher;
    private OnThrow onThrow;

    private SafePoster(View v) {
        poster = new ViewPoster(v);
        liveWith(v);
    }

    private SafePoster(Handler handler) {
        poster = new HandlerPoster(handler);
    }

    private SafePoster(Looper looper) {
        poster = new HandlerPoster(looper);
    }

    public SafePoster liveWith(View v) {
        watchingObject = v;
        watcher = viewLifecycleWatcher;
        return this;
    }

    public SafePoster liveWith(Activity activity) {
        watchingObject = activity;
        watcher = activityLifecycleWatcher;
        return this;
    }

    public SafePoster onThrow(OnThrow onThrow) {
        this.onThrow = onThrow;
        return this;
    }

    public void post(Runnable task) {
        if (watcher == null) {
            throw new IllegalStateException("No lifecycle assigned to poster, call liveWith before do post or postDelayed.");
        }
        poster.setILifecycleWatcherAndWatchingObject(watcher, watchingObject);
        poster.post(task, onThrow);
        clear();
    }

    public void postDelayed(long delayedInMills, Runnable task) {
        if (watcher == null) {
            throw new IllegalStateException("No lifecycle assigned to poster, call liveWith before do post or postDelayed.");
        }
        poster.setILifecycleWatcherAndWatchingObject(watcher, watchingObject);
        poster.postDelayed(delayedInMills, task, onThrow);
        clear();
    }

    private void clear() {
        poster = null;
        watchingObject = null;
        watcher = null;
        onThrow = null;
    }

}
