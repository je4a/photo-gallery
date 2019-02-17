package com.gallery.io

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class ImageLoaderTask : AsyncTask<Any, Void, Bitmap?>() {
    private val uuid = UUID.randomUUID().toString()
    private val tag = "ImageLoader #$uuid"

    private var listener: TaskResultListener<Bitmap?>? = null

    // 0 - photo url
    // 1 - listener
    override fun doInBackground(vararg params: Any): Bitmap? {
        if (params.isEmpty()) {
            throw IllegalArgumentException("Task needs parameters")
        }

        var urlConnection: HttpURLConnection? = null

        listener = params[1] as TaskResultListener<Bitmap?>
        try {
            urlConnection = URL(params[0] as String).openConnection() as HttpURLConnection
            return BitmapFactory.decodeStream(urlConnection.inputStream)
        } catch(ex: Exception) {
            Log.e(tag, ex.message, ex)
        } finally {
            urlConnection?.disconnect()
        }

        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        listener?.onResultReceived(result)
    }
}
