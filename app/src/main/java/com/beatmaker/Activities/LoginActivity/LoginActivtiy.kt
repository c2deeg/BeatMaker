package com.beatmaker.Activities.LoginActivity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.beatmaker.Activities.HomeActivity.HomeActivity
import com.beatmaker.Activities.LoginActivity.presenter.LoginPresenter
import com.beatmaker.Activities.LoginActivity.view.LoginView
import com.beatmaker.Activities.SignUpActivity.SignUpActivity
import com.beatmaker.R
import com.beatmaker.Utils.Utils
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern


class LoginActivtiy : AppCompatActivity(), View.OnClickListener, LoginView {
    //    private var callbackManager: CallbackManager? = null
    lateinit var btn_login: Button
    lateinit var tv_signup: TextView
    lateinit var et_email: EditText
    lateinit var et_pass: EditText
    lateinit var img_eye: ImageView
    lateinit var activity: Activity
    lateinit var loginPresenter: LoginPresenter
    lateinit var login_google: ImageView
    private var login_facebook: ImageView? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 1
    var login_button: LoginButton? = null
    var callbackManager: CallbackManager? = null
    var flag = true


    var accessTokenTracker: AccessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(
            accessToken: AccessToken?,
            accessToken1: AccessToken?
        ) {

            if (accessToken1 == null) {
                Toast.makeText(activity, "logout", Toast.LENGTH_SHORT).show()
            } else {
                loaUserProfile(accessToken1)
            }
        }
    }


    // defining our own password pattern
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activtiy)

        activity = this
        init()
        listeners()

        //        updateUI(account);
        FacebookSdk.sdkInitialize(this!!.applicationContext)
        AppEventsLogger.activateApp(this!!.application)
        callbackManager = CallbackManager.Factory.create()

        login_button!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {


            override fun onCancel() {

            }

            override fun onError(e: FacebookException) {}
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(activity, "Login Successfully", Toast.LENGTH_SHORT).show();

//                                CSPreferences.putString(activity, Utils.USERLOGIN, "true");
//                                CSPreferences.putString(activity, Utils.USERNAME, loginExample.getData().getFullname());
//                                CSPreferences.putString(activity, Utils.USEREMAIL, loginExample.getData().getEmail());
//                                CSPreferences.putString(activity, Utils.USERPHOTO, loginExample.getData().getImage());
//                                CSPreferences.putString(activity, Utils.USERID, loginExample.getData().getId());
//                                CSPreferences.putString(activity, Utils.TOKEN, loginExample.getData().getToken());
//                            CSPreferences.putString(activity, Utils.LOGINTYPE, "normal");
                val homeIntent = Intent(activity, HomeActivity::class.java)
                startActivity(homeIntent)


            }
        })


        loginPresenter = LoginPresenter(activity, this)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private fun init() {

//        callbackManager = CallbackManager.Factory.create()

        login_facebook = findViewById(R.id.login_facebook)

        btn_login = findViewById(R.id.btn_login)
        tv_signup = findViewById(R.id.tv_signup)
        et_email = findViewById(R.id.et_email)
        et_pass = findViewById(R.id.et_pass)
        login_google = findViewById(R.id.login_google)
        login_button = findViewById(R.id.login_button)
        img_eye = findViewById(R.id.img_eye)

    }

    private fun listeners() {
        tv_signup.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        login_google.setOnClickListener(this)
        login_facebook?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_signup -> {

                var intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {

                val email: String = et_email.getText().toString().trim()
                val password: String = et_pass.getText().toString().trim()
                loginPresenter.hitLogin(et_email, et_pass)

            }
            R.id.login_google -> {
                signIn()
            }
            R.id.login_facebook -> {
                login_button!!.performClick()
            }
            R.id.img_eye->{


                if (flag) {
                    img_eye!!.setImageResource(R.drawable.hidden)
                    if (et_pass?.getTransformationMethod()?.javaClass?.getSimpleName() == "PasswordTransformationMethod") {
                        et_pass?.setTransformationMethod(SingleLineTransformationMethod())
                        et_pass?.setSelection(et_pass?.text!!.length)

                    } else {
                        et_pass?.setTransformationMethod(PasswordTransformationMethod())
                        et_pass?.setSelection(et_pass?.text!!.length);
                    }
                } else {
                    img_eye!!.setImageResource(R.drawable.ic_eye)


                    et_pass?.setTransformationMethod(PasswordTransformationMethod())
                    et_pass?.setSelection(et_pass?.text!!.length);

                }
                flag = !flag

            }
        }
    }


    private fun signIn() {
        try {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(activity, "Login Successfully", Toast.LENGTH_LONG).show()


            loginPresenter.sociallogin(account.id,"google",account.displayName.toString(),"male")
//            googleusername = account.displayName
//            googleusernamemail = account.email
//            googleuserid = account.id
//            Log.d("soiald", googleuserid.toString())
//            loginPresenter?.sociallogin(
//                googleuserid!!,
//                "google",
//                googleusernamemail!!,
//                googleusername!!,
//                ""
//            )

            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {

            var intent  = Intent(activity,HomeActivity::class.java)
            startActivity(intent        )
            Log.d("checkkk", e.toString())

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(ContentValues.TAG, "signInResult:failed code=" + e.statusCode)
        }
    }


    private fun loaUserProfile(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { jsonObject: JSONObject?, graphResponse: GraphResponse? ->
            if (jsonObject != null) {
                try {
                    //                    String email = jsonObject.getString("email");
                    val id = jsonObject.getString("id")
                    val name = jsonObject.getString("name")
                    loginPresenter.sociallogin(id,"google",name,"male")

                    //                    String firstName = jsonObject.getString("firstName");
                    //                    Toast.makeText(activity, email, Toast.LENGTH_SHORT).show();
                    //                    Toast.makeText(activity, id, Toast.LENGTH_SHORT).show();
                    //                    Toast.makeText(activity, name, Toast.LENGTH_SHORT).show();
                    //                    Toast.makeText(activity, firstName, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "ff", Toast.LENGTH_SHORT).show()

                }
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)

//        updateUI(account)
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

