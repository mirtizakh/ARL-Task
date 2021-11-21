package com.task.sample.ui.products.categories

import android.view.View
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.task.sample.R
import com.task.sample.data.network.api_call.ResponseStatus
import com.task.sample.data.repository.IProductRepository
import com.task.sample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductCategoriesFragmentViewModel(var productRepository: IProductRepository) :
    BaseViewModel<ProductCategoriesFragmentNavigator>() {
    // region VARIABLES

    // end region VARIABLES

    // region PUBLIC methods
    fun getProductCategories() {
        viewModelScope.launch {
            getNavigator()?.setVisibilityForProgress(View.VISIBLE)
            val response = productRepository.productCategories()
            when (response.status) {
                ResponseStatus.SUCCESS -> {
                    if (response.responseData is JsonArray) {
                        getNavigator()?.setVisibilityForProgress(View.GONE)
                    } else {
                        showError(R.string.something_went_wrong)
                    }
                }
                ResponseStatus.NETWORK_ERROR -> {
                    showError(R.string.network_error)
                }
                else -> {
                    showError(R.string.something_went_wrong)
                }
            }
        }
    }
    // end region PUBLIC methods

    // region PRIVATE methods
    private fun showError(message:Int) {
        getNavigator()?.setVisibilityForProgress(View.GONE)
        getNavigator()?.showError(message)
    }
    // end region PRIVATE methods
}