package com.beatmaker.Activities.LoginActivity.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.beatmaker.Activities.HomeActivity.HomeActivity;
import com.beatmaker.Activities.LoginActivity.view.LoginView;
import com.beatmaker.Handler.SocialLoginHandler;
import com.beatmaker.Models.Login.LoginExample;
import com.beatmaker.Models.Socail.SocailLoginExample;
import com.beatmaker.R;
import com.beatmaker.Utils.Utils;
import com.beatmaker.Utils.WebServices;
import com.humanresource.Handler.LoginHandler;

public class LoginPresenter {

    private final Activity activity;
    private final LoginView loginView;
    private String et_loginEmail;
    private String et_loginPassword;

    private EditText et_signUpName;
    private EditText et_signUpEmail;
    private EditText et_signUpPassword;

    public LoginPresenter(Activity activity, LoginView LoginView) {
        this.loginView = LoginView;
        this.activity = activity;
    }

    public void hitLogin(EditText et_loginEmail, EditText et_loginPassword) {

        this.et_loginEmail = String.valueOf(et_loginEmail);
        this.et_loginPassword = String.valueOf(et_loginPassword);

     if (Utils.Companion.isNetworkConnected(activity)) {
            if (checkValidation()) {
                loginView.showDialog(activity);

                WebServices.getmInstance().loginMethod(et_loginEmail.getText().toString().trim(), et_loginPassword.getText().toString().trim(), new LoginHandler() {
                    @Override
                    public void onSuccess(LoginExample loginExample) {
                        loginView.hideDialog();
                        if (loginExample != null) {
                            if (loginExample.getIsSuccess() == true) {
                                loginView.showMessage(activity, "Login Successfully");
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
                                loginView.showMessage(activity, loginExample.getMessage());
                            }
                        } else {
                            loginView.showMessage(activity, loginExample.getMessage());
                        }

                    }

                    @Override
                    public void onError(String message) {
                        loginView.hideDialog();
                        loginView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(activity,"Please check internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkValidation() {
//        if (et_loginEmail.getText().toString().trim().length() == 0) {
//            loginView.showMessage(activity, "Please enter email");
////            Toast.makeText(activity, "Please enter email", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (!(isValidEmail(et_loginEmail.getText().toString().trim()))) {
//            loginView.showMessage(activity, "Please enter valid email");
////            Toast.makeText(activity, "Please enter valid email", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (et_loginPassword.getText().toString().trim().length() == 0) {
//            loginView.showMessage(activity, "Please enter password");
////            Toast.makeText(activity, "Please enter password", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    public void sociallogin(String socialLoginId, String platform,String name,String gender) {

        this.et_loginEmail = et_loginEmail;
        this.et_loginPassword = et_loginPassword;

        if (Utils.Companion.isNetworkConnected(activity)) {
            if (checkValidation()) {
                loginView.showDialog(activity);

                WebServices.getmInstance().socialLogin(socialLoginId,platform,name,gender,new SocialLoginHandler() {
                    @Override
                    public void onSuccess(@NonNull SocailLoginExample socailLoginExample) {
                        loginView.hideDialog();
                        if (socailLoginExample != null) {
                            if (socailLoginExample.getIsSuccess() == true) {
                                loginView.showMessage(activity, "Login Successfully");
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
                                loginView.showMessage(activity, socailLoginExample.getMessage());
                            }
                        } else {
                            loginView.showMessage(activity, socailLoginExample.getMessage());
                        }

                    }





                    @Override
                    public void onError(String message) {
                        loginView.hideDialog();
                        loginView.showMessage(activity, message);
//                    Toast.makeText(activity, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(activity,"Please check internet connection", Toast.LENGTH_SHORT).show();
        }

    }


}
