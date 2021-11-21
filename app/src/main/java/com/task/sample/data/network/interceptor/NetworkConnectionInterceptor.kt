package com.task.sample.data.network.interceptor


import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor() : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .build()
        return chain.proceed(request)
    }
}