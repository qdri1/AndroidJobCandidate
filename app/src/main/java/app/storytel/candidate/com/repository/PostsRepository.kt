package app.storytel.candidate.com.repository

import app.storytel.candidate.com.model.CommentModel
import app.storytel.candidate.com.model.PhotoModel
import app.storytel.candidate.com.model.PostModel

interface PostsRepository {

    fun getPosts(): List<PostModel>

    fun getPhotos(): List<PhotoModel>

    fun getComments(postId: Int): List<CommentModel>

}