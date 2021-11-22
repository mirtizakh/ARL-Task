package com.task.sample.ui.products.product_details

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.data.network.response.response_model.Product
import com.task.sample.databinding.FragmentProductDetailsBinding
import com.task.sample.ui.base.BaseFragment
import com.task.sample.util.empty
import org.kodein.di.generic.instance

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductsDetailsFragmentViewModel>(),
    ProductDetailsNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_product_details
    override fun getBindingVariable() = BR._all

    override val viewModel = ProductsDetailsFragmentViewModel::class.java

    override val viewModelFactory: ProductsDetailsFragmentViewModelFactory by AppController.kodein()
        .instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
        (activity as MainActivity).setVisibilityOfBottomView(View.VISIBLE)

        arguments?.let { arg ->
            if (arg.containsKey("product")) {
                injectedViewModel.product = arg.getSerializable("product") as Product
                setDetails(injectedViewModel.product)
                injectedViewModel.getProductCount()
            }
        }

        viewDataBinding.icAdd.setOnClickListener {
            injectedViewModel.addCountValue()
        }
        viewDataBinding.icMinus.setOnClickListener {
            injectedViewModel.minusCountValue()
        }

        injectedViewModel.getProductsCount()
    }

    override fun showError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    override fun setCountValue(count: String) {
        viewDataBinding.tvCount.text = count
    }

    override fun setProductCountValue(count: Int) {
        (activity as MainActivity).updateBadgeForCart(count)
    }

    // end region OVERRIDE Methods

    // region PRIVATE Methods
    private fun setDetails(product: Product) {
        loadProduct(product.image ?: String.empty, viewDataBinding.icProduct)

        var detailsText = getString(R.string.product_price) + " " + product.price + " Rs"
        detailsText += "\n\n" + getString(R.string.product_title) + " " + product.title
        detailsText += "\n\n" + getString(R.string.product_description) + " " + product.description
        detailsText += "\n\n" + getString(R.string.product_category) + " " + product.category
        detailsText += "\n\n" + getString(R.string.product_rating) + " " + product.rating?.rate
        viewDataBinding.tvProductDetails.text = detailsText
    }

    private fun loadProduct(imageUrl: String, imageView: ImageView) {
        Glide.with(AppController.applicationContext())
            .load(imageUrl)
            .thumbnail(0.3f)
            .centerCrop()
            .placeholder(R.drawable.ic_products)
            .error(R.drawable.ic_products)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
    // end region PRIVATE Methods
}