package com.gallery.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gallery.ui.events.Event

class AlbumsActivityViewModel : ViewModel() {
    val navigationEvents: LiveData<Event> = MutableLiveData<Event>()

    fun handleEvent(evt: Event) {
        (navigationEvents as MutableLiveData).postValue(evt)
    }
}
