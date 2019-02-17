package com.gallery.data

import com.gallery.io.Subscription

interface AlbumRepository {
    fun getAlbums(): Subscription<List<Album>>
    fun getAlbumPhotos(albumId: Int): Subscription<List<AlbumPhoto>>
}
