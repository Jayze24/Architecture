package space.jay.cleanarchitecture.base

sealed class WrapperResult<out T> {
    data class Success<out T>(val value: T) : WrapperResult<T>()
    data class Error(val throwable: Throwable) : WrapperResult<Nothing>()
}
