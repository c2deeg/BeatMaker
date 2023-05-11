package com.beatmaker.Activities.SignUpActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.beatmaker.Activities.HomeActivity.HomeActivity
import com.beatmaker.Activities.SignUpActivity.Presenter.SignUpPresenter
import com.beatmaker.Activities.SignUpActivity.View.SignupView
import com.beatmaker.R
import com.beatmaker.Utils.Utils

class SignUpActivity : AppCompatActivity(), View.OnClickListener,SignupView {
    lateinit var btn_signup: Button
    lateinit var et_name: EditText
    lateinit var et_email: EditText
    lateinit var et_pass: EditText
    lateinit var et_confirmpass: EditText
    var signUpPresenter:SignUpPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        listeners()

        signUpPresenter = SignUpPresenter(this,this)
    }

    private fun init() {
        btn_signup = findViewById(R.id.btn_signup)
        et_name = findViewById(R.id.et_name)
        et_email = findViewById(R.id.et_email)
        et_pass = findViewById(R.id.et_pass)
        et_confirmpass = findViewById(R.id.et_confirmpass)

    }

    private fun listeners() {
        btn_signup.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_signup -> {

                signUpPresenter?.signup(et_name,et_email, et_pass)

            }
        }
    }

    override fun showDialog(activity: Activity) {
        Utils.showDialogMethod(activity, activity.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity?, message: String?) {
        Utils.showMessage(activity, message)
    }
}