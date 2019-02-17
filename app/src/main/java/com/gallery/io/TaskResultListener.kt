package com.gallery.io

interface TaskResultListener<T> {
    fun onResultReceived(result: T)
}
