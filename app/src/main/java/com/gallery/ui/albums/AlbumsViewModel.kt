package com.gallery.ui.albums

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gallery.data.Album
import com.gallery.data.AlbumRepository
import com.gallery.data.AlbumRepositoryImpl
import com.gallery.io.Subscription

class AlbumsViewModel : ViewModel() {
    // ideally, bound with DI into an @Inject constructor
    private val albumRepository: AlbumRepository =
        AlbumRepositoryImpl()
    private var albumsSubscription: Subscription<List<Album>>? = null

    val spansCount: LiveData<Int> by lazy {
        MutableLiveData<Int>().also { it.value = 1 }
    }
    val albumsList: LiveData<List<Album>> by lazy {
        MutableLiveData<List<Album>>().also { forceLoadAlbums() }
    }

    fun forceLoadAlbums() {
        albumsSubscription?.unsubscribe()
        albumsSubscription = albumRepository.getAlbums().subscribe {
            (albumsList as MutableLiveData).postValue(it)
        }
    }
}
