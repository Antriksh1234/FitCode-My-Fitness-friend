package com.atandroidlabs.fitcode_myfitnessfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoActivity : AppCompatActivity() {

    lateinit var tipsList: ArrayList<String>
    lateinit var recyclerView: RecyclerView
    lateinit var videoLinkList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        tipsList = ArrayList()
        videoLinkList = ArrayList()

        tipsList.add("Squats")
        tipsList.add("Push ups")
        tipsList.add("Pull ups")
        tipsList.add("Cobra stretch")
        tipsList.add("Plank")
        tipsList.add("Crunches")
        tipsList.add("Skipping")
        tipsList.add("Lunges")
        tipsList.add("Side Plank")
        tipsList.add("Nutrition for healthy life")
        tipsList.add("Exercise vs Diet")

        videoLinkList.add("https://www.youtube.com/watch?v=YaXPRqUwItQ")
        videoLinkList.add("https://www.youtube.com/watch?v=t0s5FHbdBmA")
        videoLinkList.add("https://www.youtube.com/watch?v=XeErfmGSwfE")
        videoLinkList.add("https://www.youtube.com/watch?v=jwoTJNgh8BY")
        videoLinkList.add("https://www.youtube.com/watch?v=BQu26ABuVS0")
        videoLinkList.add("https://www.youtube.com/watch?v=5ER5Of4MOPI")
        videoLinkList.add("https://www.youtube.com/watch?v=u3zgHI8QnqE")
        videoLinkList.add("https://www.youtube.com/watch?v=wrwwXE_x-pQ")
        videoLinkList.add("https://www.youtube.com/watch?v=rCxF2nG9vQ0")
        videoLinkList.add("https://www.youtube.com/watch?v=c06dTj0v0sM")
        videoLinkList.add("https://www.youtube.com/watch?v=ztiHRiFXtoc")

        recyclerView = findViewById(R.id.exercise_video_adapter)
        val adapter: VideoAdapter = VideoAdapter(applicationContext, tipsList, videoLinkList)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

    }
}