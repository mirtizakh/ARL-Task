package com.task.sample.data.network.api_call.api_manager

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.task.sample.data.network.response.response_model.Product
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiManager {
    //1-  This api will call on product categories screen
    @GET("products/categories")
    suspend fun productCategories(): Response<JsonArray?>

    @GET("products/category/{category}")
    suspend fun products(@Path("category") category: String): Response<ArrayList<Product>?>

    companion object {
        operator fun invoke(interceptor: Interceptor): ApiManager {
            val gson = GsonBuilder().create()
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .callTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS).build()

            return Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiManager::class.java)
        }
    }
}