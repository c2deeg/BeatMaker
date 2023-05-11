package com.beatmaker.Handler

import com.beatmaker.Models.Login.LoginExample
import com.beatmaker.Models.Signup.SignupExample

interface SignupHandler {
    fun onSuccess(signupExample: SignupExample?)
    fun onError(message: String?)
}