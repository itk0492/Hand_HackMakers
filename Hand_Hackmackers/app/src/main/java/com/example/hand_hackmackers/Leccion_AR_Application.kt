package com.example.hand_hackmackers

import android.app.Application
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import android.support.multidex.MultiDex
import org.artoolkit.ar.base.assets.AssetHelper

@Suppress("DEPRECATION")
class Leccion_AR_Application: Application() {
    private var sInstance: Application? = null

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            MultiDex.install(this@Leccion_AR_Application)
        }
    }

    fun getInstance(): Application? {
        return sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        (sInstance as Leccion_AR_Application).initializeInstance()
    }

    protected fun initializeInstance() {
        PreferenceManager.setDefaultValues(this,org.artoolkit.ar.base.R.xml.preferences, false)

        // Unpack assets to cache directory so native library can read them.
        // N.B.: If contents of assets folder changes, be sure to increment the
        // versionCode integer in the modules build.gradle file.
        val assetHelper = AssetHelper(assets)
        assetHelper.cacheAssetFolder(getInstance(), "Data")
        assetHelper.cacheAssetFolder(getInstance(), "DataNFT")
        assetHelper.cacheAssetFolder(getInstance(), "OSG")

    }
}