package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var planTextView: TextView
    lateinit var startWorkoutCardView: CardView
    lateinit var adapter: DayAdapter
    var day_number = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recycler_days)
        planTextView = view.findViewById(R.id.workout_description)
        startWorkoutCardView = view.findViewById(R.id.card_start_workout)

        startWorkoutCardView.setOnClickListener {
            activity?.finish()
            val intent = Intent(activity, TodayExerciseActivity::class.java)
            startActivity(intent)
        }

        val sp = context?.getSharedPreferences(
            "com.atandroidlabs.fitcode_myfitnessfriend",
            Context.MODE_PRIVATE
        )

        if (sp!!.getBoolean("doneToday", false)) {
            startWorkoutCardView.isEnabled = false
        }

        day_number = sp.getInt("day_number", 1)

        val list = ArrayList<Int>()

        val no_of_days = sp.getInt("days", 15)

        var planText = "Your plan - "
        if (sp.getBoolean("isBeginner",true)) {
            planText += "Beginner for $no_of_days days"
        } else
            planText += "Advanced for $no_of_days days"

        planTextView.text = planText
        list.clear()

        for (i in 1..no_of_days) {
            list.add(i)
        }

        val sqLiteDatabase = requireContext().openOrCreateDatabase(
            Constants.dbName,
            Context.MODE_PRIVATE,
            null
        )

        val calendar = Calendar.getInstance()

        val previousOpenCalendar = Calendar.getInstance()

        val date = sp.getInt("Date", 0)
        val month = sp.getInt("Month", 0)
        val year = sp.getInt("Year", 0)

        previousOpenCalendar[Calendar.DATE] = date
        previousOpenCalendar[Calendar.MONTH] = month
        previousOpenCalendar[Calendar.YEAR] = year

        val doneList = ArrayList<String>()

        doneList.clear()

        var c = sqLiteDatabase.rawQuery("SELECT * FROM ${Constants.exerciseTableName}", null)

        var doneIndex = c.getColumnIndex("done")

        c.moveToFirst()

        while (!c.isAfterLast) {
            doneList.add(c.getString(doneIndex))
            c.moveToNext()
        }

        if (day_number >= list.size) {
            Toast.makeText(context, "Reset your plan please!", Toast.LENGTH_SHORT).show()
        } else {
            while (calendar.after(previousOpenCalendar)) {
                previousOpenCalendar.add(Calendar.DATE, 1)
                if (doneList[day_number - 1].contentEquals("Pending")) sqLiteDatabase.execSQL("UPDATE exerciseTable set done = 'No' WHERE day_id = $day_number")
                day_number++
            }
            if (doneList[day_number - 1].contentEquals("Yes")) {
                startWorkoutCardView.isEnabled = false
            }
        }

        sp.edit().putInt("Date", calendar[Calendar.DATE]).apply()
        sp.edit().putInt("Month", calendar[Calendar.MONTH]).apply()
        sp.edit().putInt("Year", calendar[Calendar.YEAR]).apply()

        sp.edit().putInt("day_number", day_number).apply()

        doneList.clear()

        c = sqLiteDatabase.rawQuery("SELECT * FROM ${Constants.exerciseTableName}", null)

        doneIndex = c.getColumnIndex("done")

        c.moveToFirst()

        while (!c.isAfterLast) {
            doneList.add(c.getString(doneIndex))
            c.moveToNext()
        }

        val dayList = ArrayList<Day>()

        for (i in 0 until doneList.size) {
            val day: Day = Day(list[i], doneList[i])
            dayList.add(day)
        }

        adapter = DayAdapter(requireContext(), dayList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        c.close()

        return view
    }

}