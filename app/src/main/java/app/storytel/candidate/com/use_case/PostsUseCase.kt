package app.storytel.candidate.com.use_case

import app.storytel.candidate.com.model.PostAndPhotoModel
import app.storytel.candidate.com.model.toPostAndPhotoModel
import app.storytel.candidate.com.repository.PostsRepository

class PostsUseCase(private val postsRepository: PostsRepository) {

    fun execute(): List<PostAndPhotoModel> {
        val posts = postsRepository.getPosts()
        val photos = postsRepository.getPhotos()
        return posts.mapIndexed { index, postModel ->
            postModel.toPostAndPhotoModel(
                    photoUrl = photos[index].url,
                    photoThumbnailUrl = photos[index].thumbnailUrl
            )
        }
    }

}