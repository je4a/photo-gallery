package com.gallery.ui.albumphotos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gallery.data.Album
import com.gallery.data.AlbumPhoto
import com.gallery.data.AlbumRepository
import com.gallery.data.AlbumRepositoryImpl
import com.gallery.io.Subscription

class AlbumPhotosViewModel : ViewModel() {
    // ideally, bound with DI into an @Inject constructor
    private val albumRepository: AlbumRepository = AlbumRepositoryImpl()
    private var subscription: Subscription<List<AlbumPhoto>>? = null

    val spansCount: LiveData<Int> by lazy {
        MutableLiveData<Int>().also { it.value = 2 }
    }
    val albumPhotos: LiveData<List<AlbumPhoto>> by lazy {
        MutableLiveData<List<AlbumPhoto>>()
    }

    fun loadAlbumPhotos(album: Album) {
        subscription?.unsubscribe()
        subscription = albumRepository.getAlbumPhotos(album.id).subscribe {
            (albumPhotos as MutableLiveData).postValue(it)
        }
    }
}
