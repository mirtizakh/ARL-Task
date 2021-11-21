package com.task.sample.app

import android.app.Application
import com.task.sample.activity.MainActivityViewModelFactory
import com.task.sample.data.network.api_call.api_manager.ApiManager
import com.task.sample.data.network.api_call.middle_ware.ResponseMiddleware
import com.task.sample.data.network.api_call.session.SessionManager
import com.task.sample.data.network.interceptor.NetworkConnectionInterceptor
import com.task.sample.data.repository.ProductRepository
import com.task.sample.dialog.DialogManager
import com.task.sample.ui.auth.login.LoginViewModelFactory
import com.task.sample.ui.auth.signup.SignupViewModelFactory
import com.task.sample.ui.cart.CartFragmentViewModelFactory
import com.task.sample.ui.products.categories.ProductCategoriesFragmentViewModelFactory
import com.task.sample.ui.splash.SplashViewModelFactory
import com.task.sample.util.LogExceptions
import com.task.sample.util.Validation
import nye.health.data.network.api_call.network_handler.NetworkAvailable
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
            bind() from singleton { ApiManager(NetworkConnectionInterceptor()) }
            bind() from singleton { LogExceptions() }
            bind() from singleton { NetworkAvailable() }
            bind() from singleton { ResponseMiddleware(instance(), instance()) }
            // ViewModelFactory
            bind() from singleton { SignupViewModelFactory(instance(), instance()) }
            bind() from singleton { LoginViewModelFactory(instance(), instance()) }
            bind() from singleton { SplashViewModelFactory(instance()) }
            bind() from singleton { ProductCategoriesFragmentViewModelFactory(instance()) }
            bind() from singleton { CartFragmentViewModelFactory() }
            bind() from singleton { MainActivityViewModelFactory() }

            // Repository
            bind() from singleton { ProductRepository(instance(), instance()) }

        }

        fun kodein() = kodein
        fun applicationContext(): AppController = instance
    }
}