package com.beatmaker.Activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.beatmaker.Activities.SplashActivity2.SplashActivity2
import com.beatmaker.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_continue: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listeners()

        FirebaseApp.initializeApp(this);



        /*FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result

                Log.d(TAG, token)

            })*/

    }

    private fun init() {
        btn_continue = findViewById(R.id.btn_continue)

    }

    private fun listeners() {
        btn_continue.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_continue -> {
                var intent = Intent(this, SplashActivity2::class.java)
                startActivity(intent)

            }
        }
    }
}