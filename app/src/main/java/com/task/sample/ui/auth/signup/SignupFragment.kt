package com.task.sample.ui.auth.signup

import android.view.View
import android.widget.Toast
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentSignupBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class SignupFragment : BaseFragment<FragmentSignupBinding, SignupViewModel>(),
    SignupFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_signup

    override fun getBindingVariable() = BR.signupViewModel

    override val viewModel = SignupViewModel::class.java

    override val viewModelFactory: SignupViewModelFactory by AppController.kodein().instance()


    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)

        viewDataBinding.btnSignup.setOnClickListener {
            if (injectedViewModel.validate()) {

            }
        }
    }

    override fun signUpSuccessfully() {

    }

    override fun showFieldsError(error: Int) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    /**
     * To show and hide of progress bar based on api call and response
     */
    override fun setVisibilityForProgress(visibility: Int) {
        viewDataBinding.progressBar.visibility = visibility
    }
    // end region VARIABLES


}