package app.storytel.candidate.com.mvvm.abstract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.storytel.candidate.com.task.TaskHandler

abstract class AbstractViewModel: ViewModel() {

    protected val taskHandler = TaskHandler()
    protected val isLoading = MutableLiveData<Boolean>()
    protected val errorHandler = MutableLiveData<Throwable>()

    fun isLoading(): LiveData<Boolean> = isLoading
    fun getErrorHandler(): LiveData<Throwable> = errorHandler

    override fun onCleared() {
        super.onCleared()
        taskHandler.cancelAll()
    }

}