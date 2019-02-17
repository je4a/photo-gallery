package com.gallery.io

import android.os.AsyncTask
import java.lang.ref.WeakReference


class ApiTask<T>(private val apiCaller: ApiCaller<T>) :
    AsyncTask<TaskResultListener<T>, Void, T>() {

    private lateinit var internalListener: WeakReference<TaskResultListener<T>>

    override fun doInBackground(vararg params: TaskResultListener<T>?): T {
        if (params.isEmpty()) throw IllegalArgumentException("Listener is required")

        params[0]?.let {
            internalListener = WeakReference(it)
        }

        return apiCaller.call()
    }

    override fun onPostExecute(result: T) {
        internalListener.get()?.onResultReceived(result)
    }
}