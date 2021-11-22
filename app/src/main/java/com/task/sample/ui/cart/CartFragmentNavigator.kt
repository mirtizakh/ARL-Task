package com.task.sample.ui.cart

import com.task.sample.data.network.response.response_model.Product
import com.task.sample.ui.base.BaseNavigator

interface CartFragmentNavigator : BaseNavigator {
    fun showCategories(list: ArrayList<Product>)
    fun showError(error: Int)
    fun showTotalPrice(price: Double)
}