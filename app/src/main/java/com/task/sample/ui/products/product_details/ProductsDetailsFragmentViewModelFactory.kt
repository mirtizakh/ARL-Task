package com.task.sample.ui.products.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.repository.IProductRepository

@Suppress("UNCHECKED_CAST")
class ProductsDetailsFragmentViewModelFactory(private var productRepository: IProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsDetailsFragmentViewModel(productRepository) as T
    }
}