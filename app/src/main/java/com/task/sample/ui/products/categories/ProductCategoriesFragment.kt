package com.task.sample.ui.products.categories

import android.view.View
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentProductCategoriesBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class ProductCategoriesFragment : BaseFragment<FragmentProductCategoriesBinding, ProductCategoriesFragmentViewModel>(),
    ProductCategoriesFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_product_categories

    override fun getBindingVariable() = BR._all

    override val viewModel = ProductCategoriesFragmentViewModel::class.java

    override val viewModelFactory: ProductCategoriesFragmentViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)
    }
    // end region OVERRIDE Methods
}