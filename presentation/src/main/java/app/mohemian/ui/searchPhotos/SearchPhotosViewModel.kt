package app.mohemian.ui.searchPhotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mohemian.domain.entity.Photo
import app.mohemian.domain.result.doOnError
import app.mohemian.domain.result.doOnSuccess
import app.mohemian.domain.useCase.FetchPhotosUseCase
import app.mohemian.domain.useCase.base.UseCaseParam
import app.mohemian.eventFlow.EventFlow
import app.mohemian.extensions.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    private val actionSubject: EventFlow<Action> = EventFlow()
    val actionStream: EventFlow<Action> = actionSubject
    private val commands: EventFlow<Command> = EventFlow()

    init {
        viewModelScope.launch {
            commands.getFlow().collect { command ->
                return@collect when (command) {
                    is Command.ShowPhotoDetails -> showPhotoDetails(command.photo)
                    is Command.FetchPhotos -> fetchPhotos(command.text ?: "dog")
                }
            }
        }
    }

    fun triggerCommand(command: Command) = viewModelScope.launch {
        commands.emit(command)
    }

    private fun fetchPhotos(text: String) {
        executeUseCase(
            fetchPhotosUseCase,
            UseCaseParam.Flickr.SearchPhoto(text)
        ) {
            Timber.i("Trying to fetch photos.")
            doOnSuccess {
                Timber.i("Successfully fetched photos: $it")
                Action.PhotoFetched(it.photosPage.photos).send()
            }
            doOnError { Timber.e("Failed to fetch photos: $it") }
        }
    }

    private fun showPhotoDetails(photo: Photo) = Action.ShowPhotoDetails(photo).send()

    private fun Action.send() {
        Timber.i("Sending action: $this")
        viewModelScope.launch {
            actionSubject.emit(this@send)
        }
    }

    sealed class Command {
        data class ShowPhotoDetails(val photo: Photo) : Command()
        data class FetchPhotos(val text: String? = "dog") : Command()
    }

    sealed class Action {
        data class ShowPhotoDetails(val photo: Photo) : Action()
        data class PhotoFetched(val photos: List<Photo>) : Action()
    }
}