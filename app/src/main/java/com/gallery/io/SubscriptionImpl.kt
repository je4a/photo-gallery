package com.gallery.io

import android.os.AsyncTask

class SubscriptionImpl<T, out TASK: AsyncTask<TaskResultListener<T>, Void, T>>
    (private val task: TASK) : Subscription<T> {

    override fun subscribe(action: (T) -> Unit): Subscription<T> {
        task.execute(object : TaskResultListener<T> {
            override fun onResultReceived(result: T) {
                action(result)
            }
        })

        return this
    }

    override fun unsubscribe() {
        if (task.status == AsyncTask.Status.FINISHED) return
        task.cancel(true)
    }
}