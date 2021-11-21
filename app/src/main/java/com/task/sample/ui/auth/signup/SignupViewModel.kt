package com.task.sample.ui.auth.signup

import androidx.lifecycle.MutableLiveData
import com.task.sample.R
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.ui.base.BaseViewModel
import com.task.sample.util.ValidationInterface
import com.task.sample.util.empty

class SignupViewModel(
    private var validation: ValidationInterface,
    private var sessionManager: ISessionManager
) : BaseViewModel<SignupFragmentNavigator>() {


    var liveDataEmail: MutableLiveData<String> = MutableLiveData()
    var liveDataPassword: MutableLiveData<String> = MutableLiveData()
    var liveDataConfirmPassword: MutableLiveData<String> = MutableLiveData()
    var liveDataName: MutableLiveData<String> = MutableLiveData()
    fun validate(): Boolean {
        return validateName() && validateEmail() && validatePassword() && validateConfirmPassword()
    }


    private fun validatePassword(): Boolean {
        return if (validation.isPasswordValid(liveDataPassword.value?.trim() ?: String.empty)) {
            true
        } else {
            getNavigator()?.showFieldsError(R.string.please_enter_correct_password)
            false
        }
    }

    private fun validateConfirmPassword(): Boolean {
        return if (validation.isPasswordValid(
                liveDataConfirmPassword.value?.trim() ?: String.empty
            ) &&
            liveDataConfirmPassword.value?.trim().equals(liveDataPassword.value?.trim())
        ) {
            true
        } else {
            getNavigator()?.showFieldsError(R.string.please_enter_correct_password)
            false
        }
    }

    private fun validateEmail(): Boolean {
        return if (validation.isEmailValid(liveDataEmail.value?.trim() ?: String.empty)) {
            true
        } else {
            getNavigator()?.showFieldsError(R.string.please_enter_correct_email)
            false
        }
    }

    private fun validateName(): Boolean {
        return if (validation.isNameValid(liveDataName.value?.trim() ?: String.empty)) {
            true
        } else {
            getNavigator()?.showFieldsError(R.string.please_enter_correct_name)
            false
        }
    }
}