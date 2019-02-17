package com.gallery.ui.albums

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
import com.gallery.ui.AlbumsActivityViewModel
import com.gallery.ui.SpacingDecoration
import com.gallery.ui.events.ShowAlbum
import kotlinx.android.synthetic.main.albums_fragment.*

class AlbumsFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumsFragment()
    }

    private lateinit var parentViewModel: AlbumsActivityViewModel
    private lateinit var viewModel: AlbumsViewModel
    private lateinit var layoutManager: GridLayoutManager

    private val albumsAdapter = AlbumsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parentViewModel = ViewModelProviders.of(activity!!).get(AlbumsActivityViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)

        viewModel.spansCount.observe(this, Observer { spansCount ->
            setupRecyclerView(spansCount)
        })

        viewModel.albumsList.observe(this, Observer {
            (albumsAdapter::replaceItems)(it ?: emptyList())
        })
    }

    private fun setupRecyclerView(spansCount: Int?) {
        layoutManager = GridLayoutManager(activity, spansCount ?: 1)
        albums_list.layoutManager = layoutManager
        albums_list.adapter = albumsAdapter

        albums_list.addItemDecoration(
            SpacingDecoration(resources.getDimensionPixelOffset(R.dimen.list_item_spacing))
        )

        val itemClickListener = View.OnClickListener { view ->
            val position = albums_list.getChildViewHolder(view).adapterPosition
            val album = albumsAdapter.getItem(position)

            album?.let {
                parentViewModel.handleEvent(ShowAlbum(it))
            }
        }

        val attachStateListener = object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener(itemClickListener)
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                //do nothing
            }
        }
        albums_list.addOnChildAttachStateChangeListener(attachStateListener)
    }
}
