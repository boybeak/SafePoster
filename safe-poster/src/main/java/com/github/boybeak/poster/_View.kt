package com.github.boybeak.poster

import android.view.View

fun View.postSafety(task: Runnable) {
    SafePoster.by(this).post(task)
}
fun View.postDelayedSafety(delayMills: Long, task: Runnable) {
    SafePoster.by(this).postDelayed(delayMills, task)
}