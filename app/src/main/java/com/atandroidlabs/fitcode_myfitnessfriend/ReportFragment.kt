package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ReportFragment : Fragment() {

    lateinit var currentWeightTextView: TextView
    lateinit var maxWeightTextView: TextView
    lateinit var minWeightTextView: TextView
    lateinit var userBMITextView: TextView
    lateinit var consistencyTextView: TextView
    lateinit var changeDetails: LinearLayout
    lateinit var bmiHistory: LinearLayout
    lateinit var videoActivityTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_reports, container, false)
        val sp: SharedPreferences = requireContext().getSharedPreferences(
            "com.atandroidlabs.fitcode_myfitnessfriend",
            Context.MODE_PRIVATE
        )

        currentWeightTextView = view.findViewById(R.id.current_weight)
        maxWeightTextView = view.findViewById(R.id.max_weight)
        minWeightTextView = view.findViewById(R.id.min_weight)
        userBMITextView = view.findViewById(R.id.user_bmi)
        consistencyTextView = view.findViewById(R.id.user_consistency)
        changeDetails = view.findViewById(R.id.change_weight_height)
        bmiHistory = view.findViewById(R.id.view_bmi_history)
        videoActivityTextView = view.findViewById(R.id.video_Activity_text)

        videoActivityTextView.setOnClickListener {
            val intent = Intent(requireContext(), VideoActivity::class.java)
            startActivity(intent)
        }

        changeDetails.setOnClickListener {
            activity?.finish()
            val intent: Intent = Intent(context, BodyActivity::class.java)
            startActivity(intent)
        }

        bmiHistory.setOnClickListener {
            val intent: Intent = Intent(context, BmiHistory::class.java)
            startActivity(intent)
        }

        val maxWeight = sp.getFloat("max_weight", 0f)
        val minWeight = sp.getFloat("min_weight", 0f)
        val currentWeight = sp.getFloat("weight", 0f)
        val currentHeight = sp.getFloat("height", 0f)
        val dayNumber = sp.getInt("day_number", 1)
        val days = sp.getInt("days", 15)

        val BMI = currentWeight / (currentHeight / 100f * currentHeight / 100f)

        currentWeightTextView.text = "Current Weight - $currentWeight kg"
        minWeightTextView.text = "Minimum Weight - $minWeight kg"
        maxWeightTextView.text = "Maximum Weight - $maxWeight kg"
        userBMITextView.text = String.format("%.1f", BMI)

        val database =
            requireContext().openOrCreateDatabase(Constants.dbName, Context.MODE_PRIVATE, null)
        val c = database.rawQuery("SELECT * FROM ${Constants.exerciseTableName}", null)
        val doneIndex = c.getColumnIndex("done")

        var doneTimes = 0;
        c.moveToFirst()

        while (!c.isAfterLast) {
            if (c.getString(doneIndex).contentEquals("Yes")) {
                doneTimes++
            }
            c.moveToNext()
        }

        if (dayNumber > days) {
            consistencyTextView.text = "$doneTimes/$days"
        } else
            consistencyTextView.text = "$doneTimes/$dayNumber"

        return view
    }

}