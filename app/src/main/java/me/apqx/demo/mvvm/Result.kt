package me.apqx.demo.mvvm

import java.lang.Exception

sealed class Result<R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val e: Exception): Result<Nothing>()
}