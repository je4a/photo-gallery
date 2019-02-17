package com.gallery.data

import android.os.Parcel
import android.os.Parcelable

data class Album(val id: Int, val userId: Int, val title: String) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(userId)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Album> =
            object : Parcelable.Creator<Album> {
                override fun createFromParcel(source: Parcel): Album =
                    Album(source)

                override fun newArray(size: Int): Array<Album?> =
                    arrayOfNulls(size)
            }
    }
}
