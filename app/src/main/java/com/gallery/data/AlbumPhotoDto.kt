package com.gallery.data


data class AlbumPhotoDto(
    val id: Int,
    val albumId: Int,
    val url: String,
    val thumbnail: String,
    val title: String
)