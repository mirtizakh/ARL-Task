package com.task.sample.ui.products.categories

import com.google.gson.JsonArray
import com.task.sample.ui.base.BaseNavigator

interface ProductCategoriesFragmentNavigator : BaseNavigator {
    fun showError(error: Int)
    fun showCategories(jsonArray: JsonArray)
}