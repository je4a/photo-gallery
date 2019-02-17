package com.gallery.ui.albumphotos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gallery.R
import com.gallery.data.AlbumPhoto
import com.gallery.io.ImageLoader

class AlbumPhotosAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<AlbumPhoto> =
        emptyList<AlbumPhoto>().toMutableList()

    private inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val thumbnail = itemView.findViewById<ImageView>(R.id.photo_thumb)
        private val title = itemView.findViewById<TextView>(R.id.photo_title)

        fun bind(photo: AlbumPhoto) {
            title.text = photo.title
            imageLoader.load(photo.thumbnail, thumbnail)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.album_photo_list_item, parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        val photo = getItem(position) ?: return

        if (vh is ViewHolder) {
            vh.bind(photo)
        }
    }

    fun getItem(position: Int): AlbumPhoto? {
        if (position < 0 || position >= items.size) return null
        return items[position]
    }

    fun replaceItems(newPhotos: List<AlbumPhoto>) {
        items.clear()
        items.addAll(newPhotos)
        notifyDataSetChanged()
    }
}
