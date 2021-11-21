## Table of contents
* [Testcases](#Test-cases)

## Test cases
Test cases of following classes are added
### SignupViewModel.kt
 6 test cases are added for this class with code coverage of 94% lines
* signupUser_whenUserSuccessfullySignup_checkNavigatorSuccessfullyFunctionsCalls1Time
* signupUser_whenUserIsNotSuccessfullySignup_checkNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenNameIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenEmailIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenPasswordIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenAllFieldsAreValid_returnsTrueAndNavigatorShowFieldsErrorFunctionsNeverCalls

### Validation.kt
 6 test cases are added for this class with code coverage of 100% lines
* isNameValid_checkValidNames_allNamesShouldBeAccept()
* isNameValid_checkInvalidNames_allNamesShouldNotBeAccept()
* isEmailValid_checkValidEmails_allEmailsShouldBeAccept()
* isEmailValid_checkInvalidEmails_allEmailsShouldNotBeAccept()
* isPasswordValid_checkValidPasswords_allPasswordsShouldBeAccept()
* isPasswordValid_checkInvalidPasswords_allPasswordsShouldNotBeAccept()

