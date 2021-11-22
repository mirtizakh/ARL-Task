package com.task.sample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.sample.data.db.dao.ProductDao
import com.task.sample.data.network.response.response_model.Product

const val product_table = "productTable"
const val database_name = "products"

@Database(entities = [Product::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(database: DatabaseConfig) = instance ?: synchronized(LOCK)
        {
            instance ?: database.getDatabase().also { instance = it }
        }
    }
}