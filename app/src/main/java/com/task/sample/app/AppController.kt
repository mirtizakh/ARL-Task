package com.task.sample.app

import android.app.Application
import com.task.sample.data.network.api_call.session.SessionManager
import com.task.sample.dialog.DialogManager
import com.task.sample.ui.auth.login.LoginViewModelFactory
import com.task.sample.ui.auth.signup.SignupViewModelFactory
import com.task.sample.util.LogExceptions
import com.task.sample.util.Validation
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class AppController : Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AppController

        private val kodein = Kodein.lazy {
            bind() from singleton { DialogManager() }
            bind() from singleton { Validation() }
            bind() from singleton { SessionManager() }
            bind() from singleton { LogExceptions() }

            // ViewModelFactory
            bind() from singleton { SignupViewModelFactory(instance(), instance()) }
            bind() from singleton { LoginViewModelFactory(instance(), instance()) }

        }

        fun kodein() = kodein
        fun applicationContext(): AppController = instance
    }
}