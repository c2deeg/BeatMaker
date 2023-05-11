package com.beatmaker.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.beatmaker.R;

public class Utilsjava {


    private Activity activity;
    private String[] text;
    private String[] text2;


    private static ProgressDialog progressDialog;

    public static final String USERLOGIN = "user_login";
    public static final String USERNAME = "username";
    public static final String USEREMAIL = "useremail";
    public static final String USERPHOTO = "userphoto";
    public static final String LOGINTYPE = "logintype";
    public static final String USERID = "userid";
    public static final String TOKEN = "token";
    public static final String DEVICETOKEN = "deviceToken";
    public static final String HEADERSTOKEN = "headerstoken";

    public static final String CONTACTNUMBER = "contactNumber";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String CATEGORIESID1 = "categoriesid1";
    public static final String CATEGORIESID2 = "categoriesid2";

    public static final String GETFULLADDRESS = "fulladdress";
    public static final String GETADDRESS = "getaddress";
    public static final String GETSTATE = "getstate";
    public static final String GETADDRESSID = "getaddressid";
    public static final String GETCITY = "getcity";
    public static final String GENDERSELECT = "genderSelect";
    public static final String NAME = "name";
    public static final String ABOUTME = "aboutme";
    public static final String ANOTHERUSERID = "anotheruserid";
    public static final String FORGOTPASSWORDTOKEN = "forgotpasswordtoken";
    public static final String PROFILEIDANOTHERUSER = "profileidanotheruser";

    public static final String ImageBaseURL = "http://13.54.226.124/";
    public static final String BOOLEANVALUE = "booleanvalue";


    public static final String URL_CHAT_SERVER = "http://3.136.56.91:8001";

    public static final String FACEBOOKTOKEN = "facebooktoken";

    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    // for keyboard hide
    public static void hideKeyboardFrom(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showDialogMethod(Activity activity, FragmentManager supportFragmentManager) {

        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait while loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

    public static void hideDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();

            }
        } catch (Exception e) {
        }
    }

    public static void showMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    //camra
    public static boolean hasFeatureCamera(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


//
//    public static void changeTabFragment(Context context, androidx.fragment.app.Fragment fragment) {
//
//        androidx.fragment.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commitAllowingStateLoss();
//
//    }

}


