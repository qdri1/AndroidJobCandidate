package app.storytel.candidate.com.model

class PostModel(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String
)

fun PostModel.toPostAndPhotoModel(
        photoUrl: String,
        photoThumbnailUrl: String
): PostAndPhotoModel {
    return PostAndPhotoModel(
            postId = id,
            postTitle = title,
            postBody = body,
            photoUrl = photoUrl,
            photoThumbnailUrl = photoThumbnailUrl
    )
}