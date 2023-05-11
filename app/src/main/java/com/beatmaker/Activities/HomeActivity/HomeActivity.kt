package com.beatmaker.Activities.HomeActivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beatmaker.Fragments.CareerFragment.CareerFragment
import com.beatmaker.Fragments.CatalogFragment.CatalogFragment
import com.beatmaker.Fragments.MyMusic.MyMusicFragment
import com.beatmaker.Fragments.MyMusicJavaFragment
import com.beatmaker.Fragments.ProgressFragment.ProgressFragment
import com.beatmaker.Fragments.SettingFragment.SettingFragment
import com.beatmaker.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    val careerFragment = CareerFragment()
    val catalogFragment = CatalogFragment()
    val myMusicFragment = MyMusicFragment()
    val progressFragment = ProgressFragment()
    val settingFragment = SettingFragment()
    val myMusicJavaFragment = MyMusicJavaFragment()
    lateinit var home_container:FrameLayout
    lateinit var bottomnav:BottomNavigationView

    var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listener()
        setCurrentFragment(careerFragment)
        bottomnav = findViewById(R.id.bottomnav)
        bottomnav?.setOnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.career ->setCurrentFragment(careerFragment)
                R.id.catalog-> setCurrentFragment(catalogFragment)
                R.id.mymusic-> setCurrentFragment(myMusicJavaFragment)
                R.id.progress->setCurrentFragment(progressFragment)
                R.id.setting->setCurrentFragment(settingFragment)
//                R.id.setting->setCurrentFragment(myMusicJavaFragment)

//                R.id.chat -> setCurrentFragment(chatFragment)
//                R.id.setting -> setCurrentFragment(settingFragment)

            }
            true
        }

    }

    private fun init(){
        home_container = findViewById(R.id.home_container)

    }
    private fun listener(){

    }

    private  fun setCurrentFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}