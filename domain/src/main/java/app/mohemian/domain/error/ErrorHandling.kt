package app.mohemian.domain.error

import app.mohemian.data.error.RepositoryError

fun Throwable.toRepositoryError(): RepositoryError = when (this) {
    is RepositoryError -> this
    else -> RepositoryError.Unknown(originalException = this)
}

