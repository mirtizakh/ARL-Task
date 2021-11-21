package com.task.sample.data.network.response.response_parser

enum class ResponseCodes(val code: Int) {
    SUCCESS_CODE_200(200),
    ERROR_CODE_401(401),
    ERROR_CODE_404(404),
    ERROR_CODE_400(400),
    ERROR_CODE_500(500)
}

enum class ResponseMessages(val message: String) {
    SOME_THING_WENT_WRONG("Something went wrong please try again later"),
    Empty_Or_Null_Response("Empty or Null response"),
    CODE_RESEND_SUCCESSFULLY("Code resent successfully"),
    ENTER_CORRECT_CODE("Please enter correct code"),
    SESSION_TIMEOUT("Sorry, you didn’t get a response from Nye. Please try again"),
    SESSION_EXPIRE("Your Token Is Expire , Please Login Again")
}