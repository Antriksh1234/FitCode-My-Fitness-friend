package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class TodayExerciseActivity : AppCompatActivity() {

    companion object {
        var exerciseList = ArrayList<String?>()
        var durationList = ArrayList<String?>()
        var no_of_exercise_done_today = 0
    }
    override fun onBackPressed() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun startExercise(view: View?) {
        finish()
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_exercise)

        val recyclerView: RecyclerView = findViewById(R.id.exercise_list)

        exerciseList = ArrayList<String?>()
        durationList = ArrayList<String?>()

        val hashMap = HashMap<String, String>()

        hashMap["Push Ups Beginner"] = "1:00 Minute"
        hashMap["Pull Ups Beginner"] = "1:00 Minute"
        hashMap["Cobra Stretch Beginner"] = "2:00 Minutes"
        hashMap["Crunches Beginner"] = "2:00 Minutes"
        hashMap["Plank Beginner"] = "1:00 Minute"
        hashMap["Lunges Beginner"] = "2:00 Minutes"
        hashMap["Side Plank Beginner"] = "2:00 Minutes"
        hashMap["Squats Beginner"] = "1:00 Minute"
        hashMap["Skipping Up Beginner"] = "2:00 Minutes"

        hashMap["Push Ups Advanced"] = "2:00 Minutes"
        hashMap["Pull Ups Advanced"] = "2:00 Minutes"
        hashMap["Cobra Stretch Advanced"] = "3:00 Minutes"
        hashMap["Crunches Advanced"] = "3:00 Minutes"
        hashMap["Plank Advanced"] = "2:00 Minute"
        hashMap["Lunges Advanced"] = "3:00 Minutes"
        hashMap["Side Plank Advanced"] = "3:00 Minutes"
        hashMap["Squats Advanced"] = "2:00 Minute"
        hashMap["Skipping Up Advanced"] = "3:00 Minutes"

        val exercise_no = ArrayList<Int>()
        var random_number: Int
        while (exercise_no.size <= 5) {
            random_number = Random().nextInt(9) + 1
            if (!exercise_no.contains(random_number)) {
                exercise_no.add(random_number)
            }
        }

        for (i in 1..5) {
            when (exercise_no[i]) {
                1 -> exerciseList.add("Push Ups")
                2 -> exerciseList.add("Pull Ups")
                3 -> exerciseList.add("Cobra Stretch")
                4 -> exerciseList.add("Crunches")
                5 -> exerciseList.add("Plank")
                6 -> exerciseList.add("Lunges")
                7 -> exerciseList.add("Side Plank")
                8 -> exerciseList.add("Squats")
                9 -> exerciseList.add("Skipping")
            }
        }

        val sharedPreferences = getSharedPreferences("com.atandroidlabs.fitcode_myfitnessfriend", MODE_PRIVATE)
        val isBeginner = sharedPreferences.getBoolean("isBeginner", true)

        if (isBeginner) {
            for (i in 1..5) {
                when (exercise_no[i]) {
                    1 -> durationList.add(hashMap["Push Ups Beginner"])
                    2 -> durationList.add(hashMap["Pull Ups Beginner"])
                    3 -> durationList.add(hashMap["Cobra Stretch Beginner"])
                    4 -> durationList.add(hashMap["Crunches Beginner"])
                    5 -> durationList.add(hashMap["Plank Beginner"])
                    6 -> durationList.add(hashMap["Lunges Beginner"])
                    7 -> durationList.add(hashMap["Side Plank Beginner"])
                    8 -> durationList.add(hashMap["Squats Beginner"])
                    9 -> durationList.add(hashMap["Skipping Up Beginner"])
                }
            }
        } else {
            for (i in 1..5) {
                when (exercise_no[i]) {
                    1 -> durationList.add(hashMap["Push Ups Advanced"])
                    2 -> durationList.add(hashMap["Pull Ups Advanced"])
                    3 -> durationList.add(hashMap["Cobra Stretch Advanced"])
                    4 -> durationList.add(hashMap["Crunches Advanced"])
                    5 -> durationList.add(hashMap["Plank Advanced"])
                    6 -> durationList.add(hashMap["Lunges Advanced"])
                    7 -> durationList.add(hashMap["Side Plank Advanced"])
                    8 -> durationList.add(hashMap["Squats Advanced"])
                    9 -> durationList.add(hashMap["Skipping Up Advanced"])
                }
            }
        }

        val adapter: MyExerciseAdapter = MyExerciseAdapter(applicationContext, exerciseList, durationList)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }
}