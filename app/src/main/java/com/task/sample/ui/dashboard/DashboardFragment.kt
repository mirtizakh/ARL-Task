package com.task.sample.ui.dashboard

import android.view.View
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentDashboardBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    DashboardFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_dashboard

    override fun getBindingVariable() = BR._all

    override val viewModel = DashboardViewModel::class.java

    override val viewModelFactory: DashboardViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)
    }
    // end region OVERRIDE Methods
}