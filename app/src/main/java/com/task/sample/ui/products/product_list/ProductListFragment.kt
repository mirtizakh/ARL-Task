package com.task.sample.ui.products.product_list

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.adapter.ProductListAdapter
import com.task.sample.app.AppController
import com.task.sample.data.network.response.response_model.Product
import com.task.sample.databinding.FragmentProductCategoriesBinding
import com.task.sample.ui.base.BaseFragment
import com.task.sample.util.empty
import org.kodein.di.generic.instance

class ProductListFragment :
    BaseFragment<FragmentProductCategoriesBinding, ProductsListFragmentViewModel>(),
    ProductListFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_product_categories
    private lateinit var adapter: ProductListAdapter
    private lateinit var list: ArrayList<Product>
    override fun getBindingVariable() = BR._all

    override val viewModel = ProductsListFragmentViewModel::class.java

    override val viewModelFactory: ProductsListFragmentViewModelFactory by AppController.kodein()
        .instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)
        (viewDataBinding.rvCategories.layoutManager as LinearLayoutManager).orientation =
            RecyclerView.VERTICAL
        adapter = ProductListAdapter { view ->
            viewDataBinding.rvCategories.findContainingViewHolder(view)?.adapterPosition?.let { position ->
                Toast.makeText(
                    requireContext(),
                    list.get(position).title,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewDataBinding.rvCategories.adapter = adapter
        arguments?.let { arg ->
            if (arg.containsKey("category")) {
                injectedViewModel.getProductCategories(arg.getString("category") ?: String.empty)
            }
        }
    }

    override fun showError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun showCategories(list: ArrayList<Product>) {
        if (list.size > 0) {
            this.list = list
            adapter.setData(list)
            viewDataBinding.rvCategories.visibility = View.VISIBLE
        } else {
            showError(R.string.no_categories)
        }
    }

    /**
     * To show and hide of progress bar based on api call and response
     */
    override fun setVisibilityForProgress(visibility: Int) {
        viewDataBinding.shimmerCategories.visibility = visibility
    }
    // end region OVERRIDE Methods
}