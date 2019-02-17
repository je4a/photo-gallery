package com.gallery.io

import java.util.concurrent.Callable

interface ApiCaller<T> : Callable<T> {
}
