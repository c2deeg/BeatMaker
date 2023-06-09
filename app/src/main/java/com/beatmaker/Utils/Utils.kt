package com.beatmaker.Utils

import android.app.Activity
import android.app.FragmentManager
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.beatmaker.R
import java.lang.Exception

class Utils {

    // for keyboard hide
    fun hideKeyboardFrom(activity: Activity) {
        // Check if no view has focus:
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    //camra
    fun hasFeatureCamera(context: Context): Boolean {
        val pm = context.packageManager
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    companion object {
        private var progressDialog: ProgressDialog? = null


        fun isNetworkConnected(activity: Activity): Boolean {
            val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }



        fun homeActivitychangeFragment(context: Context, fragment: Fragment?) {
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(android.R.id.content, fragment!!)
                .addToBackStack(null).commitAllowingStateLoss()
        }
        fun homeActivitychangeFragment2(context: Context, fragment: Fragment?) {
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.home_container, fragment!!)
                .addToBackStack(null).commitAllowingStateLoss()
        }

        fun showDialogMethod(activity: Activity?, supportFragmentManager: FragmentManager?) {
            progressDialog = ProgressDialog(activity)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setMessage("Wait while loading...")
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog!!.show()
        }

        fun hideDialog() {
            try {
                if (progressDialog != null) {
                    progressDialog!!.dismiss()
                }
            } catch (e: Exception) {
            }
        }

        fun showMessage(activity: Activity?, message: String?) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        private val activity: Activity? = null


        val USERLOGIN = "user_login"
        val USERNAME = "username"
        val USEREMAIL = "useremail"
        val USERPHOTO = "userphoto"
        val LOGINTYPE = "logintype"
        val USERID = "userid"
        val TOKEN = "token"
        val DEVICETOKEN = "deviceToken"
        val HEADERSTOKEN = "headerstoken"

        val CONTACTNUMBER = "contactNumber"
        val ADDRESS = "address"
        val CITY = "city"
        val STATE = "state"
        val COUNTRY = "country"

        val GETFULLADDRESS = "fulladdress"
        val GETADDRESS = "getaddress"
        val GETSTATE = "getstate"
        val GETADDRESSID = "getaddressid"
        val GETCITY = "getcity"
        val GENDERSELECT = "genderSelect"
        val NAME = "name"
        val ABOUTME = "aboutme"
        val ANOTHERUSERID = "anotheruserid"
        val FORGOTPASSWORDTOKEN = "forgotpasswordtoken"
        val PROFILEIDANOTHERUSER = "profileidanotheruser"

        val ImageBaseURL = "http://13.54.226.124/"
        val BOOLEANVALUE = "booleanvalue"


        val URL_CHAT_SERVER = "http://3.136.56.91:8001"

        val FACEBOOKTOKEN = "facebooktoken"
        val ROLE = "role"
        val CATEGORYID = "categoryid"
        val FILTERCATEGORYID = "filtercategoryid"
        val FILTERLOCATION = "filterlocation"



    }
}