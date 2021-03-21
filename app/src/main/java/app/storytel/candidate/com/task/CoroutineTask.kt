package app.storytel.candidate.com.task

import kotlinx.coroutines.*

class CoroutineTask<T>(
        private val id: String,
        private val taskStrategy: Task.Strategy,
        private val function: suspend () -> T,
        private val onSuccess: (T) -> Unit,
        private val onError: (Throwable) -> Unit,
        private val scope: CoroutineScope = GlobalScope
) : Task {

    private var job: Job? = null

    override fun getId(): String {
        return id
    }

    override fun execute(onFinish: () -> Unit) {
        if (job == null) {
            job = scope.launch {
                try {
                    val result = function()
                    withContext(Dispatchers.Main) { onSuccess(result) }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) { onError(e) }
                } finally {
                    withContext(Dispatchers.Main) { onFinish() }
                }
            }
        }
    }

    override fun cancel() {
        job?.cancel(null)
    }

    override fun getStatus(): Task.Status {
        return when {
            job?.isCompleted == true -> Task.Status.Completed
            job?.isActive == true -> Task.Status.InProgress
            job?.isCancelled == true -> Task.Status.Cancelled
            else -> Task.Status.InProgress
        }
    }

    override fun getStrategy(): Task.Strategy {
        return taskStrategy
    }
}