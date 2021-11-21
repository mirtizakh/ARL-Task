package com.task.sample.mock

import com.task.sample.data.network.api_call.session.ISessionManager

class MockSessionManager : ISessionManager {
    // region VARIABLES
    var isLoginUser = false
    var isLogoutUser = false
    var isSignupUser = false
    var isSigninUser = false
    // end region VARIABLES

    // region OVERRIDE methods
    override fun logoutUser(): Boolean {
        return isLogoutUser
    }

    override fun isLogin(): Boolean {
        return isLoginUser
    }

    override fun signUpUser(email: String, password: String, callBack: (Boolean) -> Unit) {
        callBack.invoke(isSignupUser)
    }

    override fun signInUser(email: String, password: String, callBack: (Boolean) -> Unit) {
        callBack.invoke(isSigninUser)
    }
    // end region OVERRIDE methods
}