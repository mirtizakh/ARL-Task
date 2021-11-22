package com.task.sample.data.repository

import com.google.gson.JsonArray
import com.task.sample.data.network.api_call.Resource
import com.task.sample.data.network.api_call.api_manager.ApiManager
import com.task.sample.data.network.api_call.middle_ware.ResponseMiddlewareInterface
import com.task.sample.data.network.response.response_model.Product

interface IProductRepository {
    suspend fun productCategories(): Resource<JsonArray?>
    suspend fun products(category: String): Resource<ArrayList<Product>?>
}

class ProductRepository(
    private val responseMiddleware: ResponseMiddlewareInterface,
    private val apiManager: ApiManager
) : IProductRepository {
    override suspend fun productCategories() = responseMiddleware.networkCall {
        apiManager.productCategories()
    }

    override suspend fun products(category: String) = responseMiddleware.networkCall {
        apiManager.products(category)
    }
}