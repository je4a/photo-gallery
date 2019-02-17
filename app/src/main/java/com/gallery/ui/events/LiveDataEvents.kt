package com.gallery.ui.events

import com.gallery.data.Album
import com.gallery.data.AlbumPhoto


open class Event
open class DataEvent<T>(val data: T?) : Event()
class ShowAlbum(album: Album) : DataEvent<Album>(album)
class ShowAlbumPhoto(photo: AlbumPhoto) : DataEvent<AlbumPhoto>(photo)
