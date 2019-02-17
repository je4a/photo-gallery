package com.gallery.data

import org.json.JSONArray
import org.json.JSONObject

fun List<AlbumPhotoDto>.toAlbumPhotoList(): List<AlbumPhoto> {
    return map { dto -> dto.toAlbumPhoto() }
}

fun AlbumPhotoDto.toAlbumPhoto(): AlbumPhoto {
    return AlbumPhoto(
        id = id, albumId = albumId, thumbnail = thumbnail, url = url, title = title
    )
}

fun JSONArray.toAlbumPhotoDtoList(): List<AlbumPhotoDto> {
    val list = emptyList<AlbumPhotoDto>().toMutableList()

    for (i in 0 until length()) {
        list.add(getJSONObject(i).toAlbumPhotoDto())
    }
    return list
}

fun JSONObject.toAlbumPhotoDto(): AlbumPhotoDto {
    return AlbumPhotoDto(
        id = getInt("id"), albumId = getInt("albumId"),
        url = getString("url"), thumbnail = getString("thumbnailUrl"),
        title = getString("title")
    )
}
