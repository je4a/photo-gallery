package com.gallery.io

import android.util.Log
import com.gallery.data.Album
import com.gallery.data.toAlbumDtoList
import com.gallery.data.toAlbumList
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class AlbumsListApiCaller : BaseApiCaller<List<Album>>() {
    private val tag = "ALBUMS_API_LOADER"
    private val url = "https://jsonplaceholder.typicode.com/albums"

    override fun call(): List<Album> {
        var urlConnection: HttpURLConnection? = null

        try {
            val url = URL(url)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.setRequestProperty("Content-Type", "application/json")

            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val albumsArray = JSONArray(streamToString(urlConnection.inputStream))

                return albumsArray.toAlbumDtoList().toAlbumList()
            }
        } catch(ex: Exception) {
            Log.e(tag, ex.message, ex)
        } finally {
            urlConnection?.disconnect()
        }

        return emptyList()
    }
}