package com.task.sample.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.databinding.ActivityMainBinding
import com.task.sample.databinding.BadgeViewBinding
import com.task.sample.dialog.DialogManager
import com.task.sample.ui.base.BaseActivity
import com.task.sample.util.LogExceptions
import org.kodein.di.generic.instance

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() , MainActivityNavigator {
    // region VARIABLES
    override val layoutId: Int = R.layout.activity_main
    override val viewModelFactory: MainActivityViewModelFactory by AppController.kodein().instance()
    private val dialogManager: DialogManager by AppController.kodein().instance()
    override val viewModel = MainActivityViewModel::class.java
    override fun getBindingVariable() = BR._all
    var navController: NavController? = null
    private lateinit var navHost: NavHostFragment
    private val logException: LogExceptions by AppController.kodein().instance()
    private var notificationsBadge: BadgeViewBinding? = null
    // end region VARIABLES

    // region OVERRIDE methods
    override fun initUserInterface() {
        injectedViewModel.setNavigator(this)
        navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        setupBottomNavigationBar()
        injectedViewModel.getProductsCount()
    }

    override fun logoutSuccessfully() {
        navigate(R.id.splashFragment,  popBackstack = true)
    }

    override fun setProductCountValue(count: Int) {
        updateBadgeForCart(count)
    }
    // end region OVERRIDE methods

    // region PUBLIC methods
    // This function is used to do navigation between screens
    fun navigate(destinationId: Int, bundle: Bundle? = null, popBackstack: Boolean = false) {
        try {
            navController?.currentDestination?.id?.let {
                if (navController?.currentDestination?.id == destinationId) {
                    return
                }
            }
            if (popBackstack) navController?.popBackStack(R.id.nav_graph, true)
            if (bundle != null) navController?.navigate(
                destinationId, bundle
            )
            else navController?.navigate(destinationId)
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

    fun updateBadgeForCart(count: Int) {
            if (notificationsBadge == null) {
                val bottomNavigationMenuView =
                    viewDataBinding.bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
                val item = bottomNavigationMenuView.getChildAt(0) as BottomNavigationItemView
                notificationsBadge =
                    DataBindingUtil.inflate(layoutInflater, R.layout.badge_view, item, false)
                item.addView(notificationsBadge?.root)
            }
            notificationsBadge?.textViewNotificationsBadge?.text = count.toString()
        if (count == 0) {
            notificationsBadge?.root?.visibility = View.GONE
        } else {
            notificationsBadge?.root?.visibility = View.VISIBLE
        }
    }
    // end region PUBLIC methods

    // region PRIVATE methods
    private fun setupBottomNavigationBar() {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.products -> {
                    navigate(R.id.productCategoriesFragment,popBackstack = true)
                    true
                }
                R.id.cart -> {
                    navigate(R.id.cartFragment,popBackstack = true)
                    true
                }
                else -> {
                    showLogoutDialog()
                    true
                }
            }
        }
    }
    private fun showLogoutDialog(){
        dialogManager.twoButtonDialog(this,getString(R.string.logout_title),
            getString(R.string.logout_message) , getString(R.string.ok) , getString(R.string.cancel),
            object :DialogManager.AlertDialogListener{
                override fun onPositiveButtonClicked(){
                    injectedViewModel.logoutUser()
                }
            })

    }
    // region PRIVATE methods
}