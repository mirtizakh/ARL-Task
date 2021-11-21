package com.task.sample.ui.auth.signup

import com.google.common.truth.Truth
import com.google.gson.JsonObject
import com.task.sample.JsonReader
import com.task.sample.mock.MockSessionManager
import com.task.sample.util.Validation
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SignupViewModelTest {
    // region VARIABLES
    private lateinit var validation: Validation
    private lateinit var sessionManager: MockSessionManager
    private lateinit var signupViewModel: SignupViewModel
    private var jsonObject: JsonObject? = null
    private lateinit var signupFragmentNavigator: SignupFragmentNavigator
    // end region VARIABLES

    // region SETUP
    @Before
    fun setUp() {
        validation = Validation()
        sessionManager = MockSessionManager()
        signupViewModel = SignupViewModel(validation, sessionManager)
        signupFragmentNavigator = mock(SignupFragmentNavigator::class.java)
        signupViewModel.setNavigator(signupFragmentNavigator)
        jsonObject = JsonReader.readJson(validation, "user_input.json")
        jsonObject = jsonObject?.get("user_Input")?.asJsonObject
    }
    // end region SETUP

    // region TESTCASES
    //[unitUnderTest]_[input]_[ConditionToBeMet]
    @Test
    fun signupUser_whenUserSuccessfullySignup_checkNavigatorSuccessfullyFunctionsCalls1Time() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        signupViewModel.liveDataPassword.value = "12345678"
        sessionManager.isSignupUser = true
        signupViewModel.signupUser()
        verify(signupFragmentNavigator, times(1)).signUpSuccessfully()
    }

    @Test
    fun signupUser_whenUserIsNotSuccessfullySignup_checkNavigatorShowFieldsErrorFunctionsCalls1Time() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        signupViewModel.liveDataPassword.value = "12345678"
        sessionManager.isSignupUser = false
        signupViewModel.signupUser()
        verify(signupFragmentNavigator, times(1)).showFieldsError(anyInt())
    }

    @Test
    fun validate_whenNameIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        signupViewModel.liveDataPassword.value = "12345678"
        signupViewModel.liveDataConfirmPassword.value = "12345678"
        signupViewModel.liveDataName.value = "ir"
        Truth.assertThat(signupViewModel.validate()).isFalse()
        verify(signupFragmentNavigator, times(1)).showFieldsError(anyInt())
    }

    @Test
    fun validate_whenEmailIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail"
        signupViewModel.liveDataPassword.value = "12345678"
        signupViewModel.liveDataConfirmPassword.value = "12345678"
        signupViewModel.liveDataName.value = "irtiza"
        Truth.assertThat(signupViewModel.validate()).isFalse()
        verify(signupFragmentNavigator, times(1)).showFieldsError(anyInt())
    }

    @Test
    fun validate_whenPasswordIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        signupViewModel.liveDataPassword.value = "123456"
        signupViewModel.liveDataConfirmPassword.value = "12345678"
        signupViewModel.liveDataName.value = "irtiza"
        Truth.assertThat(signupViewModel.validate()).isFalse()
        verify(signupFragmentNavigator, times(1)).showFieldsError(anyInt())
    }

    @Test
    fun validate_whenAllFieldsAreValid_returnsTrueAndNavigatorShowFieldsErrorFunctionsNeverCalls() {
        signupViewModel.liveDataEmail.value = "irtizakh@gmail.com"
        signupViewModel.liveDataPassword.value = "12345678"
        signupViewModel.liveDataConfirmPassword.value = "12345678"
        signupViewModel.liveDataName.value = "irtiza"
        Truth.assertThat(signupViewModel.validate()).isTrue()
        verify(signupFragmentNavigator, times(0)).showFieldsError(anyInt())
    }
    // end region TESTCASES
}