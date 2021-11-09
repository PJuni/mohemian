package app.mohemian.data.error

import java.io.IOException

data class NoInternetException(override val cause: Throwable? = null) : IOException()

sealed class RepositoryError : BaseApiException() {

    data class Unknown(
        override val originalException: Throwable? = null
    ) : RepositoryError()
}