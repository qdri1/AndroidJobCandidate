package app.storytel.candidate.com.mvvm.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.storytel.candidate.com.api.PostsApiClient
import app.storytel.candidate.com.model.PostAndPhotoModel
import app.storytel.candidate.com.mvvm.abstract.AbstractViewModel
import app.storytel.candidate.com.repository.PostRepositoryImpl
import app.storytel.candidate.com.repository.PostsRepository
import app.storytel.candidate.com.task.CoroutineTask
import app.storytel.candidate.com.task.Task
import app.storytel.candidate.com.use_case.PostsUseCase

class PostsViewModel : AbstractViewModel() {

    private val postRepository: PostsRepository by lazy {
        PostRepositoryImpl(PostsApiClient.create())
    }
    private val postsUseCase by lazy {
        PostsUseCase(postRepository)
    }
    private val posts = MutableLiveData<List<PostAndPhotoModel>>()

    fun getPosts(): LiveData<List<PostAndPhotoModel>> = posts

    fun fetchPosts() {
        isLoading.value = true
        val fetchPosts = CoroutineTask(
                id = "fetchPosts",
                taskStrategy = Task.Strategy.KillFirst,
                function = {
                    postsUseCase.execute()
                },
                onSuccess = { list ->
                    isLoading.value = false
                    posts.value = list
                },
                onError = { error ->
                    isLoading.value = false
                    errorHandler.value = error
                }
        )
        taskHandler.handle(fetchPosts)
    }

}