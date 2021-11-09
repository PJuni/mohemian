package app.mohemian.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mohemian.eventFlow.EventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val actionSubject: EventFlow<Action> = EventFlow()
    val actionStream: EventFlow<Action> = actionSubject
    private val commands: EventFlow<Command> = EventFlow()

    init {
        viewModelScope.launch {
            commands.getFlow().collect { command ->
                return@collect when (command) {
                    is Command.SetTitle -> setTitle(command.title)
                    Command.ShowTitle -> showTitle()
                    Command.SetMainTheme -> setMainTheme()
                }
            }
        }
    }

    private fun setTitle(title: String) = Action.SetTitle(title).send()
    private fun showTitle() = Action.ShowTitle.send()
    private fun setMainTheme() = Action.SetMainTheme.send()

    fun triggerCommand(command: Command) = viewModelScope.launch {
        commands.emit(command)
    }

    private fun Action.send() {
        Timber.i("Sending action: $this")
        viewModelScope.launch {
            actionSubject.emit(this@send)
        }
    }

    sealed class Command {
        data class SetTitle(val title: String): Command()
        object ShowTitle: Command()
        object SetMainTheme: Command()
    }

    sealed class Action {
        data class SetTitle(val title: String) : Action()
        object ShowTitle: Action()
        object SetMainTheme: Action()
    }
}