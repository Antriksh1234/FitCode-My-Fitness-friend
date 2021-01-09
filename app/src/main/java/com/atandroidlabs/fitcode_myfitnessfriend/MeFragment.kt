package com.atandroidlabs.fitcode_myfitnessfriend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var changePlanBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_me, container, false)

        val plan_Text = view.findViewById<TextView>(R.id.plan_text)
        recyclerView = view.findViewById(R.id.profileRecyclerView)
        changePlanBtn = view.findViewById(R.id.button2)

        changePlanBtn.setOnClickListener {
            activity?.finish()
            val intent = Intent(context, PlanActivity::class.java)
            startActivity(intent)
        }
        val heading = ArrayList<String>()
        heading.add("Plan ")
        heading.add("Duration ")
        heading.add("My Height ")
        heading.add("My Weight ")

        val descrip = ArrayList<String>()

        val sharedPreferences = requireContext().getSharedPreferences(
            "com.atandroidlabs.fitcode_myfitnessfriend",
            Context.MODE_PRIVATE
        )

        val isBeginner = sharedPreferences.getBoolean("isBeginner", true)
        if (isBeginner) {
            plan_Text.text = "My plan - Beginner Plan"
            descrip.add("Beginner")
        } else {
            plan_Text.text = "My plan - Advanced Plan"
            descrip.add("Advanced")
        }
        when (sharedPreferences.getInt("days", 15)) {
            15 -> descrip.add("15 days")
            30 -> descrip.add("30 days")
            45 -> descrip.add("45 days")
            60 -> descrip.add("60 days")
            90 -> descrip.add("90 days")
        }

        descrip.add(java.lang.Float.toString(sharedPreferences.getFloat("height", 0f)) + " cm")
        descrip.add(java.lang.Float.toString(sharedPreferences.getFloat("weight", 0f)) + " kg")

        val adapter: ProfileAdapter = ProfileAdapter(requireContext(), heading, descrip)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        return view
    }

}