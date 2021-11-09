package app.mohemian.domain.useCase.base

import app.mohemian.domain.result.RepositoryResult

interface UseCaseHandler<UseCaseParam, T> {

    suspend fun execute(param: UseCaseParam): RepositoryResult<T> = RepositoryResult.Any
    suspend fun execute(): RepositoryResult<T> = RepositoryResult.Any
}