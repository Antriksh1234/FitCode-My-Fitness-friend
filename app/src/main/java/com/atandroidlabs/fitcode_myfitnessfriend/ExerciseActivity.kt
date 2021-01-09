package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExerciseActivity : AppCompatActivity() {

    lateinit var exercise_name: TextView
    lateinit var timer_text:TextView
    lateinit var exercise_photo: ImageView
    lateinit var mediaPlayer: MediaPlayer
    var duration: Long = 60000

    override fun onBackPressed() {
        //Don't allow to go back on pressing back button
    }

    private fun startCountDownTimer(duration: Long) {
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.music)
        val timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var text_of_timer = ""
                val minutes = millisUntilFinished.toInt() / 60000
                val seconds = (millisUntilFinished % 60000) as Int / 1000
                val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
                mediaPlayer.start()
                var secondString = ""
                secondString = if (seconds < 10) {
                    "0$seconds"
                } else Integer.toString(seconds)
                text_of_timer = "$minutes:$secondString"
                timer_text.setText(text_of_timer)
            }

            override fun onFinish() {
                timer_text.text = "0:00"
                TodayExerciseActivity.no_of_exercise_done_today++
                finish()
                val intent: Intent
                mediaPlayer.pause()
                intent = if (TodayExerciseActivity.no_of_exercise_done_today === 5) {
                    val sqLiteDatabase = openOrCreateDatabase(Constants.dbName, MODE_PRIVATE, null)
                    val sp = getSharedPreferences(
                        "com.atandroidlabs.fitcode_myfitnessfriend",
                        MODE_PRIVATE
                    )
                    val day_number = sp.getInt("day_number", 1)
                    sqLiteDatabase.execSQL("UPDATE ${Constants.exerciseTableName} set done = 'Yes' WHERE day_id = $day_number")
                    Intent(this@ExerciseActivity, FinishedActivity::class.java)
                } else {
                    Intent(this@ExerciseActivity, RestActivity::class.java)
                }
                startActivity(intent)
            }
        }.start()
    }

    fun startTimer(view: View) {
        val go_button = view as Button
        go_button.isEnabled = false
        startCountDownTimer(duration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        exercise_name = findViewById(R.id.exercise_name)
        timer_text = findViewById(R.id.timer_text)
        exercise_photo = findViewById(R.id.exercise_photo)
        val go_button = findViewById<Button>(R.id.button4)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        exercise_name.setText(TodayExerciseActivity.exerciseList.get(TodayExerciseActivity.no_of_exercise_done_today))

        timer_text.setText(TodayExerciseActivity.durationList.get(TodayExerciseActivity.no_of_exercise_done_today))

        when (TodayExerciseActivity.durationList.get(TodayExerciseActivity.no_of_exercise_done_today)) {
            "1:00 Minute" -> duration = 60000
            "2:00 Minutes" -> duration = 120000
            "3:00 Minutes" -> duration = 180000
        }

        if (TodayExerciseActivity.no_of_exercise_done_today >= 1) {
            go_button.visibility = View.INVISIBLE
            startCountDownTimer(duration)
        }

        when (TodayExerciseActivity.exerciseList.get(TodayExerciseActivity.no_of_exercise_done_today)) {
            "Push Ups" -> exercise_photo.setImageResource(R.drawable.pushups)
            "Pull Ups" -> exercise_photo.setImageResource(R.drawable.pullups)
            "Cobra Stretch" -> exercise_photo.setImageResource(R.drawable.cobra)
            "Crunches" -> exercise_photo.setImageResource(R.drawable.crunches)
            "Plank" -> exercise_photo.setImageResource(R.drawable.plank)
            "Lunges" -> exercise_photo.setImageResource(R.drawable.lunges)
            "Side Plank" -> exercise_photo.setImageResource(R.drawable.sideplank)
            "Squats" -> exercise_photo.setImageResource(R.drawable.squats)
            "Skipping" -> exercise_photo.setImageResource(R.drawable.skipping)
        }
    }
}