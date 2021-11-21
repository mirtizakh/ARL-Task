package com.task.sample.ui.products.categories

import android.view.View
import android.widget.Toast
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentProductCategoriesBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class ProductCategoriesFragment :
    BaseFragment<FragmentProductCategoriesBinding, ProductCategoriesFragmentViewModel>(),
    ProductCategoriesFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_product_categories

    override fun getBindingVariable() = BR._all

    override val viewModel = ProductCategoriesFragmentViewModel::class.java

    override val viewModelFactory: ProductCategoriesFragmentViewModelFactory by AppController.kodein()
        .instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)
        injectedViewModel.getProductCategories()
    }

    override fun showError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun showCategories() {

    }

    /**
     * To show and hide of progress bar based on api call and response
     */
    override fun setVisibilityForProgress(visibility: Int) {
        viewDataBinding.progressBar.visibility = visibility
    }
    // end region OVERRIDE Methods
}