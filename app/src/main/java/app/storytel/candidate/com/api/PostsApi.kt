package app.storytel.candidate.com.api

import app.storytel.candidate.com.entity.CommentEntity
import app.storytel.candidate.com.entity.PhotoEntity
import app.storytel.candidate.com.entity.PostEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

    @GET("posts")
    fun getPosts(): Response<List<PostEntity>>

    @GET("photos")
    fun getPhotos(): Response<List<PhotoEntity>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Response<List<CommentEntity>>

}