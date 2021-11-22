package com.task.sample.ui.products.categories

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.adapter.ProductCategoriesAdapter
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentProductCategoriesBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class ProductCategoriesFragment :
    BaseFragment<FragmentProductCategoriesBinding, ProductCategoriesFragmentViewModel>(),
    ProductCategoriesFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_product_categories
    private lateinit var adapter: ProductCategoriesAdapter
    private lateinit var jsonArray: JsonArray
    override fun getBindingVariable() = BR._all

    override val viewModel = ProductCategoriesFragmentViewModel::class.java

    override val viewModelFactory: ProductCategoriesFragmentViewModelFactory by AppController.kodein()
        .instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)
        (viewDataBinding.rvCategories.layoutManager as LinearLayoutManager).orientation =
            RecyclerView.VERTICAL
        adapter = ProductCategoriesAdapter { view ->
            viewDataBinding.rvCategories.findContainingViewHolder(view)?.adapterPosition?.let { position ->
                Toast.makeText(
                    requireContext(),
                    jsonArray.get(position).asString,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewDataBinding.rvCategories.adapter = adapter
        injectedViewModel.getProductCategories()
    }

    override fun showError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun showCategories(jsonArray: JsonArray) {
        if (jsonArray.size() > 0) {
            this.jsonArray = jsonArray
            adapter.setData(jsonArray)
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