package com.xmethodius.stormapp.ui.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xmethodius.stormapp.R
import com.xmethodius.stormapp.ui.fragments.EmployeeDetailFragment
import com.xmethodius.stormapp.ui.fragments.OfficeDetailFragment


class DetailActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var fragment: Fragment

    private val videoURL: String = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        videoView = findViewById(R.id.video)

        getExtra()
        supportFragmentManager.beginTransaction().add(R.id.detail_container, fragment).commit()
        startVideo()
    }
    
    private fun startVideo() {
        try {
        if(videoView.isPlaying) {
            val uri: Uri = Uri.parse(videoURL)
            videoView.setVideoURI(uri)
            videoView.setOnCompletionListener {
                Log.d("log-", "completion")
            }

        } else {
                videoView.pause()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
        }
        videoView.requestFocus()
        videoView.setOnPreparedListener {
            it.isLooping = true
            videoView.start()
        }
    }

    private fun checkExtra(source: String) {
        if(source.equals("employee"))
            fragment = EmployeeDetailFragment()
        else if(source.equals("office"))
            fragment = OfficeDetailFragment()
    }

    private fun getExtra() {
        val intent = intent
        val source: String? = intent.getStringExtra("source")

        if (source != null) {
            checkExtra(source)
        }
    }

}