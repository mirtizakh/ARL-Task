package com.task.mainactivity.app

import android.app.Application
import com.task.mainactivity.dialog.DialogManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AppController : Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AppController

        val kodein = Kodein.lazy {
            bind() from singleton { DialogManager() }

        }

        fun kodein() = kodein
        fun applicationContext(): AppController = instance
    }
}