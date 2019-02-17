package com.gallery.ui.albumphotos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallery.R
import com.gallery.data.Album
import com.gallery.io.ImageLoader
import com.gallery.io.ImageLoaderTask
import com.gallery.ui.AlbumsActivityViewModel
import com.gallery.ui.SpacingDecoration
import com.gallery.ui.events.ShowAlbumPhoto
import kotlinx.android.synthetic.main.album_photos_fragment.*

private var Bundle.album: Album?
    get() = this.getParcelable("album")
    set(value) {
        this.putParcelable("album", value)
    }

class AlbumPhotosFragment : Fragment() {

    companion object {
        fun newInstance(album: Album): AlbumPhotosFragment {
            return AlbumPhotosFragment().apply {
                arguments = Bundle().apply {
                    this.album = album
                }
            }
        }
    }

    private lateinit var parentViewModel: AlbumsActivityViewModel
    private lateinit var viewModel: AlbumPhotosViewModel
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: AlbumPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.album_photos_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imageLoader = ImageLoader()
        lifecycle.addObserver(imageLoader)
        adapter = AlbumPhotosAdapter(imageLoader)

        viewModel = ViewModelProviders.of(this)
            .get(AlbumPhotosViewModel::class.java)
        parentViewModel = ViewModelProviders.of(activity!!)
            .get(AlbumsActivityViewModel::class.java)

        viewModel.albumPhotos.observe(this, Observer {
            val list = it ?: return@Observer
            adapter.replaceItems(list)
        })

        viewModel.spansCount.observe(this, Observer {
            val value = it ?: return@Observer

            layoutManager = GridLayoutManager(activity, value)
            album_photos.addItemDecoration(
                SpacingDecoration(
                    resources.getDimensionPixelOffset(
                        R.dimen.list_item_spacing
                    )
                )
            )
            album_photos.layoutManager = layoutManager
            album_photos.adapter = adapter

            val itemClickListener = View.OnClickListener { view ->
                adapter.getItem(album_photos.getChildAdapterPosition(view))
                    ?.let {
                        parentViewModel.handleEvent(ShowAlbumPhoto(it))
                    }
            }
            val attachStateListener =
                object : RecyclerView.OnChildAttachStateChangeListener {
                    override fun onChildViewAttachedToWindow(view: View) {
                        view.setOnClickListener(itemClickListener)
                    }

                    override fun onChildViewDetachedFromWindow(view: View) {
                        //do nothing
                    }
                }

            album_photos.addOnChildAttachStateChangeListener(attachStateListener)
        })

        arguments?.album?.let { viewModel.loadAlbumPhotos(it) }
    }
}
