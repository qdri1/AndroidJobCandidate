package app.storytel.candidate.com.repository

import app.storytel.candidate.com.api.PostsApi
import app.storytel.candidate.com.entity.CommentEntity
import app.storytel.candidate.com.entity.PhotoEntity
import app.storytel.candidate.com.entity.PostEntity
import app.storytel.candidate.com.entity.toModel
import app.storytel.candidate.com.model.CommentModel
import app.storytel.candidate.com.model.PhotoModel
import app.storytel.candidate.com.model.PostModel

class PostRepositoryImpl(
        private val api: PostsApi
) : PostsRepository {

    override fun getPosts(): List<PostModel> {
        return api.getPosts().body()?.map(PostEntity::toModel) ?: emptyList()
    }

    override fun getPhotos(): List<PhotoModel> {
        return api.getPhotos().body()?.map(PhotoEntity::toModel) ?: emptyList()
    }

    override fun getComments(postId: Int): List<CommentModel> {
        return api.getComments(postId).body()?.map(CommentEntity::toModel) ?: emptyList()
    }

}