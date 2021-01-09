package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BodyActivity : AppCompatActivity() {
    var heightText: EditText? = null
    var weightText: EditText? = null
    var passedToHomeScreen = false

    override fun onBackPressed() {
        val sp = getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", MODE_PRIVATE)
        val firstTime = sp.getBoolean(Constants.firstTimeKey, true)
        if (firstTime) {
            finish()
        } else {
            finish()
            val intent: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)
        weightText = findViewById<EditText>(R.id.editText2)
        heightText = findViewById(R.id.editText3)
    }

    fun SaveBodyDim(view: View?) {
        val sp = getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", MODE_PRIVATE)
        val firstTime = sp.getBoolean(Constants.firstTimeKey, true)

        if (weightText?.text
                .toString().isNotEmpty() && heightText!!.text.toString()
                .isNotEmpty() && heightText!!.text.toString()[heightText!!.text.toString().length - 1] != '.' && weightText?.getText()
                .toString().get(weightText?.text.toString().length - 1) != '.'
        ) {
            val weight: Float = weightText?.text.toString().toFloat()
            val height = heightText!!.text.toString().toFloat()

            val weightAddedBefore = sp.getFloat("weight", 0f)
            if (weightAddedBefore == 0f) {
                val sqLiteDatabase = openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)
                val calendar = Calendar.getInstance()
                val month = calendar[Calendar.MONTH] + 1
                val year = calendar[Calendar.YEAR]
                val bmi = weight / (height / 100f * (height / 100f))
                val bmiString = bmi.toString()
                var monthString = ""
                when (month) {
                    1 -> monthString = "January"
                    2 -> monthString = "February"
                    3 -> monthString = "March"
                    4 -> monthString = "April"
                    5 -> monthString = "May"
                    6 -> monthString = "June"
                    7 -> monthString = "July"
                    8 -> monthString = "August"
                    9 -> monthString = "September"
                    10 -> monthString = "October"
                    11 -> monthString = "November"
                    12 -> monthString = "December"
                }
                sqLiteDatabase.execSQL("INSERT INTO ${Constants.bmiTableName} (month,year,bmi) VALUES ('$monthString',$year,'$bmiString')")
            }

            sp.edit().putFloat("height", height).apply()
            sp.edit().putFloat("weight", weight).apply()
            if (firstTime) {
                sp.edit().putFloat("min_weight", weight).apply()
                sp.edit().putFloat("max_weight", weight).apply()
                finish()
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PlanActivity::class.java)
                startActivity(intent)
            } else {
                val maxWeight = sp.getFloat("max_weight", 0f)
                val minWeight = sp.getFloat("min_weight", 0f)
                val sqLiteDatabase = openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)
                val calendar = Calendar.getInstance()
                val month = calendar[Calendar.MONTH] + 1
                val year = calendar[Calendar.YEAR]
                val bmi = weight / (height / 100f * (height / 100f))
                val bmiString = bmi.toString()
                var monthString = ""
                when (month) {
                    1 -> monthString = "January"
                    2 -> monthString = "February"
                    3 -> monthString = "March"
                    4 -> monthString = "April"
                    5 -> monthString = "May"
                    6 -> monthString = "June"
                    7 -> monthString = "July"
                    8 -> monthString = "August"
                    9 -> monthString = "September"
                    10 -> monthString = "October"
                    11 -> monthString = "November"
                    12 -> monthString = "December"
                }
                sqLiteDatabase.execSQL("INSERT INTO ${Constants.bmiTableName} (month,year,bmi) VALUES ('$monthString',$year,'$bmiString')")

                //Updating max weight and  min weight values
                if (minWeight > weight) sp.edit().putFloat("min_weight", weight)
                    .apply() else if (maxWeight < weight) sp.edit().putFloat("max_weight", weight)
                    .apply()
                finish()
                passedToHomeScreen = true
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Height weight updated", Toast.LENGTH_SHORT).show()
            }
        } else Toast.makeText(this, "Please enter complete details", Toast.LENGTH_SHORT).show()
    }
}