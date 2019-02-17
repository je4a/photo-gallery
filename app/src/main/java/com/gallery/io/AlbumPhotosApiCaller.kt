package com.gallery.io

import android.util.Log
import com.gallery.data.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class AlbumPhotosApiCaller(private val albumId: Int) : BaseApiCaller<List<AlbumPhoto>>() {
    private val tag = "PHOTOS_API_LOADER"
    private val url = "https://jsonplaceholder.typicode.com/photos?albumId="

    override fun call(): List<AlbumPhoto> {
        var urlConnection: HttpURLConnection? = null

        try {
            val url = URL(url + albumId)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.setRequestProperty("Content-Type", "application/json")

            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val photosArray = JSONArray(streamToString(urlConnection.inputStream))

                return photosArray.toAlbumPhotoDtoList().toAlbumPhotoList()
            }
        } catch(ex: Exception) {
            Log.e(tag, ex.message, ex)
        } finally {
            urlConnection?.disconnect()
        }

        return emptyList()
    }
}
