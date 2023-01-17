package com.example.milkaggregatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import android.widget.VideoView
import android.media.MediaPlayer.OnPreparedListener
import android.media.MediaPlayer
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.view.View
import com.example.milkaggregatorapplication.MainActivity
import com.example.milkaggregatorapplication.spash

class spash : AppCompatActivity() {
    //    LottieAnimationView lottie;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        val videoview = findViewById<View>(R.id.video) as VideoView
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.videologo)
        videoview.setVideoURI(uri)
        videoview.setBackgroundColor(Color.rgb(243, 248, 243))
        videoview.setOnPreparedListener {
            videoview.setZOrderOnTop(true)
            videoview.start()
        }

//        lottie=findViewById(R.id.lottie);


//        lottie.animate().translationX(3000).setDuration(3000).setStartDelay(3100);
        Handler().postDelayed({
            val i = Intent(
                this@spash,
                MainActivity::class.java
            )
            //Intent is used to switch from one activity to another.
            startActivity(i)
            //invoke the SecondActivity.
            finish()
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT.toLong())
    }

    companion object {
        //
        private const val SPLASH_SCREEN_TIME_OUT = 3000
    }
}