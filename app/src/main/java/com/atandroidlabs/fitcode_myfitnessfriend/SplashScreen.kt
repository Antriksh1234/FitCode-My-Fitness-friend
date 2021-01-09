package com.atandroidlabs.fitcode_myfitnessfriend

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.*

class SplashScreen : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val headerTextView: TextView = findViewById(R.id.app_heading)
        val subheading = findViewById<TextView>(R.id.sub_heading)
        val view: View = findViewById(R.id.splash_view)

        val sqLiteDatabase = this.openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ${Constants.exerciseTableName}(day_id INTEGER PRIMARY KEY ,done VARCHAR)")
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ${Constants.bmiTableName}(month VARCHAR, year INTEGER , bmi VARCHAR)")

        headerTextView.alpha = 0f
        subheading.alpha = 0f
        view.alpha = 0f

        headerTextView.translationY = 150f
        subheading.translationY = 150f

        headerTextView.animate().alpha(1f).translationYBy(-150f).duration = 1500
        subheading.animate().alpha(1f).translationYBy(-150f).duration = 1500
        view.animate().alpha(1f).duration = 1500

        val handler: Handler = Handler()

        handler.postDelayed(Runnable {
            val sp: SharedPreferences = applicationContext.getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", MODE_PRIVATE)
            val firstTime: Boolean = sp.getBoolean(Constants.firstTimeKey, true)
            finish()
            val intent: Intent
            if (firstTime) {
                intent = Intent(this@SplashScreen, BodyActivity::class.java)
                val calendar = Calendar.getInstance()
                val previousDay = calendar[Calendar.DATE]
                val previousMonth = calendar[Calendar.MONTH]
                val previousYear = calendar[Calendar.YEAR]
                sp.edit().putInt(Constants.dateKey, previousDay).apply()
                sp.edit().putInt(Constants.monthKey, previousMonth).apply()
                sp.edit().putInt(Constants.yearKey, previousYear).apply()
            } else {
                intent = Intent(this@SplashScreen, MainActivity::class.java)
            }
            startActivity(intent)
        }, 3000)
    }
}