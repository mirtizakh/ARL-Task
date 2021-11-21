## Table of contents
* [General info](#General-info)
* [Technologies](#Technologies)
* [Test cases](#Test-cases)

## General info
This is an Android application that display a list of products like an ecommerce app. And also provide a product details UI.
Also this app has a mechanism to allow user to add the products in the shopping cart for order placing.
Login and signup feature also available by using Firebase authentication.

## Technologies
Project is created with:
1- Retrofit
2- Room Database
3- Firebase Authentication
4- Kodein Dependency Injection
5- Coroutines
6- Material Library

## Test cases
Test cases of following classes are added
#### SignupViewModel.kt
 6 test cases are added for this class with code coverage of 94% lines
* signupUser_whenUserSuccessfullySignup_checkNavigatorSuccessfullyFunctionsCalls1Time
* signupUser_whenUserIsNotSuccessfullySignup_checkNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenNameIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenEmailIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenPasswordIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time
* validate_whenAllFieldsAreValid_returnsTrueAndNavigatorShowFieldsErrorFunctionsNeverCalls

#### Validation.kt
 6 test cases are added for this class with code coverage of 100% lines
* isNameValid_checkValidNames_allNamesShouldBeAccept()
* isNameValid_checkInvalidNames_allNamesShouldNotBeAccept()
* isEmailValid_checkValidEmails_allEmailsShouldBeAccept()
* isEmailValid_checkInvalidEmails_allEmailsShouldNotBeAccept()
* isPasswordValid_checkValidPasswords_allPasswordsShouldBeAccept()
* isPasswordValid_checkInvalidPasswords_allPasswordsShouldNotBeAccept()

#### LoginViewModel.kt
 4 test cases are added for this class with code coverage of 96% lines
* loginUser_whenUserSuccessfullyLogin_checkNavigatorSuccessfullyFunctionsCalls1Time
* loginUser_whenUserIsNotSuccessfullyLogin_checkNavigatorShowFieldsErrorFunctionsCalls1Time()
* validate_whenEmailIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time()
* validate_whenPasswordIsNotValid_returnsFalseAndNavigatorShowFieldsErrorFunctionsCalls1Time()


