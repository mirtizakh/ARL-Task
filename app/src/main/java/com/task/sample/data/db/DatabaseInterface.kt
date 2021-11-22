package com.task.sample.data.db

import com.task.sample.data.network.response.response_model.Product

interface DatabaseInterface {
    suspend fun saveProductToDB(product: Product): Long
    suspend fun updateProductToDB(product: Product)
    suspend fun getProductsFromDB(): List<Product>?
    suspend fun getProductCount(productId: Int): Int?
    suspend fun deleteProduct(productId: Int)
    suspend fun deleteAllProducts()
}