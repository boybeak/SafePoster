package com.github.boybeak.poster.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ActivityLifecycleWatcher extends AbsLifecycleWatcher {

    private final OnActivityDestroyCallback destroyWatcher = new OnActivityDestroyCallback() {
        @Override
        public void onActivityDestroyed(Activity activity) {
            terminate(activity);
        }
    };

    @Override
    public void onWatch(Object key, int size) {
        destroyWatcher.watchIfNot((Activity) key);
    }

    @Override
    public void onUnwatch(Object key, int size) {
    }

    private abstract class OnActivityDestroyCallback implements Application.ActivityLifecycleCallbacks {

        private boolean isWatching = false;
        public void watchIfNot(Activity activity) {
            if (isWatching) {
                return;
            }
            activity.getApplication().registerActivityLifecycleCallbacks(this);
            isWatching = true;
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
        @Override
        public void onActivityStarted(Activity activity) {}
        @Override
        public void onActivityResumed(Activity activity) {}
        @Override
        public void onActivityPaused(Activity activity) {}
        @Override
        public void onActivityStopped(Activity activity) {}
        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
    }

}
