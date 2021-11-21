package com.task.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel<*>> : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel: Class<BVM>

    lateinit var injectedViewModel: BVM

    lateinit var viewDataBinding: VDB

    abstract fun getBindingVariable(): Int

    // region LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectedViewModel = ViewModelProvider(this, viewModelFactory).get(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container, false
        )

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), injectedViewModel)
        initUserInterface(view)
    }
    // end region LIFECYCLE


    protected abstract fun initUserInterface(view: View?)
}