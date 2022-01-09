package com.github.boybeak.poster.lifecycle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbsLifecycleWatcher implements ILifecycleWatcher {

    private final Map<Object, LinkedList<OnTerminateListener>> subscriptions = new HashMap<>();

    LinkedList<OnTerminateListener> obtainTerminators(Object key) {
        LinkedList<OnTerminateListener> terminators = subscriptions.get(key);
        if (terminators == null) {
            terminators = new LinkedList<>();
            subscriptions.put(key, terminators);
        }
        return terminators;
    }

    boolean isWatching(Object key) {
        return subscriptions.containsKey(key);
    }

    @Override
    public final void watch(Object t, OnTerminateListener listener) {
        LinkedList<OnTerminateListener> terminators = obtainTerminators(t);
        onWatch(t, terminators.size());
        terminators.add(listener);
    }

    @Override
    public final void unwatch(Object t, OnTerminateListener listener) {
        LinkedList<OnTerminateListener> terminators = subscriptions.get(t);
        if (terminators != null) {
            terminators.remove(listener);
            if (terminators.isEmpty()) {
                subscriptions.remove(t);
            }
            onUnwatch(t, terminators.size());
        }
    }

    public void terminate(Object key) {
        LinkedList<OnTerminateListener> terminators = subscriptions.remove(key);
        if (terminators != null) {
            for (OnTerminateListener t : terminators) {
                t.onTerminated();
            }
            terminators.clear();
        }
    }

    public abstract void onWatch(Object key, int size);
    public abstract void onUnwatch(Object key, int size);


}
