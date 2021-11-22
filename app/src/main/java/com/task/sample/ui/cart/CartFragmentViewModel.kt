package com.task.sample.ui.cart

import android.view.View
import androidx.lifecycle.viewModelScope
import com.task.sample.R
import com.task.sample.data.db.DatabaseInterface
import com.task.sample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CartFragmentViewModel(private var databaseInterface: DatabaseInterface) :
    BaseViewModel<CartFragmentNavigator>() {
    // region VARIABLES

    // end region VARIABLES

    // region PUBLIC methods
    fun getProducts() {
        viewModelScope.launch {
            getNavigator()?.setVisibilityForProgress(View.VISIBLE)
            val productsCount = databaseInterface.getProductsFromDB()
            productsCount?.let {
                getNavigator()?.showCategories(ArrayList(it))
                getNavigator()?.setVisibilityForProgress(View.GONE)
                if(productsCount.size > 0){
                    val totalPrice = databaseInterface.getTotalPrice()
                    totalPrice?.let {
                        getNavigator()?.showTotalPrice(totalPrice)
                    }
                } else {
                    getNavigator()?.showError(R.string.no_cart)
                    getNavigator()?.setVisibilityForProgress(View.GONE)
                }
            } ?: kotlin.run {
                getNavigator()?.showError(R.string.no_cart)
                getNavigator()?.setVisibilityForProgress(View.GONE)
            }
        }
    }
    // end region PUBLIC methods
}