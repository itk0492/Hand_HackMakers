package com.example.hand_hackmackers

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//import androidx.appcompat.app.AppCompatActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
//import androidx.core.view.isVisible
//import com.rommansabbir.animationx.Flip
//import com.rommansabbir.animationx.animationXFlip
import java.util.*


class JuegoNum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_num)
        var handler: Handler = Handler()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        val card1 = findViewById(R.id.imageView) as ImageView
        val card2 = findViewById(R.id.imageView2) as ImageView
        val card3 = findViewById(R.id.imageView3) as ImageView
        val card4 = findViewById(R.id.imageView4) as ImageView
        val card5 = findViewById(R.id.imageView5) as ImageView
        val card6 = findViewById(R.id.imageView6) as ImageView
        val card7 = findViewById(R.id.imageView7) as ImageView
        val card8 = findViewById(R.id.imageView8) as ImageView
        var dioclick=0
        var uno= arrayOf(R.drawable.uno_sena, R.drawable.uno_obj, R.drawable.uno_num)
        var dos= arrayOf(R.drawable.dos_sena, R.drawable.dos_obj, R.drawable.dos_num)
        var tres= arrayOf(R.drawable.tres_sena, R.drawable.tres_obj, R.drawable.tres_num)
        var cuatro= arrayOf(R.drawable.cuatro_sena, R.drawable.cuatro_obj, R.drawable.cuatro_num)
        var cinco= arrayOf(R.drawable.cinco_sena, R.drawable.cinco_obj, R.drawable.cinco_num)
        val numeroRandom = Random().nextInt(0.. 2)
        val numeroRando = Random().nextInt(0.. 2)
        var t1=0
        var t2=0
        var volteadas=0
        //val tarjetasRandom = List(2) { Random().nextInt(1.. 8) }

        Log.i("JuegoNum",""+numeroRandom)
        Log.i("JuegoNum",""+numeroRando)

        card1.setOnClickListener {
           // card1.animationXFlip(Flip.FLIP_IN_Y)
            card1.setImageResource(uno[numeroRandom])


            dioclick=dioclick+1

            if(dioclick==1){
                t1=1
            }
            else {
                if (dioclick == 2) {
                    t2=1
                    if (t1 == t2) {
                        //card1.isVisible = false
                        //card6.isVisible = false
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card1.visibility = View.GONE
                                card6.visibility = View.GONE
                            }
                        }, 3000)
                        //card1.visibility = View.GONE
                        //card6.visibility = View.GONE
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)

                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card2.setOnClickListener {
           // card2.animationXFlip(Flip.FLIP_IN_Y)
            card2.setImageResource(dos[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=2
            }
            else {
                if (dioclick == 2) {
                    t2=2
                    if (t1 == t2) {
                        //card2.isVisible = false
                        //card8.isVisible = false
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card2.visibility = View.GONE
                                card8.visibility = View.GONE
                            }
                        }, 3000)
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card3.setOnClickListener {
            //card3.animationXFlip(Flip.FLIP_IN_Y)
            card3.setImageResource(tres[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=3
            }
            else {
                if (dioclick == 2) {
                    t2=3
                    if (t1 == t2) {
                        //card3.isVisible = false
                        //card7.isVisible = false
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card3.visibility = View.GONE
                                card7.visibility = View.GONE
                            }
                        }, 3000)
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card4.setOnClickListener {
            //card4.animationXFlip(Flip.FLIP_IN_Y)
            card4.setImageResource(cuatro[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=4
            }
            else {
                if (dioclick == 2) {
                    t2=4
                    if (t1 == t2) {
                        //card4.isVisible = false
                        //card5.isVisible = false

                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card4.visibility = View.GONE
                                card5.visibility = View.GONE
                            }
                        }, 3000)
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card5.setOnClickListener {
            //card5.animationXFlip(Flip.FLIP_IN_Y)
            card5.setImageResource(cuatro[numeroRando])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=4
            }
            else {
                if (dioclick == 2) {
                    t2=4
                    if (t1 == t2) {
                        //card4.isVisible = false
                        //card5.isVisible = false

                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card4.visibility = View.GONE
                                card5.visibility = View.GONE
                            }
                        }, 3000)
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card6.setOnClickListener {
            //card6.animationXFlip(Flip.FLIP_IN_Y)
            card6.setImageResource(uno[numeroRando])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=1
            }
            else {
                if (dioclick == 2) {
                    t2=1
                    if (t1 == t2) {
                        //card1.isVisible = false
                        //card6.isVisible = false
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card1.visibility = View.GONE
                                card6.visibility = View.GONE
                            }
                        }, 3000)
                        //card1.visibility = View.GONE
                        //card6.visibility = View.GONE
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }
        }

        card7.setOnClickListener {
            //card7.animationXFlip(Flip.FLIP_IN_Y)
            card7.setImageResource(tres[numeroRando])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=3
            }
            else {
                if (dioclick == 2) {
                    t2=3
                    if (t1 == t2) {
                        //card3.isVisible = false
                        //card7.isVisible = false

                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card3.visibility = View.GONE
                                card7.visibility = View.GONE
                            }
                        }, 3000)
                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card8.setOnClickListener {
            //card8.animationXFlip(Flip.FLIP_IN_Y)
            card8.setImageResource(dos[numeroRando])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=2
            }
            else {
                if (dioclick == 2) {
                    t2=2
                    if (t1 == t2) {
                        //card2.isVisible = false
                        //card8.isVisible = false

                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                card2.visibility = View.GONE
                                card8.visibility = View.GONE
                            }
                        }, 3000)

                        dioclick = 0
                        t1=0
                        t2=0
                        volteadas += 1
                        if(volteadas==4)
                        {
                            val intent = Intent(this@JuegoNum, Felicidades::class.java)
                            intent.putExtra("leccion_valor", 2);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        }
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed(object: Runnable {
                            override fun run() {
                                Log.i("segunda_tarjeta", "Aquí debería salir un delay")
                                card1.setImageResource(R.drawable.icon_card)
                                card2.setImageResource(R.drawable.icon_card)
                                card3.setImageResource(R.drawable.icon_card)
                                card4.setImageResource(R.drawable.icon_card)
                                card5.setImageResource(R.drawable.icon_card)
                                card6.setImageResource(R.drawable.icon_card)
                                card7.setImageResource(R.drawable.icon_card)
                                card8.setImageResource(R.drawable.icon_card)
                                /*card1.animationXFlip(Flip.FLIP_IN_X)
                                card2.animationXFlip(Flip.FLIP_IN_X)
                                card3.animationXFlip(Flip.FLIP_IN_X)
                                card4.animationXFlip(Flip.FLIP_IN_X)
                                card5.animationXFlip(Flip.FLIP_IN_X)
                                card6.animationXFlip(Flip.FLIP_IN_X)
                                card7.animationXFlip(Flip.FLIP_IN_X)
                                card8.animationXFlip(Flip.FLIP_IN_X)*/
                                dioclick = 0
                                t1=0
                                t2=0
                            }
                        }, 3000)
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@JuegoNum,Leccion::class.java)
        intent.putExtra("leccion", 2)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}

private fun Random.nextInt(intRange: IntRange): Int{
    return  intRange.start + nextInt(intRange.last-intRange.start)
}