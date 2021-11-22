package com.task.sample.data.network.api_call.middle_ware

import com.task.sample.data.network.api_call.Resource
import com.task.sample.data.network.response.response_parser.ResponseCodes
import com.task.sample.data.network.response.response_parser.ResponseMessages
import com.task.sample.util.ILogException
import com.task.sample.util.empty
import com.task.sample.data.network.api_call.api_request.ApiRequest
import com.task.sample.data.network.api_call.network_handler.NetworkAvailableInterface
import retrofit2.Response

interface ResponseMiddlewareInterface {
    suspend fun <T> networkCall(call: suspend () -> Response<T>): Resource<T>
    suspend fun <T> parseApiResponse(response: Response<T>?): Resource<T>
}

class ResponseMiddleware(
    private var networkAvailableInterface: NetworkAvailableInterface,
    private var logException: ILogException
) : ApiRequest(), ResponseMiddlewareInterface {

    override suspend fun <T> networkCall(call: suspend () -> Response<T>): Resource<T> {
        if (networkAvailableInterface.isInternetIsAvailable()) {
            var response: Response<T>? = null
            try {
                response = apiRequest { call() }
            } catch (e: Exception) {
                return crashRecord(e)
            }
            return parseApiResponse(response)
        } else return Resource.networkError()
    }

    override suspend fun <T> parseApiResponse(response: Response<T>?): Resource<T> {
        response?.let {
            return when (response.code()) {
                ResponseCodes.SUCCESS_CODE_200.code -> Resource.success(data = response.body())
                else -> Resource.error(ResponseMessages.SOME_THING_WENT_WRONG.message)
            }
        } ?: kotlin.run {
            return Resource.error(ResponseMessages.SOME_THING_WENT_WRONG.message)
        }
    }

    private fun <T> crashRecord(exceptions: Exception): Resource<T> {
        logException.saveExceptionErrors(exceptions, this.javaClass.simpleName, String.empty)
        return Resource.error(ResponseMessages.SOME_THING_WENT_WRONG.message)
    }
}