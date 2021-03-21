package app.storytel.candidate.com.entity

import app.storytel.candidate.com.model.PostModel
import java.io.Serializable

class PostEntity(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String
): Serializable

fun PostEntity.toModel(): PostModel {
    return PostModel(
            userId = userId,
            id = id,
            title = title,
            body = body
    )
}