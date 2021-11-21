package com.task.sample.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.regex.Pattern

interface ValidationInterface {
    fun isEmailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
    fun isNameValid(name: String): Boolean
}

enum class ValidationRegex(val regex: String) {
    EMAIL_REGEX("^[a-zA-Z0-9]+[[+.'!_%-]{0,1}[a-zA-Z0-9]+]+[@][a-zA-Z]{1,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}+)+$"),
}

class Validation : ValidationInterface {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun isNameValid(name: String): Boolean {
        if (name.isEmpty()) return false
        else if (name.chars().anyMatch(Character::isLetter) && name.length >= 3) return true
        return false
    }

    override fun isEmailValid(email: String) =
        Pattern.compile(ValidationRegex.EMAIL_REGEX.regex).matcher(email).matches()

    override fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty() || password.contains(" ")) return false
        else if (password.length >= 8) return true
        return false
    }
}



