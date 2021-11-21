package com.task.sample.ui.cart

import android.view.View
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentCartBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class CartFragment : BaseFragment<FragmentCartBinding, CartFragmentViewModel>(),
    CartFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_cart

    override fun getBindingVariable() = BR._all

    override val viewModel = CartFragmentViewModel::class.java

    override val viewModelFactory: CartFragmentViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)
    }
    // end region OVERRIDE Methods
}