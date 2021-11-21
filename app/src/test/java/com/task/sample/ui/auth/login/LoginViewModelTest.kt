package com.task.sample.ui.auth.login

import com.google.common.truth.Truth
import com.task.sample.mock.MockSessionManager
import com.task.sample.util.Validation
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {
    // region VARIABLES
    private lateinit var validation: Validation
    private lateinit var sessionManager: MockSessionManager
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginFragmentNavigator: LoginFragmentNavigator
    // end region VARIABLES

    // region SETUP
    @Before
    fun setUp() {
        validation = Validation()
        sessionManager = MockSessionManager()
        loginViewModel = LoginViewModel(validation, sessionManager)
        loginFragmentNavigator = Mockito.mock(LoginFragmentNavigator::class.java)
        loginViewModel.setNavigator(loginFragmentNavigator)
    }
    // end region SETUP

    // region TESTCASES
    //[unitUnderTest]_[input]_[ConditionToBeMet]
    @Test
    fun loginUser_whenUserSuccessfullyLogin_checkNavigatorSuccessfullyFunctionsCalls1Time() {
        loginViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        loginViewModel.liveDataPassword.value = "12345678"
        sessionManager.isSigninUser = true
        loginViewModel.loginUser()
        Mockito.verify(loginFragmentNavigator, Mockito.times(1)).signInSuccessfully()
    }

    @Test
    fun loginUser_whenUserIsNotSuccessfullyLogin_checkNavigatorShowFieldsErrorFunctionsCalls1Time() {
        loginViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        loginViewModel.liveDataPassword.value = "12345678"
        sessionManager.isSigninUser = false
        loginViewModel.loginUser()
        Mockito.verify(loginFragmentNavigator, Mockito.times(1)).showFieldsError(Mockito.anyInt())
    }

    @Test
    fun validate_whenEmailIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time() {
        loginViewModel.liveDataEmail.value = "irtizakh@gmail"
        loginViewModel.liveDataPassword.value = "12345678"
        Truth.assertThat(loginViewModel.validate()).isFalse()
        Mockito.verify(loginFragmentNavigator, Mockito.times(1)).showFieldsError(Mockito.anyInt())
    }

    @Test
    fun validate_whenPasswordIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time() {
        loginViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        loginViewModel.liveDataPassword.value = "123456"
        Truth.assertThat(loginViewModel.validate()).isFalse()
        Mockito.verify(loginFragmentNavigator, Mockito.times(1)).showFieldsError(Mockito.anyInt())
    }
    // end region TESTCASES
}