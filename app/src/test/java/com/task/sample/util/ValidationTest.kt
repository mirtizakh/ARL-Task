package com.task.sample.util

import com.google.common.truth.Truth
import com.google.gson.JsonObject
import com.task.sample.JsonReader
import org.junit.Before
import org.junit.Test

class ValidationTest {
    // region VARIABLES
    private lateinit var validation: Validation
    private var jsonObject: JsonObject? = null
    // end region VARIABLES

    // region SETUP
    @Before
    fun setUp() {
        validation = Validation()
        jsonObject = JsonReader.readJson(validation, "user_input.json")
        jsonObject = jsonObject?.get("user_Input")?.asJsonObject
    }
    // end region SETUP

    // region TESTCASES
    //[unitUnderTest]_[input]_[ConditionToBeMet]
    @Test
    fun isNameValid_checkValidNames_allNamesShouldBeAccept() {
        val validNames = jsonObject?.get("valid_Name")?.asJsonArray
        validNames?.let { names ->
            for (name in names) {
                val result = validation.isNameValid(name.asString)
                Truth.assertThat(result).isTrue()
            }
        }
    }

    @Test
    fun isNameValid_checkInvalidNames_allNamesShouldNotBeAccept() {
        val inValidNames = jsonObject?.get("invalid_Name")?.asJsonArray
        inValidNames?.let { names ->
            for (name in names) {
                val result = validation.isNameValid(name.asString)
                Truth.assertThat(result).isFalse()
            }
        }
    }

    @Test
    fun isEmailValid_checkValidEmails_allEmailsShouldBeAccept() {
        val validEmails = jsonObject?.get("valid_Email")?.asJsonArray
        validEmails?.let { emails ->
            for (email in emails) {
                val result = validation.isEmailValid(email.asString)
                Truth.assertThat(result).isTrue()
            }
        }
    }

    @Test
    fun isEmailValid_checkInvalidEmails_allEmailsShouldNotBeAccept() {
        val invalidEmails = jsonObject?.get("invalid_Email")?.asJsonArray
        invalidEmails?.let { emails ->
            for (email in emails) {
                val result = validation.isEmailValid(email.asString)
                Truth.assertThat(result).isFalse()
            }
        }
    }

    @Test
    fun isPasswordValid_checkValidPasswords_allPasswordsShouldBeAccept() {
        val validPasswords = jsonObject?.get("valid_Password")?.asJsonArray
        validPasswords?.let { passwords ->
            for (password in passwords) {
                val result = validation.isPasswordValid(password.asString)
                Truth.assertThat(result).isTrue()
            }
        }
    }

    @Test
    fun isPasswordValid_checkInvalidPasswords_allPasswordsShouldNotBeAccept() {
        val invalidPasswords = jsonObject?.get("invalid_Password")?.asJsonArray
        invalidPasswords?.let { passwords ->
            for (password in passwords) {
                val result = validation.isPasswordValid(password.asString)
                Truth.assertThat(result).isFalse()
            }
        }
    }
    // end region TESTCASES
}