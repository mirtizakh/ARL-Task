package com.task.sample.ui.products.product_list

import android.view.View
import com.task.sample.R
import com.task.sample.data.network.api_call.ResponseStatus
import com.task.sample.data.repository.IProductRepository
import com.task.sample.ui.base.BaseViewModel
import com.task.sample.util.getViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProductsListFragmentViewModel(
    var productRepository: IProductRepository,
    private val coroutineScopeProvider: CoroutineScope? = null,
) :
    BaseViewModel<ProductListFragmentNavigator>() {
    // region VARIABLES

    // end region VARIABLES

    // region PUBLIC methods
    fun getProductCategories(category: String) {
        getViewModelScope(coroutineScopeProvider).launch {
            getNavigator()?.setVisibilityForProgress(View.VISIBLE)
            val response = productRepository.products(category)
            when (response.status) {
                ResponseStatus.SUCCESS -> {
                    if (response.responseData is ArrayList) {
                        getNavigator()?.setVisibilityForProgress(View.GONE)
                        getNavigator()?.showCategories(response.responseData)
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
    private fun showError(message: Int) {
        getNavigator()?.setVisibilityForProgress(View.GONE)
        getNavigator()?.showError(message)
    }
    // end region PRIVATE methods
}