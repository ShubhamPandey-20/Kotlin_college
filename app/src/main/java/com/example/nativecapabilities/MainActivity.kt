package com.example.nativecapabilities

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.MediaPlayer.OnSeekCompleteListener
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ComponentActivity
import com.example.nativecapabilities.ui.theme.NativecapabilitiesTheme

@SuppressLint("RestrictedApi")
class MainActivity : ComponentActivity(), OnSeekBarChangeListener {
    var songs =  arrayOf(R.raw.song1, R.raw.song3, R.raw.song4, R.raw.song5);
    var current = 0;
    lateinit var media:MediaPlayer


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         media = MediaPlayer.create(applicationContext, songs[current])

        setContentView(R.layout.layout)
        val butplay = findViewById<Button>(R.id.button);
        val butPause= findViewById<Button>(R.id.button2);
        val butstop = findViewById<Button>(R.id.button3);
        val butnext = findViewById<Button>(R.id.button4);
        val butpre = findViewById<Button>(R.id.button5);
        val seek = findViewById<SeekBar>(R.id.Seekbar1);
        val tv1 = findViewById<TextView>(R.id.textView);
        val tv2 = findViewById<TextView>(R.id.textView2);

        seek.max = media.duration;

        seek.setOnSeekBarChangeListener(this)
        val handler = Handler()
        handler.postDelayed(object :Runnable {
            override fun run(){
                seek.progress = media.currentPosition
                handler.postDelayed(this, 1)
            }


        }, 0)





        butplay.setOnClickListener {
            if(media.isPlaying){
                media.pause();
               butplay.setText("Resume")

            }
            else {
                media.start();
               butplay.setText("Pause")
            }


        }
//        butPause.setOnClickListener {
//            media.pause();
//
//        }
        butstop.setOnClickListener {
           butplay.setText("Play")
            media.stop();
            media.prepare()
        }
       butnext.setOnClickListener {
           current++;
           media.stop()
           if(current<songs.size){


           }
           else{
               current = 0;
           }
           media = MediaPlayer.create(applicationContext , songs[current]);
           seek.progress = 0
           media.start();
           seek.max=media.duration
       }
        butpre.setOnClickListener {
            current--;
            media.stop()
            if(current<0){
                current = songs.size - 1;
            }


            media = MediaPlayer.create(applicationContext , songs[current]);
            media.start();
            seek.progress = 0
            seek.max=media.duration

        }





    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar) {
        media.seekTo(p0.progress)



       Toast.makeText(applicationContext , ""  +p0.progress, Toast.LENGTH_LONG).show();
    }
}