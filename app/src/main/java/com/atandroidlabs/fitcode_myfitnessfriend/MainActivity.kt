package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.ibrahimsn.lib.OnItemSelectedListener
import me.ibrahimsn.lib.SmoothBottomBar
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var smoothBottomBar: SmoothBottomBar

    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smoothBottomBar = findViewById(R.id.bottom_nav)

        fragment = HomeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

        smoothBottomBar.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelect(pos: Int): Boolean {
                if (pos == 0) {
                    fragment = HomeFragment()
                } else if (pos == 1) {
                    fragment = MeFragment()
                } else if (pos == 2) {
                    fragment = ReportFragment()
                } else {
                    fragment = HomeFragment()
                }

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()

                return false
            }
        }

    }
}