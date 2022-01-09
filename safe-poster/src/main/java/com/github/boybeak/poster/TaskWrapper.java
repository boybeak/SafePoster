package com.github.boybeak.poster;

class TaskWrapper implements Runnable {

    private Runnable task;
    private OnThrow onThrow;
    private OnTaskListener onComplete;

    public TaskWrapper(Runnable task, OnThrow onThrow, OnTaskListener onComplete) {
        this.task = task;
        this.onThrow = onThrow;
        this.onComplete = onComplete;
    }

    @Override
    public void run() {
        try {
            if (task != null) {
                task.run();
            }
        } catch (Throwable t) {
            if (onThrow != null) {
                onThrow.onThrow(t);
            }
        } finally {
            if (onComplete != null) {
                onComplete.onTaskComplete();
            }
            clear();
        }
    }

    void clear() {
        task = null;
        onThrow = null;
        onComplete = null;
    }

    interface OnTaskListener {
        void onTaskComplete();
    }

}
