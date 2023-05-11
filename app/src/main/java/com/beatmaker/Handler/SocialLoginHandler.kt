package com.beatmaker.Handler

import com.beatmaker.Models.Signup.SignupExample
import com.beatmaker.Models.Socail.SocailLoginExample

interface SocialLoginHandler {
    fun onSuccess(socailLoginExample: SocailLoginExample)
    fun onError(message: String?)
}