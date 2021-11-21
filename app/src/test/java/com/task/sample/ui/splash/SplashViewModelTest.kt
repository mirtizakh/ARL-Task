package com.task.sample.ui.splash

import com.google.common.truth.Truth
import com.task.sample.R
import com.task.sample.mock.MockSessionManager
import org.junit.Before
import org.junit.Test

class SplashViewModelTest {
    // region VARIABLES
    private lateinit var sessionManager: MockSessionManager
    private lateinit var splashViewModel: SplashViewModel
    // end region VARIABLES

    // region SETUP
    @Before
    fun setUp() {
        sessionManager = MockSessionManager()
        splashViewModel = SplashViewModel(sessionManager)
    }
    // end region SETUP

    // region TESTCASES
    //[unitUnderTest]_[input]_[ConditionToBeMet]
    @Test
    fun redirectedToScreen_whenUserIsLoggedin_redirectScreenIdMatchestoDashbord() {
        val splashFragmentNavigator = object : SplashFragmentNavigator {
            override fun redirectToScreen(redirectionId: Int) {
                Truth.assertThat(redirectionId)
                    .isEqualTo(R.id.action_splashFragment_to_dashboardFragment)
            }
        }
        splashViewModel.setNavigator(splashFragmentNavigator)
        sessionManager.isLoginUser = true
        splashViewModel.redirectedToScreen()

    }

    @Test
    fun redirectedToScreen_whenUserIsNotLoggedin_redirectScreenIdMatchestoSignup() {
        val splashFragmentNavigator = object : SplashFragmentNavigator {
            override fun redirectToScreen(redirectionId: Int) {
                Truth.assertThat(redirectionId)
                    .isEqualTo(R.id.action_splashFragment_to_signupFragment)
            }
        }
        splashViewModel.setNavigator(splashFragmentNavigator)
        sessionManager.isLoginUser = false
        splashViewModel.redirectedToScreen()
    }
    // end region TESTCASES
}