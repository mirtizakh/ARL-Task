package com.task.sample.ui.auth.login

import com.task.sample.ui.base.BaseNavigator

interface LoginFragmentNavigator : BaseNavigator {
    fun signInSuccessfully()
    fun showFieldsError(error: Int)
}