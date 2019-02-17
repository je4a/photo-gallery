package com.gallery.io

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.graphics.Bitmap
import android.support.v4.util.LruCache
import android.util.Log
import android.widget.ImageView
import java.lang.ref.WeakReference

class ImageLoader : LifecycleObserver {
    private val tag = "ImageLoader"
    private val cacheSize = 4 * 1024 * 1024 // 4MB
    private val bitmapCache = LruCache<Int, Bitmap>(cacheSize)
    private val taskCache = HashMap<Int, ImageLoaderTask>().toMutableMap()

    fun load(url: String, view: ImageView) {
        val cacheKey = url.hashCode()
        if (bitmapCache[cacheKey] != null) {
            view.setImageBitmap(bitmapCache[cacheKey])
            return
        }

        val weakView = WeakReference(view)
        val task = ImageLoaderTask()

        task.execute(url, object: TaskResultListener<Bitmap?> {
            override fun onResultReceived(result: Bitmap?) {
                result?.let { bitmap ->
                    bitmapCache.put(cacheKey, bitmap)
                    weakView.get()?.setImageBitmap(bitmap)
                    taskCache.remove(cacheKey)
                }
            }
        })
        taskCache[cacheKey] = task
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun shutdown() {
        taskCache.keys.forEach { taskCache[it]?.cancel(true) }
        bitmapCache.evictAll()
    }
}