package app.mohemian.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mohemian.domain.result.RepositoryResult
import app.mohemian.domain.useCase.base.UseCaseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T, UseCaseParam> ViewModel.executeUseCase(
    useCase: UseCaseHandler<UseCaseParam, T>,
    params: UseCaseParam? = null,
    useCaseCall: suspend RepositoryResult<T>.() -> Unit
) = viewModelScope.launch(Dispatchers.IO) {

    val useCaseResult = if (params == null) useCase.execute()
    else useCase.execute(params)

    useCaseCall.invoke(useCaseResult)
}