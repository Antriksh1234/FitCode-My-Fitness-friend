package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PlanActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    var modeGroup: RadioGroup? = null
    var dayGroup: RadioGroup? = null
    var passedToHomeScreen = false
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        passedToHomeScreen = true
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        modeGroup = findViewById(R.id.moderadiogroup)
        dayGroup = findViewById(R.id.dayradiogroup)
        sharedPreferences = getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", MODE_PRIVATE)
    }

    fun EditPlan(view: View?) {
        //Editing mode plan
        if (modeGroup!!.checkedRadioButtonId == R.id.beginner) sharedPreferences!!.edit().putBoolean("isBeginner", true).apply() else sharedPreferences!!.edit().putBoolean("isBeginner", false).apply()

        //Editing day plan
        if (dayGroup!!.checkedRadioButtonId == R.id.duration_fifteen_days) sharedPreferences!!.edit().putInt("days", 15).apply() else if (dayGroup!!.checkedRadioButtonId == R.id.duration_thirty_days) sharedPreferences!!.edit().putInt("days", 30).apply() else if (dayGroup!!.checkedRadioButtonId == R.id.duration_fortyfive_days) sharedPreferences!!.edit().putInt("days", 45).apply() else if (dayGroup!!.checkedRadioButtonId == R.id.duration_sixty_days) sharedPreferences!!.edit().putInt("days", 60).apply() else if (dayGroup!!.checkedRadioButtonId == R.id.duration_ninety_days) sharedPreferences!!.edit().putInt("days", 90).apply()
        val days = sharedPreferences!!.getInt("days", 15)
        val sqLiteDatabase = this.openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)
        sqLiteDatabase.execSQL("DELETE FROM ${Constants.exerciseTableName}")
        for (i in 1..days) {
            sqLiteDatabase.execSQL("INSERT INTO ${Constants.exerciseTableName}(done) VALUES ('Pending')")
        }
        passedToHomeScreen = !sharedPreferences!!.getBoolean(Constants.firstTimeKey, false)
        sharedPreferences!!.edit().putBoolean(Constants.firstTimeKey, false).apply()
        val calendar = Calendar.getInstance()
        val previousDay = calendar[Calendar.DATE]
        val previousMonth = calendar[Calendar.MONTH]
        val previousYear = calendar[Calendar.YEAR]
        sharedPreferences!!.edit().putInt("Date", previousDay).apply()
        sharedPreferences!!.edit().putInt("Month", previousMonth).apply()
        sharedPreferences!!.edit().putInt("Year", previousYear).apply()
        sharedPreferences!!.edit().putInt("day_number", 1).apply()
        Toast.makeText(this, "Fitness regime changed", Toast.LENGTH_SHORT).show()
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}