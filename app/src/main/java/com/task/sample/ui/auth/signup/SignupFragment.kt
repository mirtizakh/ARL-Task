package com.task.sample.ui.auth.signup

import android.view.View
import android.widget.Toast
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentSignupBinding
import com.task.sample.ui.base.BaseFragment
import com.task.sample.util.mayNavigate
import org.kodein.di.generic.instance

class SignupFragment : BaseFragment<FragmentSignupBinding, SignupViewModel>(),
    SignupFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_signup

    override fun getBindingVariable() = BR.signupViewModel

    override val viewModel = SignupViewModel::class.java

    override val viewModelFactory: SignupViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)

        viewDataBinding.btnSignup.setOnClickListener {
            if (injectedViewModel.validate()) {
                injectedViewModel.signupUser()
            }
        }

        viewDataBinding.tvLogin.setOnClickListener {
            redirectToLoginScreen()
        }
    }

    override fun signUpSuccessfully() {
        (activity as MainActivity).navigate(R.id.action_signupFragment_to_dashboardFragment)
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

    // end region OVERRIDE

    private fun redirectToLoginScreen() {
        if (mayNavigate(R.id.signupFragment)) {
            (activity as MainActivity).navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}