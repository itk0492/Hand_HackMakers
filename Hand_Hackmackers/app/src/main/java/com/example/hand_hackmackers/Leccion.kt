package com.example.hand_hackmackers

import android.content.Intent
import android.graphics.PorterDuff
//import androidx.appcompat.app.AppCompatActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
//import androidx.core.content.ContextCompat
//import androidx.core.view.isVisible

class Leccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        val bundle = intent.extras
        val leccion = bundle?.get("leccion_valor")

        //Toast.makeText(this@Leccion, "" + leccion, Toast.LENGTH_SHORT).show()

        val titulo = findViewById(R.id.textView2) as TextView
        val iconoLecc = findViewById(R.id.imageView9) as ImageView
        val iconoJuego = findViewById(R.id.imageView10) as ImageView

        if(leccion==1)
        {
            titulo.text="Lección Abecedario"
            //iconoLeccAbc.isVisible=true
            //iconoJuegoAbc.isVisible=true
            //iconoLeccNum.isVisible=false
            //iconoJuegoNum.isVisible=false
            iconoLecc.setImageResource(R.drawable.boton_leccionra)
            iconoJuego.setImageResource(R.drawable.boton_juegoab)
        }
        else
        {
            titulo.text="Lección Números"
            //iconoLeccAbc.isVisible=false
            //iconoJuegoAbc.isVisible=false
            //iconoLeccNum.isVisible=true
            //iconoJuegoNum.isVisible=true
            iconoLecc.setImageResource(R.drawable.boton_leccionran)
            iconoJuego.setImageResource(R.drawable.boton_juegonu)

        }

        iconoLecc.setOnClickListener {
            //iconoLecc.setColorFilter(0x206a5d.toInt(), PorterDuff.Mode.MULTIPLY);
            if(leccion==1){
                val intent = Intent(this@Leccion, Leccion_AR::class.java)
                intent.putExtra("leccion_valor", 1)
                intent.putExtra("juego", 0)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@Leccion, Leccion_AR::class.java)
                intent.putExtra("leccion_valor", 2)
                intent.putExtra("juego", 0)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
            }
        }

        iconoJuego.setOnClickListener {
           // iconoJuego.setColorFilter(0x206a5d.toInt(), PorterDuff.Mode.MULTIPLY);
            if(leccion==1){
                val intent = Intent(this@Leccion, Leccion_AR::class.java)
                intent.putExtra("leccion_valor", 1)
                intent.putExtra("juego", 1)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@Leccion, JuegoNum::class.java)
                intent.putExtra("leccion_valor", 2)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Leccion,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}