package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FinishedActivity : AppCompatActivity() {

    fun goHome(view: View?) {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished)
    }
}