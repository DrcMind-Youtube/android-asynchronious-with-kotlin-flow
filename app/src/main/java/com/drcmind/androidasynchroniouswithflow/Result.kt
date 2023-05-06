package com.drcmind.androidasynchroniouswithflow

sealed  class Result<T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data : T) : Result<T>()
}
