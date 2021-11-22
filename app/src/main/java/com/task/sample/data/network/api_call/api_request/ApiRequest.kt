package com.task.sample.data.network.api_call.api_request


import retrofit2.Response


// Calling Apis in this class
abstract class ApiRequest {

    suspend fun <T> apiRequest(call: suspend () -> Response<T>): Response<T> {
        return call.invoke()
    }
}