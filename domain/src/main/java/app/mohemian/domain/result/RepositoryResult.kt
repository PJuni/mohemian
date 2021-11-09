package app.mohemian.domain.result

import app.mohemian.data.error.RepositoryError
import app.mohemian.domain.error.toRepositoryError

sealed class RepositoryResult<out T> {

    data class Success<out T>(val data: T) : RepositoryResult<T>()
    data class Failure(val error: RepositoryError) : RepositoryResult<Nothing>()
    object Any : RepositoryResult<Nothing>()
}

inline infix fun <T> RepositoryResult<T>.mapError(apply: (RepositoryError) -> RepositoryError): RepositoryResult<T> =
    when (this) {
        is RepositoryResult.Failure -> RepositoryResult.Failure(apply(error))
        is RepositoryResult.Success -> this
        is RepositoryResult.Any -> this
    }

inline infix fun <T, R> RepositoryResult<T>.map(apply: (T) -> R): RepositoryResult<R> = when (this) {
    is RepositoryResult.Failure -> this
    is RepositoryResult.Success -> RepositoryResult.Success(apply(data))
    is RepositoryResult.Any -> this
}

inline infix fun <T, R> RepositoryResult<T>.flatMap(apply: (T) -> RepositoryResult<R>): RepositoryResult<R> =
    when (this) {
        is RepositoryResult.Failure -> this
        is RepositoryResult.Success -> apply(data)
        is RepositoryResult.Any -> this
    }

inline infix fun <T> RepositoryResult<T>.doOnSuccess(apply: (T) -> Unit): RepositoryResult<T> {
    if (this is RepositoryResult.Success) {
        apply(data)
    }
    return this
}

inline infix fun <T> RepositoryResult<T>.doOnError(apply: (RepositoryError) -> Unit): RepositoryResult<T> {
    if (this is RepositoryResult.Failure) {
        apply(error)
    }
    return this
}

inline infix fun <T> RepositoryResult<T>.doOnFinal(apply: () -> Unit): RepositoryResult<T> {
    apply()
    return this
}

infix fun <T, R> RepositoryResult<(T) -> R>.apply(apply: RepositoryResult<T>): RepositoryResult<R> = when (this) {
    is RepositoryResult.Failure -> this
    is RepositoryResult.Success -> apply.map(this.data)
    is RepositoryResult.Any -> this
}

inline fun <T, reified A> RepositoryResult<T>.fold(
    error: (RepositoryError) -> A = { this as A },
    success: (T) -> A = { this as A },
    any: () -> A = { this as A }
): A = when (this) {
    is RepositoryResult.Failure -> error(this.error)
    is RepositoryResult.Success -> success(this.data)
    is RepositoryResult.Any -> any()
}

fun <T> success(data: T) = RepositoryResult.Success(data)

fun failure(repositoryError: RepositoryError) = RepositoryResult.Failure(repositoryError)

inline fun <T> safeCall(call: () -> T): RepositoryResult<T> = try {
    success(call.invoke())
} catch (exception: Exception) {
    failure(exception.toRepositoryError())
}