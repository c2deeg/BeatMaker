package com.beatmaker.Activities.SignUpActivity.Presenter;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.beatmaker.Activities.HomeActivity.HomeActivity;
import com.beatmaker.Activities.SignUpActivity.SignUpActivity;
import com.beatmaker.Activities.SignUpActivity.View.SignupView;
import com.beatmaker.Handler.SignupHandler;
import com.beatmaker.Models.Login.LoginExample;
import com.beatmaker.Models.Signup.SignupExample;
import com.beatmaker.Utils.Utils;
import com.beatmaker.Utils.WebServices;
import com.humanresource.Handler.LoginHandler;

public class SignUpPresenter {
    private final SignUpActivity activity;
    private final SignupView signupView;

    public SignUpPresenter(SignUpActivity activity, SignupView signupView) {
        this.activity = activity;
        this.signupView = signupView;

    }


    public void signup(EditText et_name, EditText et_email, EditText et_pass) {



        if (Utils.Companion.isNetworkConnected(activity)) {

                signupView.showDialog(activity);

                WebServices.getmInstance().signupMethod(et_name.getText().toString().trim(),et_email.getText().toString().trim(),
                        et_pass.getText().toString().trim(),"12345","dsdsdsdsdd", new SignupHandler() {
                            @Override
                            public void onSuccess(@Nullable SignupExample signupExample) {
                                signupView.hideDialog();
                                if (signupExample != null) {
                                    if (signupExample.getIsSuccess() == true) {
                                        signupView.showMessage(activity, "Signup Successfully");
//                            Toast.makeText(activity, "Login Successfully", Toast.LENGTH_SHORT).show();

//                                CSPreferences.putString(activity, Utils.USERLOGIN, "true");
//                                CSPreferences.putString(activity, Utils.USERNAME, loginExample.getData().getFullname());
//                                CSPreferences.putString(activity, Utils.USEREMAIL, loginExample.getData().getEmail());
//                                CSPreferences.putString(activity, Utils.USERPHOTO, loginExample.getData().getImage());
//                                CSPreferences.putString(activity, Utils.USERID, loginExample.getData().getId());
//                                CSPreferences.putString(activity, Utils.TOKEN, loginExample.getData().getToken());
//                            CSPreferences.putString(activity, Utils.LOGINTYPE, "normal");

                                        Intent homeIntent = new Intent(activity, HomeActivity.class);
                                        activity.startActivity(homeIntent);
                                        activity.finishAffinity();

                                    } else {
                                        signupView.showMessage(activity, signupExample.getMessage());
                                    }
                                } else {
                                    signupView.showMessage(activity, signupExample.getMessage());
                                }
                            }



                    @Override
                    public void onError(String message) {
                        signupView.hideDialog();
                        signupView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }


}
