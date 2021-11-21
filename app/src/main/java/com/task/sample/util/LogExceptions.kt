package com.task.sample.util

import android.util.Log

interface ILogException {
    fun saveExceptionErrors(error: Exception, className: String, lineNumber: String)
}

class LogExceptions : ILogException {
    override fun saveExceptionErrors(error: Exception, className: String, lineNumber: String) {
        // Show values of log
        val stackTrace = error.stackTrace[0]
        stackTrace?.let { s ->
            var msg = "Class name: " + className + " (" + s.className + ") "
            msg += "at line: " + lineNumber + " (" + s.lineNumber + ") "
            Log.e("LogError", msg)
        }
    }
}