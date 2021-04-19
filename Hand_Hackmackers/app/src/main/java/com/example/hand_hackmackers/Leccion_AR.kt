package com.example.hand_hackmackers

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.net.ConnectivityManager
import android.opengl.GLSurfaceView
import android.os.*
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.*
import org.artoolkit.ar.base.camera.CameraPreferencesActivity
import org.artoolkit.ar.base.camera.CaptureCameraPreview
import kotlinx.coroutines.*
import kotlinx.coroutines.android.UI
import java.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class Leccion_AR : Activity() {

    private val TAG = "Leccion_AR"
    var dioClic = 0
    var juego = 0
    var leccion0 = 0
    var nftCargado = 0
    var numMarcadores : Int = 0
    var mostrandoAviso : Int = 0
    var sumaParaAviso : Int = 0
    var letraRanTV : TextView? = null
    var loadIView : ImageView? = null
    var palabraIView : ImageView? = null
    var leccionView : ImageView? = null
    var botonFelic : ImageView? = null
    var imagenPalabra : ImageView? = null
    var viendoMarcador : Int = 0
    var marcadorPrevio : Int = -1
    var tv1 : TextView? = null
    var time : TextView? = null
    var layoutMensaje : FrameLayout? = null
    var tvMensaje : TextView? = null
    var abecedario = arrayOf("A", "E", "I", "K", "LL", "O", "Q", "U", "Z")
    var numeros = arrayOf("1", "2", "3", "4", "5")
    var stringNombreMarcador = ""
    var marcadorDetectado = 0
    var job1 : Job? = null
    var job2 : Job? = null
    var job4 : Job? = null

    companion object {

        init {
            System.loadLibrary("c++_shared")
            System.loadLibrary("nftBookNative")
            Log.i("Leccion_AR", "librarys loaded")
        }

        @JvmStatic
        val REQUEST_CAMERA_PERMISSION_RESULT = 0

        // Lifecycle functions.
        @JvmStatic
        external fun nativeCreate(ctx: Context, objectDFN: String, markerDFN: String): Boolean

        @JvmStatic
        external fun nativeStart(): Boolean

        @JvmStatic
        external fun nativeStop(): Boolean

        @JvmStatic
        external fun nativeDestroy(): Boolean

        // Camera functions.
        @JvmStatic
        external fun nativeVideoInit(w: Int, h: Int, cameraIndex: Int, cameraIsFrontFacing: Boolean): Boolean

        @JvmStatic
        external fun nativeVideoFrame(image: ByteArray)

        // OpenGL functions.
        @JvmStatic
        external fun nativeSurfaceCreated()

        @JvmStatic
        external fun nativeSurfaceChanged(w: Int, h: Int)

        @JvmStatic
        external fun nativeDrawFrame()

        // Other functions.
        @JvmStatic
        external fun nativeDisplayParametersChanged(orientation: Int, w: Int, h: Int, dpi: Int)  // 0 = portrait, 1 = landscape (device rotated 90 degrees ccw), 2 = portrait upside down, 3 = landscape reverse (device rotated 90 degrees cw).

        @JvmStatic
        external fun nativeSetInternetState(state: Int)

        @JvmStatic
        external fun nativeGetDetectedPage() : Int

        @JvmStatic
        external fun nativeGetNFTLoaded() : Int
    }

    private var glView: GLSurfaceView? = null
    private var camSurface: CameraSurface? = null

    private var mainLayout: FrameLayout? = null

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nftCargado=0
        Thread.setDefaultUncaughtExceptionHandler { thread, e -> handleUncaughtException(thread, e) }

        var needActionBar = false

        if (needActionBar) {
            requestWindowFeature(Window.FEATURE_ACTION_BAR)
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE // Force landscape-only.

        val bundle = intent.extras
        leccion0 = bundle!!.get("leccion_valor") as Int
        juego = bundle.get("juego") as Int


        setContentView(R.layout.activity_ar)

        if(leccion0 == 1 && juego == 0) {
            nativeCreate(this, "Data/objectsL1.dat", "Data/markersL1.dat")
            numMarcadores = 9
        } else if (leccion0 == 1 && juego == 1) {
            nativeCreate(this, "Data/objectsJuego.dat", "Data/markersL1.dat")
            numMarcadores = 9
        } else if (leccion0 == 2 && juego == 0) {
            nativeCreate(this, "Data/objectsL2.dat", "Data/markersL2.dat")
            numMarcadores = 5
        }

    }

    public override fun onStart() {
        super.onStart()

        var topMainLayout = this.findViewById(R.id.ARtopLayout) as LinearLayout
        topMainLayout.setBackgroundColor(Color.parseColor("#000000"))

        mainLayout = this.findViewById(R.id.ARLayout) as FrameLayout
        mainLayout!!.setBackgroundColor(Color.parseColor("#000000"))

        nativeStart()
    }

    public override fun onResume() {
        super.onResume()
        dioClic = 0
        nftCargado = 0

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        nativeSetInternetState(if (isConnected) 1 else 0)

        camSurface = CameraSurface(this)

        if (camSurface!!.gettingCameraAccessPermissionsFromUser())
            return

        glView = GLSurfaceView(this)
        glView!!.setRenderer(Renderer())
        glView!!.setZOrderMediaOverlay(true)

        letraRanTV = TextView(this)
        time = TextView(this)
        loadIView = ImageView(this)
        palabraIView = ImageView(this)
        leccionView = ImageView(this)
        botonFelic = ImageView(this)
        imagenPalabra = ImageView(this)
        var paramsSeg = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER or Gravity.BOTTOM)
        var paramsTimeTV = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.RIGHT)
        paramsSeg.leftMargin = pxFromDp(this, 5f).toInt()
        paramsSeg.rightMargin = pxFromDp(this, 5f).toInt()
        paramsSeg.bottomMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.leftMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.rightMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.bottomMargin = pxFromDp(this, 5f).toInt()
        var paramsLeccionView = FrameLayout.LayoutParams(200, 200, Gravity.TOP or Gravity.LEFT)
        paramsLeccionView.topMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.leftMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.rightMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.bottomMargin = pxFromDp(this, 5f).toInt()
        var paramsBotFelic = FrameLayout.LayoutParams(200, 200, Gravity.BOTTOM or Gravity.RIGHT)
        letraRanTV!!.gravity = Gravity.TOP or Gravity.CENTER
        time!!.gravity = Gravity.BOTTOM or Gravity.LEFT
        time!!.textSize = 18F

        if (juego == 0)
            letraRanTV!!.textSize = 20F
        else
            letraRanTV!!.textSize = 26F

        letraRanTV!!.text = ""
        letraRanTV!!.setTextColor(Color.parseColor("#FFFFFF"))
        letraRanTV!!.maxLines=2
        letraRanTV!!.background = resources.getDrawable(R.drawable.corner_time)
        letraRanTV!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())

        time!!.text = ""
        time!!.setTextColor(Color.parseColor("#FFFFFF"))
        time!!.maxLines=2
        time!!.background = resources.getDrawable(R.drawable.corner_time)
        time!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())

        tv1 = TextView(this)
        tv1!!.textSize = 22F
        tv1!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tv1!!.visibility = View.GONE
        tv1!!.setTextColor(Color.parseColor("#FFFFFF"))
        tv1!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())
        tv1!!.text = "\u2611"

        var fBParams : FrameLayout.LayoutParams

        if(juego == 0)
            fBParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.RIGHT)
        else
            fBParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM or Gravity.RIGHT)
        fBParams.bottomMargin = pxFromDp(this, 5f).toInt()
        fBParams.leftMargin = pxFromDp(this, 5f).toInt()
        fBParams.rightMargin = pxFromDp(this, 5f).toInt()
        fBParams.topMargin = pxFromDp(this, 5f).toInt()


        val mensajeParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)

        tvMensaje = TextView(this)
        tvMensaje!!.gravity = Gravity.CENTER
        tvMensaje!!.textSize = 20F
        tvMensaje!!.text = "Coloca un marcador frente a la cámara.\nRecuerda que el marcador debe pertenecer\na la lección actual."
        tvMensaje!!.setTextColor(Color.parseColor("#FFFFFF"))
        tvMensaje!!.background = resources.getDrawable(R.drawable.corner_msj)
        tvMensaje!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())

        layoutMensaje = FrameLayout(this)
        layoutMensaje!!.setBackgroundColor(Color.parseColor("#00000000"))




        mainLayout!!.addView(camSurface, ViewGroup.LayoutParams(128, 128))
        mainLayout!!.addView(glView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        mainLayout!!.addView(leccionView, paramsLeccionView)
        mainLayout!!.addView(letraRanTV, paramsSeg)


        //Inicia Job
        job1 = GlobalScope.launch {

            while(nftCargado == 0){
                nftCargado = nativeGetNFTLoaded()

            }

            Handler(Looper.getMainLooper()).post(object : Runnable {
                override fun run(){
                    loadIView!!.visibility = View.INVISIBLE
                }
            })


            while(true){
                delay(100L)
                if(juego == 1)
                    break
                marcadorDetectado = nativeGetDetectedPage()
                if(marcadorDetectado < 0) {
                    if(leccion0 == 1)
                        stringNombreMarcador = "Lección 'Abecedario'\nNo se encuentra el marcador"
                    else if(leccion0 == 2)
                        stringNombreMarcador = "Lección 'Números'\nNo se encuentra el marcador"
                } else if(leccion0 == 1)
                    stringNombreMarcador = "Lección 'Abecedario'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nLetra "+ abecedario[marcadorDetectado])
                else if(leccion0 == 2)
                    stringNombreMarcador = "Lección 'Números'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nNúmero "+ numeros[marcadorDetectado])
                if(marcadorDetectado >= 0){
                    viendoMarcador = 1
                }
                if (marcadorPrevio != marcadorDetectado || marcadorDetectado < 0) {
                    viendoMarcador = 0
                }
                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run(){
                        letraRanTV!!.text = stringNombreMarcador
                        Log.i(TAG+"_Job1", stringNombreMarcador)
                    }
                })

                marcadorPrevio = marcadorDetectado
            }
        }
        //Finaliza Job


        //IF para el juego o no juego

        if(juego == 0){

            //crearTabla()
            mainLayout!!.addView(layoutMensaje)
            layoutMensaje!!.addView(tvMensaje, mensajeParams)
            var ancho = 0f

            layoutMensaje!!.visibility = View.GONE
            if(leccion0 < 3)
                ancho = 320f
            else if (leccion0 < 5)
                ancho = 640f
            else
                ancho = 660f


            job2 = GlobalScope.launch {
                while (nftCargado == 0)
                    continue

                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run(){
                        botonFelic!!.visibility = View.VISIBLE
                    }
                })
                while (true) {
                    delay(100L)
                    if(viendoMarcador == 1) {
                        //Log.e("Corrutina Job2: ", "Ocultando mensaje")
                        if (mostrandoAviso == 1) {
                            Handler(Looper.getMainLooper()).post(object : Runnable {
                                override fun run(){
                                    layoutMensaje!!.visibility = View.GONE
                                }
                            })
                            sumaParaAviso = 0
                            mostrandoAviso = 0

                        } else if(sumaParaAviso > 0) {
                            sumaParaAviso = 0
                        }
                    } else if(sumaParaAviso < 100) {
                        sumaParaAviso++
                    } else if(sumaParaAviso == 100 && viendoMarcador == 0 && mostrandoAviso == 0){
                        Handler(Looper.getMainLooper()).post(object : Runnable {
                            override fun run(){
                                layoutMensaje!!.visibility = View.VISIBLE
                            }
                        })
                        mostrandoAviso = 1
                    }

                }
            }


        } else if(leccion0 == 1 && juego == 1){

            var random = 0
            var segs = 0L

            mainLayout!!.addView(time, paramsTimeTV)

            Log.i("Hand_Juego","Juego cargado asdf.")
            job2 = GlobalScope.launch {
                while(nftCargado == 0) {
                    //Log.i("Hand_Juego","cargando corrutina.")
                    continue
                }

                segs=10000

                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run() {
                        //time!!.text = segs.toString()
                        time!!.text = String.format("Tiempo: %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(segs), TimeUnit.MILLISECONDS.toSeconds(segs) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(segs)))
                    }
                })

                Log.i("Hand_Juego","Juego cargado asdf.")
                while(true){
                    segs = 10000
                    random = Random().nextInt(0..8)
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run() {
                            letraRanTV!!.text = "Colocar el marcador de:\n"+abecedario[random]
                        }
                    })
                    while(segs>0){

                        if (nativeGetDetectedPage()==random){

                            break;
                        }
                    }
                }
            }
            job4 = GlobalScope.launch {
                while(nftCargado == 0)
                    continue

                while (true){
                    while(segs>0){
                        delay(1000L)
                        segs-=1000
                        Handler(Looper.getMainLooper()).post(object : Runnable {
                            override fun run() {
                                //time!!.text = segs.toString()
                                time!!.text = String.format("Tiempo: %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(segs), TimeUnit.MILLISECONDS.toSeconds(segs) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(segs)))
                            }
                        })
                    }
                }
            }
        }
        // Finalizan ifs

        if (glView != null) glView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        nftCargado = 0
        if (glView != null) glView!!.onPause()


        if(job1 != null) {
            GlobalScope.launch(UI) {
                job1!!.cancelAndJoin()
            }
        }



        if(juego == 0){
            if(job2 != null) {
                GlobalScope.launch(UI) {
                    job2!!.cancelAndJoin()

                }
            }
            if(layoutMensaje != null && tvMensaje != null)
                layoutMensaje!!.removeView(tvMensaje)
            if(layoutMensaje != null)
                mainLayout!!.removeView(layoutMensaje)
        }
        else if(juego == 1){
            if(job2 != null) {
                GlobalScope.launch(UI) {
                    job2!!.cancelAndJoin()
                }
            }
            if(job4 != null) {
                GlobalScope.launch(UI) {
                    job4!!.cancelAndJoin()
                }
            }
        }


        if(letraRanTV != null)
            mainLayout!!.removeView(letraRanTV)


        mainLayout!!.removeView(glView)
        mainLayout!!.removeView(camSurface)
    }

    public override fun onStop() {
        super.onStop()

        nftCargado = 0


        GlobalScope.launch(UI){
            job1!!.cancelAndJoin()
        }


        GlobalScope.launch(UI){
            job2!!.cancelAndJoin()
            //job3!!.cancelAndJoin()
        }

        if(juego==1){
            GlobalScope.launch(UI){
                job4!!.cancelAndJoin()
                //job3!!.cancelAndJoin()
            }
        }

        nativeStop()
    }

    public override fun onDestroy() {
        super.onDestroy()

        nftCargado = 0


        GlobalScope.launch(UI){
            job1!!.cancelAndJoin()
        }

        GlobalScope.launch(UI){
            job2!!.cancelAndJoin()
            //job3!!.cancelAndJoin()
        }

        if(juego==1){
            GlobalScope.launch(UI){
                job4!!.cancelAndJoin()
                //job3!!.cancelAndJoin()
            }
        }

        nativeDestroy()
    }

    private fun updateNativeDisplayParameters() {
        val d = windowManager.defaultDisplay
        val orientation = d.rotation
        val dm = DisplayMetrics()
        d.getMetrics(dm)
        val w = dm.widthPixels
        val h = dm.heightPixels
        val dpi = dm.densityDpi
        nativeDisplayParametersChanged(orientation, w, h, dpi)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        updateNativeDisplayParameters()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            startActivity(Intent(this, CameraPreferencesActivity::class.java))
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        Log.i(TAG, "onRequestPermissionsResult(): called")
        if (requestCode == CaptureCameraPreview.REQUEST_CAMERA_PERMISSION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish()

            } else if (1 <= permissions.size) {

            }
            Log.i(TAG, "onRequestPermissionsResult(): reset ask for cam access perm")
            camSurface!!.resetGettingCameraAccessPermissionsFromUserState()
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun handleUncaughtException(thread: Thread, e: Throwable) {
        Log.e(TAG, "handleUncaughtException(): exception type, " + e.toString())
        Log.e(TAG, "handleUncaughtException(): thread, \"" + thread.name + "\" exception, \"" + e.message + "\"")
        e.printStackTrace()
        return
    }

    fun pxFromDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun spToPx(sp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics).toInt()
    }


}

private fun Random.nextInt(intRange: IntRange): Int {
    return intRange.start + nextInt(intRange.last - intRange.start)
}