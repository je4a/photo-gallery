package com.gallery.data

import org.json.JSONArray
import org.json.JSONObject

fun List<AlbumDto>.toAlbumList(): List<Album> {
    return map { dto -> dto.toAlbum() }
}

fun AlbumDto.toAlbum(): Album {
    return Album(id = id, title = title, userId = userId)
}

fun JSONArray.toAlbumDtoList(): List<AlbumDto> {
    val list = emptyList<AlbumDto>().toMutableList()

    for (i in 0 until length()) {
        list.add(getJSONObject(i).toAlbumDto())
    }
    return list
}

fun JSONObject.toAlbumDto(): AlbumDto {
    return AlbumDto(
        id = getInt("id"), userId = getInt("userId"),
        title = getString("title")
    )
}