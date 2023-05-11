package com.beatmaker.Activities.SplashActivity2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.beatmaker.Activities.SplashActivity3.SplashActivity3
import com.beatmaker.R

class SplashActivity2 : AppCompatActivity(), View.OnClickListener {
    lateinit var btn_continue:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        init()
        listeners()
    }
    private fun init() {
        btn_continue = findViewById(R.id.btn_continue)

    }
    private fun listeners() {
        btn_continue.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_continue->{
                var intent = Intent(this,SplashActivity3::class.java)
                startActivity(intent)

            }


        }
    }
}