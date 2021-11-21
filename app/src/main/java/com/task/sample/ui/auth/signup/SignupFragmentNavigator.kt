package com.task.sample.ui.auth.signup

import com.task.sample.ui.base.BaseNavigator

interface SignupFragmentNavigator : BaseNavigator {
    fun signUpSuccessfully()
    fun showFieldsError(error: Int)
}