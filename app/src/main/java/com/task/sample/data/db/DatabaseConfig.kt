package com.task.sample.data.db

interface DatabaseConfig {
    fun getDatabase(): AppDatabase
}