package com.example.hand_hackmackers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class JuegoNum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_num)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


    }
}