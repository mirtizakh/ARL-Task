package com.task.sample.activity

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.databinding.ActivityMainBinding
import com.task.sample.ui.base.BaseActivity
import com.task.sample.util.LogExceptions
import org.kodein.di.generic.instance

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {
    // region VARIABLES
    override val layoutId: Int = R.layout.activity_main
    override val viewModelFactory: MainActivityViewModelFactory by AppController.kodein().instance()
    override val viewModel = MainActivityViewModel::class.java
    override fun getBindingVariable() = BR._all
    var navController: NavController? = null
    private lateinit var navHost: NavHostFragment
    private val logException: LogExceptions by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE methods
    override fun initUserInterface() {
        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        setupBottomNavigationBar()
    }
    // end region LIFECYCLE

    // region PUBLIC methods
    // This function is used to do navigation between screens
    fun navigate(destinationId: Int) {
        try {
            if (navController != null) {
                navController?.navigate(destinationId)
            }
        } catch (e: Exception) {
            var lineNumber = ""
            Thread.currentThread().stackTrace[2]?.let { t ->
                lineNumber = t.lineNumber.toString()
            }
            logException.saveExceptionErrors(e, this.javaClass.simpleName, lineNumber)
            e.printStackTrace()
        }
    }

    fun setVisibilityOfBottomView(visibility: Int) {
        findViewById<View>(R.id.bottomNavigationView).visibility = visibility
    }
    // end region PUBLIC methods

    // region PRIVATE methods
    private fun setupBottomNavigationBar() {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.products -> {
                    checkNavigationOfBottomView(R.id.productCategoriesFragment)
                    true
                }
                R.id.cart -> {
                    checkNavigationOfBottomView(R.id.cartFragment)
                    true
                }
                else -> {

                    true
                }
            }
        }
    }

    private fun checkNavigationOfBottomView(destinationId: Int) {
        if (navController?.currentDestination?.id == destinationId) {
            return
        } else {
            navigate(destinationId)
        }
    }
    // region PRIVATE methods
}