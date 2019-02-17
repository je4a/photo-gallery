package com.gallery.data

import android.os.Parcel
import android.os.Parcelable

data class AlbumPhoto(
    val id: Int,
    val albumId: Int,
    val url: String,
    val thumbnail: String,
    val title: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(albumId)
        writeString(url)
        writeString(thumbnail)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AlbumPhoto> =
            object : Parcelable.Creator<AlbumPhoto> {
                override fun createFromParcel(source: Parcel): AlbumPhoto =
                    AlbumPhoto(source)

                override fun newArray(size: Int): Array<AlbumPhoto?> =
                    arrayOfNulls(size)
            }
    }
}