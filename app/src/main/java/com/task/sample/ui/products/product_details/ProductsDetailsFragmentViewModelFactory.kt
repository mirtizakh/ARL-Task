package com.task.sample.ui.products.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.db.DatabaseInterface
import com.task.sample.data.repository.IProductRepository

@Suppress("UNCHECKED_CAST")
class ProductsDetailsFragmentViewModelFactory(private var databaseInterface: DatabaseInterface) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsDetailsFragmentViewModel(databaseInterface) as T
    }
}