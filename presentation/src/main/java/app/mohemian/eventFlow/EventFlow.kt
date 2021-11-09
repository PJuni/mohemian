package app.mohemian.eventFlow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapNotNull

class EventFlow<T> {

    private val actionSubject: MutableSharedFlow<Event<T>> =
        MutableSharedFlow(replay = 10, extraBufferCapacity = 10)

    suspend fun emit(action: T) {
        actionSubject.emit(Event(action))
    }

    fun getFlow(): Flow<T> = actionSubject.mapNotNull { it.getContentIfNotHandled() }
}