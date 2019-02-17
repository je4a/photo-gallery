package com.gallery.io

interface Subscription<T> {
    fun unsubscribe()
    fun subscribe(action: (T) -> Unit): Subscription<T>
}
