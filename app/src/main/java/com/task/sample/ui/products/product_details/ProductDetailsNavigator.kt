package com.task.sample.ui.products.product_details

import com.task.sample.ui.base.BaseNavigator

interface ProductDetailsNavigator : BaseNavigator {
    fun showError(error: Int)
    fun setCountValue(count: String)
    fun setProductCountValue(count: Int)
}