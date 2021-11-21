package com.task.sample.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel<*>> : AppCompatActivity() {
    // region VARIABLES
    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel: Class<BVM>

    lateinit var injectedViewModel: BVM

    lateinit var viewDataBinding: VDB

    abstract fun getBindingVariable(): Int
    // end region VARIABLES

    protected abstract fun initUserInterface()

    // region LIFECYCLE methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        executeDataBinding()
        initUserInterface()
    }
    // end region LIFECYCLE methods

    // region PRIVATE methods
    private fun executeDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        injectedViewModel = ViewModelProvider(this, viewModelFactory).get(viewModel)
        viewDataBinding.setVariable(getBindingVariable(), injectedViewModel)
        viewDataBinding.executePendingBindings()
    }
    // end region PRIVATE methods
}