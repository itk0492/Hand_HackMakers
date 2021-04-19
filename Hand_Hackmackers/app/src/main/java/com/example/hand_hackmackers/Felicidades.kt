package com.example.hand_hackmackers

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_felicidades.*

class Felicidades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_felicidades)

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        val siguiente = findViewById(R.id.button) as Button

        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
                .streamFor(300, 5000L)

        siguiente.setOnClickListener{
            val intent = Intent(this@Felicidades,MainActivity::class.java)
            intent.putExtra("leccion_valor", 2);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }

        fun onBackPressed() {
            super.onBackPressed()
            val intent = Intent(this@Felicidades,Leccion::class.java)
            intent.putExtra("leccion", 2)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }

    }

}