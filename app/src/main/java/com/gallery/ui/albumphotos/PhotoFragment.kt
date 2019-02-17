package com.gallery.ui.albumphotos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallery.R
import com.gallery.data.AlbumPhoto
import com.gallery.io.ImageLoader
import kotlinx.android.synthetic.main.photo_fragment.*

private var Bundle.photo: AlbumPhoto?
    get() = this.getParcelable("photo")
    set(value) {
        this.putParcelable("photo", value)
    }

class PhotoFragment : Fragment() {

    private lateinit var imageLoader: ImageLoader

    companion object {
        fun newInstance(albumPhoto: AlbumPhoto): PhotoFragment {
            return PhotoFragment().apply {
                arguments = Bundle().apply {
                    photo = albumPhoto
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageLoader = ImageLoader()
        lifecycle.addObserver(imageLoader)

        arguments?.photo?.let {
            imageLoader.load(it.url, photo)
            photo_title.text = it.title
        }
    }
}
