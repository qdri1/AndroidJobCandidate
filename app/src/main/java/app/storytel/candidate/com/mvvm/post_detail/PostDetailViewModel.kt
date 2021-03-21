package app.storytel.candidate.com.mvvm.post_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.storytel.candidate.com.api.PostsApiClient
import app.storytel.candidate.com.model.CommentModel
import app.storytel.candidate.com.model.PostAndPhotoModel
import app.storytel.candidate.com.mvvm.abstract.AbstractViewModel
import app.storytel.candidate.com.repository.PostRepositoryImpl
import app.storytel.candidate.com.repository.PostsRepository
import app.storytel.candidate.com.task.CoroutineTask
import app.storytel.candidate.com.task.Task

class PostDetailViewModel : AbstractViewModel() {

    lateinit var post: PostAndPhotoModel
    private val postsRepository: PostsRepository by lazy {
        PostRepositoryImpl(PostsApiClient.create())
    }
    private val comments = MutableLiveData<List<CommentModel>>()

    fun getComments(): LiveData<List<CommentModel>> = comments

    fun fetchComments() {
        isLoading.value = true
        val fetchComments = CoroutineTask(
                id = "fetchComments",
                taskStrategy = Task.Strategy.KillFirst,
                function = {
                    postsRepository.getComments(post.postId)
                },
                onSuccess = { list ->
                    isLoading.value = false
                    comments.value = list
                },
                onError = { error ->
                    isLoading.value = false
                    errorHandler.value = error
                }
        )
        taskHandler.handle(fetchComments)
    }

}