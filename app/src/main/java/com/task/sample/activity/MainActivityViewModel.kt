package com.task.sample.activity

import androidx.lifecycle.viewModelScope
import com.task.sample.data.db.DatabaseInterface
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainActivityViewModel(private var sessionManager: ISessionManager,
                            private var databaseInterface: DatabaseInterface
) : BaseViewModel<MainActivityNavigator>() {
    // region VARIABLES

    // end region VARIABLES

    // region PUBLIC methods
   // Get product count from database
    fun getProductsCount() {
        viewModelScope.launch {
            val productsCount = databaseInterface.getProductsFromDB()
            getNavigator()?.setProductCountValue(productsCount?.size ?: 0)
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            databaseInterface.deleteAllProducts()
            sessionManager.logoutUser()
            getNavigator()?.logoutSuccessfully()
        }
    }
    // end region PUBLIC methods
}