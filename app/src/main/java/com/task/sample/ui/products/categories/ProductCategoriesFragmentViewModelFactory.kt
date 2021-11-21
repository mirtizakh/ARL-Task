package com.task.sample.ui.products.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.repository.IProductRepository

@Suppress("UNCHECKED_CAST")
class ProductCategoriesFragmentViewModelFactory(var productRepository: IProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductCategoriesFragmentViewModel(productRepository) as T
    }
}