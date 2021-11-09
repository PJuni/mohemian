package app.mohemian.data.error

abstract class BaseApiException : Exception() {
    abstract val originalException: Throwable?
}