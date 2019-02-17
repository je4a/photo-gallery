package com.gallery.data

import com.gallery.io.*


class AlbumRepositoryImpl: AlbumRepository {
    override fun getAlbumPhotos(albumId: Int): Subscription<List<AlbumPhoto>> {
        return SubscriptionImpl(ApiTask(AlbumPhotosApiCaller(albumId)))
    }

    override fun getAlbums(): Subscription<List<Album>> {
        return SubscriptionImpl(ApiTask(AlbumsListApiCaller()))
    }
}