package com.task.sample.ui.auth.login

import android.view.View
import android.widget.Toast
import com.task.sample.BR
import com.task.sample.R
import com.task.sample.activity.MainActivity
import com.task.sample.app.AppController
import com.task.sample.databinding.FragmentLoginBinding
import com.task.sample.ui.base.BaseFragment
import org.kodein.di.generic.instance

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(),
    LoginFragmentNavigator {

    // region VARIABLES
    override val layoutId = R.layout.fragment_login

    override fun getBindingVariable() = BR.loginViewModel

    override val viewModel = LoginViewModel::class.java

    override val viewModelFactory: LoginViewModelFactory by AppController.kodein().instance()
    // end region VARIABLES

    // region OVERRIDE Methods
    override fun initUserInterface(view: View?) {
        injectedViewModel.setNavigator(this)

        viewDataBinding.btnLogin.setOnClickListener {
            if (injectedViewModel.validate()) {
                injectedViewModel.loginUser()
            }
        }

        viewDataBinding.tvLogin.setOnClickListener {
            (activity as MainActivity).navController?.popBackStack()
        }
    }

    override fun signInSuccessfully() {
        Toast.makeText(requireContext(), "Login", Toast.LENGTH_LONG).show()
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
    // end region OVERRIDE Methods
}