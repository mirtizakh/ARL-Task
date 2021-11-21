package com.task.sample.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.util.ValidationInterface

@Suppress("UNCHECKED_CAST")
class SignupViewModelFactory(
    private var validation: ValidationInterface,
    private var sessionManager: ISessionManager
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignupViewModel(validation, sessionManager) as T
    }
}