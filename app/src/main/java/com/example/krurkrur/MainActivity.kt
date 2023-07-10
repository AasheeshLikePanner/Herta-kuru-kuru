package com.example.krurkrur

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import pl.droidsonroids.gif.GifImageView
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private lateinit var imageAnimator: ObjectAnimator
    private lateinit var mediaPlayer: MediaPlayer
    var touch:Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val but:Button = findViewById(R.id.but);
        val score:TextView = findViewById(R.id.score)
        but.setBackgroundColor(resources.getColor(R.color.white))
        val anim1:GifImageView = findViewById(R.id.anime)
        val anim2:GifImageView = findViewById(R.id.anime2)
        var one = true
        var two = true
        but.setOnClickListener {
            touch()
            score.text = (score.text.toString().toInt() + 1).toString()
            when ((1 .. 2).random()){
                1 -> {
                    sound()
                    if(one){
                        anim1.isVisible = !anim1.isVisible
                        one = false
                    }
                    anim(anim1)
                }
                2 -> {
                    sound()
                    if(two){
                        anim2.isVisible = !anim2.isVisible
                        two = false
                    }
                    anim(anim2)
                }
            }
        }
    }
    fun touch(){
        if(touch == 3) {
            Toast.makeText(this ,"You can't Kuru Kuru more Because\nYou touch herat too many times.", Toast.LENGTH_LONG).show()
            exitProcess( -1)
        }
    }
    fun anim(an:GifImageView){
        an.translationX = -700f
        imageAnimator = ObjectAnimator.ofFloat(an, "translationX", -780f, 850f).apply {
            duration = 1000
        }
        imageAnimator.start()
        an.setOnClickListener { touch++; }
    }
    fun sound(){
        when((1..3).random()){
            1 ->    {
                mediaPlayer = MediaPlayer.create(this, R.raw.static_audio_ja_kuru1)
                mediaPlayer.start()
            }
            2 ->    {
                mediaPlayer = MediaPlayer.create(this, R.raw.static_audio_ja_kuruto)
                mediaPlayer.start()
            }
            3 ->    {
                mediaPlayer = MediaPlayer.create(this, R.raw.static_audio_ja_kuru2)
                mediaPlayer.start()
            }
        }
    }
}
