package com.humanresource.Handler

import com.beatmaker.Models.Login.LoginExample


interface LoginHandler {
    fun onSuccess(loginExample: LoginExample?)
    fun onError(message: String?)

}