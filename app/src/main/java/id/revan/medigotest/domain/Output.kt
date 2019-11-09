package id.revan.medigotest.domain

sealed class Output<T> {
    data class Success<T>(val output: T) : Output<T>()
    data class Error<T>(val code: Int, val message: String) : Output<T>()
}