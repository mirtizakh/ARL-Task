package com.task.sample.ui.cart

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.adapter.CartListAdapter
import com.task.sample.app.AppController
import com.task.sample.data.network.response.response_model.Product
import com.task.sample.databinding.FragmentCartBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class CartFragment : BaseFragment<FragmentCartBinding, CartFragmentViewModel>(),
    CartFragmentNavigator {

    // region VARIABLES
    private lateinit var adapter: CartListAdapter
    private lateinit var list: ArrayList<Product>

    override val layoutId = R.layout.fragment_cart

    override fun getBindingVariable() = BR._all

    override val viewModel = CartFragmentViewModel::class.java

    override val viewModelFactory: CartFragmentViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)

        (viewDataBinding.rvCategories.layoutManager as LinearLayoutManager).orientation =
            RecyclerView.VERTICAL
        adapter = CartListAdapter()

        viewDataBinding.rvCategories.adapter = adapter
        injectedViewModel.getProducts()
    }

    override fun showCategories(list: ArrayList<Product>) {
        viewDataBinding.rvCategories.visibility = View.VISIBLE
        adapter.setData(list)
    }

    override fun showError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun showTotalPrice(price: Double) {
        viewDataBinding.tvTitle.visibility = View.VISIBLE
       viewDataBinding.tvTitle.text = "Total Price : "+price.toString()
    }

    /**
     * To show and hide of progress bar based on api call and response
     */
    override fun setVisibilityForProgress(visibility: Int) {
        viewDataBinding.shimmerCategories.visibility = visibility
    }
    // end region OVERRIDE Methods
}