package app.storytel.candidate.com.task

import java.util.concurrent.ConcurrentHashMap

class TaskHandler {

    private val tasks: ConcurrentHashMap<String, Task> = ConcurrentHashMap()

    fun handle(task: Task) {
        when (task.getStrategy()) {
            Task.Strategy.KeepFirst -> {
                val previousTask = tasks[task.getId()]
                if (previousTask?.getStatus() != Task.Status.InProgress) {
                    execute(task)
                }
            }
            Task.Strategy.KillFirst -> {
                cancel(task)
                execute(task)
            }
        }
    }

    fun cancelAll() {
        tasks.keys.forEach {
            tasks[it]?.cancel()
        }
        tasks.clear()
    }

    private fun execute(task: Task) {
        tasks[task.getId()] = task
        task.execute {
            tasks.remove(task.getId())
        }
    }

    private fun cancel(task: Task) {
        tasks[task.getId()]?.cancel()
        tasks.remove(task.getId())
    }
}