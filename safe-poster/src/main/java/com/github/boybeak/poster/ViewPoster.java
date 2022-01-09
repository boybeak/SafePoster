package com.github.boybeak.poster;

import android.view.View;

class ViewPoster extends AbsPoster {

    private View poster;

    ViewPoster(View posterView) {
        this.poster = posterView;
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
