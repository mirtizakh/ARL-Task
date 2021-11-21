package com.task.sample.ui.auth.signup

import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.ui.base.BaseViewModel
import com.task.sample.util.ValidationInterface

class SignupViewModel(
    private var validation: ValidationInterface,
    private var sessionManager: ISessionManager
) : BaseViewModel<SignupFragmentNavigator>()