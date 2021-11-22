package com.task.sample.ui.products.product_list

import com.task.sample.data.network.response.response_model.Product
import com.task.sample.ui.base.BaseNavigator

interface ProductListFragmentNavigator : BaseNavigator {
    fun showError(error: Int)
    fun showCategories(list: ArrayList<Product>)
}