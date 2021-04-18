package com.example.hand_hackmackers

import android.content.Intent
import android.graphics.PorterDuff
//import androidx.appcompat.app.AppCompatActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
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
        val iconoLeccAbc = findViewById(R.id.button) as Button
        val iconoJuegoAbc = findViewById(R.id.button2) as Button
        val iconoLeccNum = findViewById(R.id.button3) as Button
        val iconoJuegoNum = findViewById(R.id.button4) as Button

        if(leccion==1)
        {
            titulo.text="Lección Abecedario"
            //iconoLeccAbc.isVisible=true
            //iconoJuegoAbc.isVisible=true
            //iconoLeccNum.isVisible=false
            //iconoJuegoNum.isVisible=false
            iconoLeccAbc.visibility= View.VISIBLE
            iconoJuegoAbc.visibility = View.VISIBLE
            iconoLeccNum.visibility = View.GONE
            iconoJuegoNum.visibility = View.GONE
        }
        else
        {
            titulo.text="Lección Números"
            //iconoLeccAbc.isVisible=false
            //iconoJuegoAbc.isVisible=false
            //iconoLeccNum.isVisible=true
            //iconoJuegoNum.isVisible=true
            iconoLeccAbc.visibility= View.GONE
            iconoJuegoAbc.visibility = View.GONE
            iconoLeccNum.visibility = View.VISIBLE
            iconoJuegoNum.visibility = View.VISIBLE

        }

        iconoLeccAbc.setOnClickListener {
            val intent = Intent(this@Leccion, Leccion_AR::class.java)
            intent.putExtra("leccion_valor", 1);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }

        iconoLeccNum.setOnClickListener {
            val intent = Intent(this@Leccion, Leccion_AR::class.java)
            intent.putExtra("leccion_valor", 2);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }

        iconoJuegoAbc.setOnClickListener {
            val intent = Intent(this@Leccion, JuegoNum::class.java)
            intent.putExtra("leccion_valor", 1);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }

        iconoJuegoNum.setOnClickListener {
            val intent = Intent(this@Leccion, JuegoNum::class.java)
            intent.putExtra("leccion_valor", 2);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            //Toast.makeText(this@MainActivity, "You clicked 123", Toast.LENGTH_SHORT).show()
        }
    }
}