package com.atandroidlabs.fitcode_myfitnessfriend

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*
import kotlin.collections.ArrayList

class BmiHistory : AppCompatActivity() {

    private lateinit var timePeriod: ArrayList<String>
    private lateinit var bmiRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_history)

        bmiRecyclerView = findViewById(R.id.bmi_history_recyclerview)

        timePeriod = ArrayList<String>()
        val bmi = ArrayList<String>()

        val database = openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)

        val c = database.rawQuery("SELECT * FROM ${Constants.bmiTableName}", null)

        val monthIndex = c.getColumnIndex("month")
        val yearIndex = c.getColumnIndex("year")
        val bmiIndex = c.getColumnIndex("bmi")

        c.moveToFirst()

        while (!c.isAfterLast) {
            val month = c.getString(monthIndex)
            val year = c.getInt(yearIndex)
            val bmiString = c.getString(bmiIndex)
            //Setting the timePeriod text and adding it
            timePeriod.add("$month $year")
            //Adding BMI of that period to ArrayList BMI
            bmi.add(bmiString)
            c.moveToNext()
        }

        val adapter = BmiHistoryAdapter(applicationContext, timePeriod, bmi)
        bmiRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bmiRecyclerView.adapter = adapter

    }

}