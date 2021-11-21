package com.task.sample.mock

import com.task.sample.util.ILogException

class MockLogException : ILogException{
    override fun saveExceptionErrors(error: Exception, className: String, lineNumber: String) {}
}