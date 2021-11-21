package com.task.sample.app

import android.app.Application
import com.task.sample.dialog.DialogManager
import com.task.sample.ui.auth.signup.SignupViewModelFactory
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

            // ViewModelFactory
            bind() from singleton { SignupViewModelFactory() }

        }

        fun kodein() = kodein
        fun applicationContext(): AppController = instance
    }
}