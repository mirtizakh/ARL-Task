package com.task.sample.ui.products.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.repository.IProductRepository

@Suppress("UNCHECKED_CAST")
class ProductsListFragmentViewModelFactory(private var productRepository: IProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsListFragmentViewModel(productRepository) as T
    }
}