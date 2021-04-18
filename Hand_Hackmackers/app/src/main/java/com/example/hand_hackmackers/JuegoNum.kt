package com.example.hand_hackmackers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.rommansabbir.animationx.Flip
import com.rommansabbir.animationx.animationXFlip
import java.util.*

class JuegoNum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_num)

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
        var uno= arrayOf(R.drawable.icon_game_abc, R.drawable.icon_ra_abc, R.drawable.icon_game_abc)
        var dos= arrayOf(R.drawable.icon_game_abc, R.drawable.icon_ra_abc, R.drawable.icon_game_abc)
        var tres= arrayOf(R.drawable.icon_game_abc, R.drawable.icon_ra_abc, R.drawable.icon_game_abc)
        var cuatro= arrayOf(R.drawable.icon_game_abc, R.drawable.icon_ra_abc, R.drawable.icon_game_abc)
        var cinco= arrayOf(R.drawable.icon_game_abc, R.drawable.icon_ra_abc, R.drawable.icon_game_abc)
        val numeroRandom = Random().nextInt(0.. 2)
        var t1=0
        var t2=0
        var volteadas=0
        //val tarjetasRandom = List(2) { Random().nextInt(1.. 8) }

        Log.i("JuegoNum",""+numeroRandom)


        card1.setOnClickListener {
            card1.animationXFlip(Flip.FLIP_IN_Y)
            card1.setImageResource(uno[numeroRandom])

            dioclick=dioclick+1

            if(dioclick==1){
                t1=1
            }
            else {
                if (dioclick == 2) {
                    t2=1
                    if (t1 == t2) {
                        card1.isVisible = false
                        card6.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0
                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card2.setOnClickListener {
            card2.animationXFlip(Flip.FLIP_IN_Y)
            card2.setImageResource(dos[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=2
            }
            else {
                if (dioclick == 2) {
                    t2=2
                    if (t1 == t2) {
                        card2.isVisible = false
                        card8.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card3.setOnClickListener {
            card3.animationXFlip(Flip.FLIP_IN_Y)
            card3.setImageResource(tres[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=3
            }
            else {
                if (dioclick == 2) {
                    t2=3
                    if (t1 == t2) {
                        card3.isVisible = false
                        card7.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card4.setOnClickListener {
            card4.animationXFlip(Flip.FLIP_IN_Y)
            card4.setImageResource(cuatro[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=4
            }
            else {
                if (dioclick == 2) {
                    t2=4
                    if (t1 == t2) {
                        card4.isVisible = false
                        card5.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card5.setOnClickListener {
            card5.animationXFlip(Flip.FLIP_IN_Y)
            card5.setImageResource(cuatro[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=4
            }
            else {
                if (dioclick == 2) {
                    t2=4
                    if (t1 == t2) {
                        card4.isVisible = false
                        card5.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card6.setOnClickListener {
            card6.animationXFlip(Flip.FLIP_IN_Y)
            card6.setImageResource(uno[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=1
            }
            else {
                if (dioclick == 2) {
                    t2=1
                    if (t1 == t2) {
                        card1.isVisible = false
                        card6.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }
        }

        card7.setOnClickListener {
            card7.animationXFlip(Flip.FLIP_IN_Y)
            card7.setImageResource(tres[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=3
            }
            else {
                if (dioclick == 2) {
                    t2=3
                    if (t1 == t2) {
                        card3.isVisible = false
                        card7.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }

        card8.setOnClickListener {
            card8.animationXFlip(Flip.FLIP_IN_Y)
            card8.setImageResource(dos[numeroRandom])

            dioclick=dioclick+1
            if(dioclick==1){
                t1=2
            }
            else {
                if (dioclick == 2) {
                    t2=2
                    if (t1 == t2) {
                        card2.isVisible = false
                        card8.isVisible = false
                        dioclick = 0
                        t1=0
                        t2=0

                    } else {
                        card1.setImageResource(R.drawable.icon_card)
                        card2.setImageResource(R.drawable.icon_card)
                        card3.setImageResource(R.drawable.icon_card)
                        card4.setImageResource(R.drawable.icon_card)
                        card5.setImageResource(R.drawable.icon_card)
                        card6.setImageResource(R.drawable.icon_card)
                        card7.setImageResource(R.drawable.icon_card)
                        card8.setImageResource(R.drawable.icon_card)
                        card1.animationXFlip(Flip.FLIP_IN_X)
                        card2.animationXFlip(Flip.FLIP_IN_X)
                        card3.animationXFlip(Flip.FLIP_IN_X)
                        card4.animationXFlip(Flip.FLIP_IN_X)
                        card5.animationXFlip(Flip.FLIP_IN_X)
                        card6.animationXFlip(Flip.FLIP_IN_X)
                        card7.animationXFlip(Flip.FLIP_IN_X)
                        card8.animationXFlip(Flip.FLIP_IN_X)
                        dioclick = 0
                        t1=0
                        t2=0
                    }

                }
            }

            //Toast.makeText(this@MainActivity, "You clicked 1", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun Random.nextInt(intRange: IntRange): Int{
    return  intRange.start + nextInt(intRange.last-intRange.start)
}