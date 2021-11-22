package com.task.sample.data.network.api_call.session

import com.google.firebase.auth.FirebaseAuth


interface ISessionManager {
    fun logoutUser(): Boolean
    fun isLogin(): Boolean
    fun signUpUser(email: String, password: String, callBack: (Boolean) -> Unit)
    fun signInUser(email: String, password: String, callBack: (Boolean) -> Unit)
}

class SessionManager : ISessionManager {
    // region VARIABLES
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    // end region VARIABLES

    // region OVERRIDE methods
    override fun logoutUser(): Boolean {
         auth.signOut()
        return true
    }

    override fun isLogin(): Boolean {
        return auth.currentUser != null
    }

    override fun signUpUser(email: String, password: String, callBack: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callBack(true)
                } else {
                    // If sign up fails
                    callBack(false)
                }
            }
    }

    override fun signInUser(email: String, password: String, callBack: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callBack(true)
                } else {
                    // If sign in fails
                    callBack(false)
                }
            }
    }
    // end region OVERRIDE methods
}