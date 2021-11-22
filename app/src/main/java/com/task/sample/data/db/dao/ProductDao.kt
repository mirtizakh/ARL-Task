package com.task.sample.data.db.dao

import androidx.annotation.NonNull
import androidx.room.*
import com.task.sample.data.db.product_table
import com.task.sample.data.network.response.response_model.Product

@Dao
interface ProductDao {
    @NonNull
    @Query("SELECT * FROM $product_table")
    suspend fun getProducts(): List<Product>

    @NonNull
    @Query("SELECT count FROM $product_table where id = :productId")
    suspend fun getProductCount(productId: Int): Int

    @NonNull
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product): Long

    @NonNull
    @Query("DELETE FROM $product_table where id = :productId")
    suspend fun delete(productId: Int)

    @NonNull
    @Query("DELETE FROM $product_table")
    suspend fun deleteAll()

    @Update
    suspend fun editProduct(product: Product)

    @Query("SELECT SUM(totalPrice) FROM $product_table")
    suspend fun getTotalPrice(): Double
}
