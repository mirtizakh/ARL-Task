package com.task.sample.ui.products.product_details


import com.task.sample.data.repository.IProductRepository
import com.task.sample.ui.base.BaseViewModel

class ProductsDetailsFragmentViewModel(
    var productRepository: IProductRepository
) :
    BaseViewModel<ProductDetailsNavigator>() {
    // region VARIABLES
    var itemCount: Int = 0
    // end region VARIABLES

    // region PUBLIC methods

    // end region PUBLIC methods
    fun getProductCount() {
        getNavigator()?.setCountValue(itemCount.toString())
    }

    fun addCountValue() {
        itemCount++
        getNavigator()?.setCountValue(itemCount.toString())
    }

    fun minusCountValue() {
        if (itemCount > 0) {
            itemCount--
            getNavigator()?.setCountValue(itemCount.toString())
        }
    }
    // region PRIVATE methods

    // end region PRIVATE methods
}