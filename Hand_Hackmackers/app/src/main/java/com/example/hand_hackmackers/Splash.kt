package com.example.hand_hackmackers

import android.content.Intent
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat.startActivity
import com.daimajia.androidanimations.library.Techniques
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash


class Splash:AwesomeSplash() {
    override fun initSplash(configSplash: ConfigSplash?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Customize Circular Reveal
        configSplash!!.setBackgroundColor(R.color.white)
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        configSplash.setLogoSplash(R.mipmap.icon_hand); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.ZoomIn);
    }

    override fun animationsFinished() {
        val intent = Intent(this@Splash, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }
}