package com.gallery.io

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

abstract class BaseApiCaller<T> : ApiCaller<T> {
    protected fun streamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        var result = ""

        while (line != null) {
            result += line
            line = reader.readLine()
        }
        reader.close()

        return result
    }
}
