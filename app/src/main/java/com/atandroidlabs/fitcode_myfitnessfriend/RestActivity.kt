package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RestActivity : AppCompatActivity() {

    override fun onBackPressed() {
        //Don't allow user to go back on back pressed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val break_timer_text = findViewById<TextView>(R.id.break_timer)

        val timer = object : CountDownTimer(40000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var timer_text = ""
                val seconds = millisUntilFinished.toInt() / 1000
                timer_text = if (seconds < 10) "0:0$seconds" else "0:$seconds"
                break_timer_text.text = timer_text
            }

            override fun onFinish() {
                break_timer_text.text = "0:00"
                finish()
                val intent = Intent(this@RestActivity, ExerciseActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}