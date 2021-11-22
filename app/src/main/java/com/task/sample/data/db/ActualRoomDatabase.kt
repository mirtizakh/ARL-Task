package com.task.sample.data.db

import android.content.Context
import androidx.room.Room

class ActualRoomDatabase(var context: Context) : DatabaseConfig {

    override fun getDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            database_name
        )
            .build()
    }
}