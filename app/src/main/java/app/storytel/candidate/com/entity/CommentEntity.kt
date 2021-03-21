package app.storytel.candidate.com.entity

import app.storytel.candidate.com.model.CommentModel
import java.io.Serializable

class CommentEntity(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
) : Serializable

fun CommentEntity.toModel(): CommentModel {
    return CommentModel(
            postId = postId,
            id = id,
            name = name,
            email = email,
            body = body
    )
}