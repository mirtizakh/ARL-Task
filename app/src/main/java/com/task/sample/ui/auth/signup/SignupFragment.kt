package com.task.sample.ui.auth.signup

import android.view.View
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentSignupBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class SignupFragment : BaseFragment<FragmentSignupBinding, SignupViewModel>() {

    // region VARIABLES
    override val layoutId = R.layout.fragment_signup

    override fun getBindingVariable() = BR._all

    override val viewModel = SignupViewModel::class.java

    override val viewModelFactory: SignupViewModelFactory by AppController.kodein().instance()


    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {

    }
    // end region VARIABLES

}