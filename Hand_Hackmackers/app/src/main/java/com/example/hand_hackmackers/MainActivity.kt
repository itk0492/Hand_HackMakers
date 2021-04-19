package com.example.hand_hackmackers

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


        val leccionAbc = findViewById(R.id.leccionAbc) as ImageView
        val leccionNum = findViewById(R.id.leccion123) as ImageView

        leccionAbc.setOnClickListener {
           // leccionAbc.setColorFilter(0x206a5d.toInt(), PorterDuff.Mode.MULTIPLY);
            val intent = Intent(this@MainActivity, Leccion::class.java)
            intent.putExtra("leccion_valor", 1);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked ABC", Toast.LENGTH_SHORT).show()
        }


        leccionNum.setOnClickListener {
            //leccionNum.setColorFilter(0x206a5d.toInt(), PorterDuff.Mode.MULTIPLY);
            val intent = Intent(this@MainActivity, Leccion::class.java)
            intent.putExtra("leccion_valor", 2);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }


    }
}