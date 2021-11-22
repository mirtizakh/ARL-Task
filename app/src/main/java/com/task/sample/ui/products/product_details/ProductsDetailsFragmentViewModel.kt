package com.task.sample.ui.products.product_details


import androidx.lifecycle.viewModelScope
import com.task.sample.data.db.DatabaseInterface
import com.task.sample.data.network.response.response_model.Product
import com.task.sample.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductsDetailsFragmentViewModel(
    private var databaseInterface: DatabaseInterface
) :
    BaseViewModel<ProductDetailsNavigator>() {
    // region VARIABLES
    var itemCount: Int = 0
    lateinit var product: Product
    // end region VARIABLES

    // region PUBLIC methods

    // end region PUBLIC methods
    fun getProductCount() {
        viewModelScope.launch {
            val count = databaseInterface.getProductCount(product.id ?: 0)
            itemCount = count ?: 0
            getNavigator()?.setCountValue(itemCount.toString())
        }
    }

    fun addCountValue() {
        itemCount++
        saveProduct()
    }

    fun minusCountValue() {
        if (itemCount > 1) {
            itemCount--
            saveProduct()
        } else {
            viewModelScope.launch {
                var count = databaseInterface.getProductCount(product.id ?: 0)
                count = count ?: 0
                if (count == 1 && itemCount == 1) {
                    itemCount--
                    databaseInterface.deleteProduct(product.id ?: 0)
                    getNavigator()?.setCountValue(itemCount.toString())
                    getProductsCount()
                }
            }
        }
    }

    // Get product count from database
    fun getProductsCount() {
        viewModelScope.launch {
            val productsCount = databaseInterface.getProductsFromDB()
            getNavigator()?.setProductCountValue(productsCount?.size ?: 0)
        }
    }

    // Saving or Updating products in local database
    private fun saveProduct() {
        viewModelScope.launch {
            var count = databaseInterface.getProductCount(product.id ?: 0)
            count = count ?: 0
            product.count = itemCount

            product.price?.let {
                product.totalPrice = itemCount * it
            }

            if (count >= 1) {
                databaseInterface.updateProductToDB(product)
            } else {
                databaseInterface.saveProductToDB(product)
            }

            getNavigator()?.setCountValue(itemCount.toString())
            getProductsCount()
        }
    }
    // region PRIVATE methods

    // end region PRIVATE methods
}