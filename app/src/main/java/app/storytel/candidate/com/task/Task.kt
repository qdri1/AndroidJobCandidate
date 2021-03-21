package app.storytel.candidate.com.task

interface Task {

    sealed class Status {
        object InProgress : Status()
        object Completed : Status()
        object Cancelled : Status()
    }

    sealed class Strategy {
        object KeepFirst : Strategy()
        object KillFirst : Strategy()
    }

    fun getId(): String

    fun execute(onFinish: () -> Unit)

    fun cancel()

    fun getStatus(): Status

    fun getStrategy(): Strategy
}