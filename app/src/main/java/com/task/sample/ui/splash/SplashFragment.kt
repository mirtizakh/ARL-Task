package com.task.sample.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentSplashBinding
import com.task.sample.ui.base.BaseFragment
import com.task.sample.util.mayNavigate
import org.kodein.di.generic.instance

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(),
    SplashFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_splash

    override fun getBindingVariable() = BR._all

    override val viewModel = SplashViewModel::class.java

    override val viewModelFactory: SplashViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)

        Handler(Looper.getMainLooper()).postDelayed({
            injectedViewModel.redirectedToScreen()
        }, 3000)

    }

    override fun redirectToScreen(redirectionId: Int) {
        if (mayNavigate(R.id.splashFragment)) {
            (activity as MainActivity).navigate(redirectionId)
        }
    }
    // end region OVERRIDE Methods
}