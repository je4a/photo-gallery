package com.gallery.ui.albums

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gallery.R
import com.gallery.data.Album

class AlbumsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<Album> = emptyList<Album>().toMutableList()
    private val stubAlbum =
        Album(id = 0, userId = 0, title = "STUB!")

    private class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val albumTitle =
            itemView.findViewById<TextView>(R.id.album_title)

        fun bind(album: Album) {
            albumTitle.text = album.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.album_list_item, parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        if (vh is ViewHolder) {
            val album = getItem(position)
            vh.bind(album ?: stubAlbum)
        }
    }

    fun getItem(position: Int): Album? {
        if (position < 0 || position >= items.size) return null
        return items[position]
    }

    fun replaceItems(newAlbums: List<Album>) {
        items.clear()
        items.addAll(newAlbums)
        notifyDataSetChanged()
    }
}
