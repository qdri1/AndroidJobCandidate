package app.storytel.candidate.com.model

import java.io.Serializable

data class PostAndPhotoModel(
        val postId: Int,
        val postTitle: String,
        val postBody: String,
        val photoUrl: String,
        val photoThumbnailUrl: String
): Serializable

