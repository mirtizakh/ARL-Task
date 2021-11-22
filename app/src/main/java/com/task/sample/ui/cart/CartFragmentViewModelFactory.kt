package com.task.sample.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.db.DatabaseInterface

@Suppress("UNCHECKED_CAST")
class CartFragmentViewModelFactory(private var databaseInterface: DatabaseInterface) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartFragmentViewModel(databaseInterface) as T
    }
}