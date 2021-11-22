package com.task.sample.data.db

import com.task.sample.data.network.response.response_model.Product

class Database(private val database: AppDatabase) : DatabaseInterface {

    // Database interface methods implementation

    override suspend fun saveProductToDB(product: Product) =
        database.getProductDao().insert(product)

    override suspend fun updateProductToDB(product: Product) =
        database.getProductDao().editProduct(product)

    override suspend fun getProductsFromDB() = database.getProductDao().getProducts()

    override suspend fun getProductCount(productId: Int) =
        database.getProductDao().getProductCount(productId)

    override suspend fun deleteProduct(productId: Int) = database.getProductDao().delete(productId)
    override suspend fun deleteAllProducts() = database.getProductDao().deleteAll()
    override suspend fun getTotalPrice() = database.getProductDao().getTotalPrice()

}