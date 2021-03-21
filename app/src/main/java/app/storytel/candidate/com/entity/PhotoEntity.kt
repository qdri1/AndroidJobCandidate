package app.storytel.candidate.com.entity

import app.storytel.candidate.com.model.PhotoModel
import java.io.Serializable

class PhotoEntity(
        val albumId: Int,
        val id: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String
): Serializable

fun PhotoEntity.toModel(): PhotoModel {
    return PhotoModel(
            albumId = albumId,
            id = id,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl
    )
}