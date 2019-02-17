package com.gallery

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gallery.data.Album
import com.gallery.data.AlbumPhoto
import com.gallery.ui.AlbumsActivityViewModel
import com.gallery.ui.albumphotos.AlbumPhotosFragment
import com.gallery.ui.albumphotos.PhotoFragment
import com.gallery.ui.albums.AlbumsFragment
import com.gallery.ui.events.DataEvent
import com.gallery.ui.events.ShowAlbum
import com.gallery.ui.events.ShowAlbumPhoto

class AlbumsActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.albums_activity)

        viewModel = ViewModelProviders.of(this).get(AlbumsActivityViewModel::class.java)

        viewModel.navigationEvents.observe(this, Observer { evt ->
            if (evt is DataEvent<*>) {
                evt.data?.let { eventData ->
                    when (evt) {
                        is ShowAlbum -> showFragment(AlbumPhotosFragment.newInstance(eventData as Album))
                        is ShowAlbumPhoto -> showFragment(PhotoFragment.newInstance(eventData as AlbumPhoto))
                    }
                }
            }
        })

        if (savedInstanceState == null) {
            showFragment(AlbumsFragment.newInstance())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.container)
        when (f) {
            is AlbumsFragment -> finish()
            else -> supportFragmentManager.popBackStackImmediate()
        }
    }
}
