package com.example.hand_hackmackers

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.opengl.GLSurfaceView
import android.opengl.Visibility
import android.os.*
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.*
//import com.bumptech.glide.Glide
import org.artoolkit.ar.base.camera.CameraPreferencesActivity
import org.artoolkit.ar.base.camera.CaptureCameraPreview
//import kotlinx.coroutines.experimental.*
//import kotlinx.coroutines.experimental.android.UI
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.thread
//import kotlin.coroutines.experimental.coroutineContext
//import com.phelat.`fun`.Control.FunControl
//import com.phelat.`fun`.Layouts.Funny
//import com.phelat.`fun`.Widget.FunnyButton
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.coroutines.experimental.GlobalScope
//import kotlinx.coroutines.experimental.android.UI
//import kotlinx.coroutines.experimental.cancelAndJoin
//import kotlinx.coroutines.experimental.launch
//import kotlinx.coroutines.experimental.GlobalScope
//import kotlinx.coroutines.experimental.delay
//import kotlinx.coroutines.experimental.launch
//import kotlinx.coroutines.experimental.android.Main
//import me.jessyan.autosize.internal.CancelAdapt
//import org.aviran.cookiebar2.CookieBar
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.math.ceil

class Leccion_AR : Activity() {

    private val TAG = "Leccion_AR"
    var dioClic = 0
    var juego = 0
    var leccion0 = 0
    var nftCargado = 0
    lateinit var usuario : String
    lateinit var vistos : IntArray
    var numMarcadores : Int = 0
    var numVistos : Int = 0
    var mostrandoAviso : Int = 0
    var sumaParaAviso : Int = 0
    //var nombreMarcador : TextView? = null
    //var segundos : TextView? = null
    var letraRanTV : TextView? = null
    var loadIView : ImageView? = null
    var palabraIView : ImageView? = null
    var leccionView : ImageView? = null
    var botonFelic : ImageView? = null
    var imagenPalabra : ImageView? = null
    var palabrasTotales : Int = 0
    var oracionesTotales : Int = 0
    var pronombrePuesto : Int = -1
    var verboPuesto : Int = -1
    var oracionActual : Int = 0
    var palabraCompleta : Int = 0
    var contarLetras : Int = 0
    var iguales : Int = 0
    var palabraActual : Int = 0
    var viendoMarcador : Int = 0
    var marcadorPrevio : Int = -1
    lateinit var palabraTV : SpannableString
    var colorSpan : ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FCAB0A"))
    var colorSpanV : ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#2CBC00"))
    //var guardarPalabra : IntArray = IntArray(10)
    var guardarPalabra : IntArray = IntArray(40) {i -> 0}
    var palabras = arrayOf("BICICLETA", "AGUA", "PAN", "COCHE", "BURBUJA", "JUGAR", "DORMIR",
        "ESCUELA", "DIEZ", "MUÑECA", "KIWI", "TAXI", "YEMA", "OSO", "FUEGO", "KAYAK", "ÑU",
        "QUETZAL", "CUADERNO", "CEREZA", "FELIZ", "LLUVIA", "PEZ", "PELOTA", "ABEJA", "ROSA",
        "SILLA", "LIBRO", "ROPA", "BARCO", "DULCE", "PIÑA", "RELOJ", "SOL", "TAZA", "ERIZO",
        "MANZANA", "LUNA", "GUSANO", "MONO")
    var palabrasSeparadas = arrayOf(arrayOf(1,8,2,8,2,11,4,21,0), arrayOf(0,6,22,0), arrayOf(17,0,14),
        arrayOf(2,16,2,7,4), arrayOf(1,22,19,1,22,9,0), arrayOf(9,22,6,0,19), arrayOf(3,16,19,13,8,19),
        arrayOf(4,20,2,22,4,11,0), arrayOf(3,8,4,27), arrayOf(13,22,15,4,2,0), arrayOf(10,8,24,8),
        arrayOf(21,0,25,8), arrayOf(26,4,13,0), arrayOf(16,20,16), arrayOf(5,22,4,6,16), arrayOf(10,0,26,0,10),
        arrayOf(15,22), arrayOf(18,22,4,21,27,0,11), arrayOf(2,22,0,3,4,19,14,16), arrayOf(2,4,19,4,27,0),
        arrayOf(5,4,11,8,27), arrayOf(12,22,23,8,0), arrayOf(17,4,27), arrayOf(17,4,11,16,21,0),
        arrayOf(0,1,4,9,0), arrayOf(19,16,20,0), arrayOf(20,8,12,0), arrayOf(11,8,1,19,16), arrayOf(19,16,17,0),
        arrayOf(1,0,19,2,16), arrayOf(3,22,11,2,4), arrayOf(17,8,15,0), arrayOf(19,4,11,16,9), arrayOf(20,16,11),
        arrayOf(21,0,27,0), arrayOf(4,19,8,27,16), arrayOf(13,0,14,27,0,14,0), arrayOf(11,22,14,0),
        arrayOf(6,22,20,0,14,16), arrayOf(13,16,14,16))
    var oracionesSeparadas = arrayOf(arrayOf(0,5), arrayOf(0,6), arrayOf(0,7), arrayOf(0,8), arrayOf(0,9),
        arrayOf(1,5), arrayOf(1,6), arrayOf(1,7), arrayOf(1,8), arrayOf(1,9), arrayOf(2,5), arrayOf(2,6),
        arrayOf(2,7), arrayOf(2,8), arrayOf(2,9), arrayOf(3,5), arrayOf(3,6), arrayOf(3,7), arrayOf(3,8),
        arrayOf(3,9), arrayOf(4,5), arrayOf(4,6), arrayOf(4,7), arrayOf(4,8), arrayOf(4,9))
    /*
    var palabrasImagenes = arrayOf(R.drawable.bicicleta, R.drawable.agua, R.drawable.pan, R.drawable.coche,
        R.drawable.burbuja, R.drawable.jugar, R.drawable.dormir, R.drawable.escuela, R.drawable.diez_eval,
        R.drawable.muneca_toast, R.drawable.kiwi, R.drawable.taxi, R.drawable.yema, R.drawable.oso,
        R.drawable.fuego, R.drawable.kayak, R.drawable.niu, R.drawable.quetzal, R.drawable.cuaderno,
        R.drawable.cereza, R.drawable.feliz, R.drawable.lluvia, R.drawable.pez, R.drawable.pelota,
        R.drawable.abeja, R.drawable.rosa, R.drawable.silla, R.drawable.libro, R.drawable.ropa,
        R.drawable.barco, R.drawable.dulce, R.drawable.pinia, R.drawable.reloj, R.drawable.sol,
        R.drawable.taza, R.drawable.erizo, R.drawable.manzana, R.drawable.luna, R.drawable.gusano,
        R.drawable.mono)
    var letraGif = arrayOf(R.drawable.juan_mano_a, R.drawable.juan_mano_b, R.drawable.juan_mano_c, R.drawable.juan_mano_d,
        R.drawable.juan_mano_e, R.drawable.juan_mano_f, R.drawable.juan_mano_g, R.drawable.juan_mano_h, R.drawable.juan_mano_i,
        R.drawable.juan_mano_j, R.drawable.juan_mano_k, R.drawable.juan_mano_l, R.drawable.juan_mano_ll, R.drawable.juan_mano_m,
        R.drawable.juan_mano_n, R.drawable.juan_mano_ne, R.drawable.juan_mano_o, R.drawable.juan_mano_p, R.drawable.juan_mano_q,
        R.drawable.juan_mano_r, R.drawable.juan_mano_s, R.drawable.juan_mano_t, R.drawable.juan_mano_u, R.drawable.juan_mano_v,
        R.drawable.juan_mano_w, R.drawable.juan_mano_x, R.drawable.juan_mano_y, R.drawable.juan_mano_z)
    var pronGif = arrayOf(R.drawable.juan_yo, R.drawable.juan_tu, R.drawable.juan_el, R.drawable.juan_ustedes, R.drawable.juan_nosotros)
    var verbGif = arrayOf(R.drawable.juan_aprender, R.drawable.juan_enseniar, R.drawable.juan_entender, R.drawable.juan_gustar, R.drawable.juan_jugar)
    var oracionImagenes = arrayOf(R.drawable.yo_aprendo, R.drawable.yo_ensenio, R.drawable.yo_entiendo,R.drawable.yo_gusto,R.drawable.yo_juego,
        R.drawable.tu_aprendes, R.drawable.tu_ensenias, R.drawable.tu_entiendes, R.drawable.tu_gustas, R.drawable.tu_juegas,
        R.drawable.el_aprende, R.drawable.el_ensenia, R.drawable.el_entiende, R.drawable.el_gusta, R.drawable.el_juega,
        R.drawable.ellos_aprenden, R.drawable.ellos_ensenian, R.drawable.ellos_entienden, R.drawable.ellos_gustan, R.drawable.ellos_juegan,
        R.drawable.nosotros_aprendemos, R.drawable.nosotros_enseniamos, R.drawable.nosotros_entendemos, R.drawable.nosotros_gustamos, R.drawable.nosotros_jugamos)
    //var stringNombreMarcador = ""

     */
    //var funny : Funny? = null
    //var funnyButton : FunnyButton? = null
    //var funnyContainer : LinearLayout? = null
    //var funControl : FunControl? = null
    var tv1 : TextView? = null
    var time : TextView? = null
    lateinit var tbl1 : TableLayout
    var layoutMensaje : FrameLayout? = null
    var tvMensaje : TextView? = null
    var abecedario = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "LL", "M", "N",
        "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    var numeros = arrayOf("1", "2", "3", "4", "5")
    var pronombres = arrayOf("Yo", "Tú", "Él", "Ustedes", "Nosotros")
    var verbos = arrayOf("Aprender", "Enseñar", "Entender", "Gustar", "Jugar")
    var oracion = arrayOf("Yo aprender", "Yo enseñar", "Yo entender", "Yo gustar", "Yo jugar", "Tú aprender",
        "Tú enseñar", "Tú entender", "Tú gustar", "Tú jugar", "Él aprender", "Él enseñar", "Él entender",
        "Él gustar", "Él jugar", "Ustedes aprender", "Ustedes enseñar", "Ustedes entender", "Ustedes gustar",
        "Ustedes jugar", "Nosotros aprender", "Nosotros enseñar", "Nosotros entender", "Nosotros gustar",
        "Nosotros jugar")
    var oracionConjugado = arrayOf("Yo aprendo", "Yo enseño", "Yo entiendo", "Yo gusto", "Yo juego",
        "Tú aprendes", "Tú enseñas", "Tú entiendes", "Tú gustas", "Tú juegas",
        "Él aprende", "Él enseña", "Él entiende", "Él gusta", "Él juega",
        "Ustedes aprenden", "Ustedes enseñan", "Ustedes entienden", "Ustedes gustan", "Ustedes juegan",
        "Nosotros aprendemos","Nosotros enseñamos", "Nosotros entendemos", "Nosotros gustamos", "Nosotros jugamos")
    var stringNombreMarcador = ""
    var marcadorDetectado = 0
    //var job1 : Job? = null
    //var job2 : Job? = null
    //var job3 : Job? = null
    //var job4 : Job? = null
    var millisPasados = 0L

    // Load the native libraries.
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
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                if (!ViewConfiguration.get(this).hasPermanentMenuKey()) needActionBar = true
            } else {
                needActionBar = true
            }
        }
        */
        if (needActionBar) {
            requestWindowFeature(Window.FEATURE_ACTION_BAR)
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE // Force landscape-only.
        //updateNativeDisplayParameters()

        /*
        val bundle = intent.extras
        usuario = bundle.get("usuario") as String
        leccion0 = bundle.get("leccion") as Int
        juego = bundle.get("juego") as Int

        if(juego == 0) {
            var db = ManejadorDB(this)

            numMarcadores = db.checarMarcadores(leccion0)

            val file = File(this@Leccion_AR.filesDir, usuario.replace("@", "_").replace(".", "_") + "_" + leccion0 + ".dat")
            val contents = file.readText()
            vistos = IntArray(numMarcadores)
            for (i in 0..(numMarcadores - 1))
                vistos[i] = contents.split("\n")[i].toInt()

            numVistos = vistos.sum()
        }

         */

        leccion0 = 1
        juego = 0


        setContentView(R.layout.activity_ar)

        if(leccion0 == 1 && juego == 0)
            nativeCreate(this, "Data/objectsL1.dat", "Data/markersL1.dat")
        else if (leccion0 == 1 && juego == 1)
            nativeCreate(this, "Data/objectsJuego.dat", "Data/markersL1.dat")
        else if (leccion0 == 2 && juego == 0)
            nativeCreate(this, "Data/objectsL2.dat", "Data/markersL2.dat")
        else if (leccion0 == 3 && juego == 0)
            nativeCreate(this, "Data/objectsL3.dat", "Data/markersL3.dat")
        else if (leccion0 == 4 && juego == 0)
            nativeCreate(this, "Data/objectsL4.dat", "Data/markersL4.dat")
        else if (leccion0 == 5 && juego == 0)
            nativeCreate(this, "Data/objectsL5.dat", "Data/markersL5.dat")
        else if (leccion0 == 5 && juego == 1)
            nativeCreate(this, "Data/objectsJuego2.dat", "Data/markersJuego2.dat")

    }

    public override fun onStart() {
        super.onStart()

        var topMainLayout = this.findViewById(R.id.ARtopLayout) as LinearLayout
        topMainLayout.setBackgroundColor(Color.parseColor("#000000"))

        mainLayout = this.findViewById(R.id.ARLayout) as FrameLayout
        mainLayout!!.setBackgroundColor(Color.parseColor("#000000"))

        nativeStart()
    }

    public override// FILL_PARENT still required for API level 7 (Android 2.1)
    fun onResume() {
        super.onResume()
        dioClic = 0
        nftCargado = 0

        // Update info on whether we have an Internet connection.
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        nativeSetInternetState(if (isConnected) 1 else 0)

        // In order to ensure that the GL surface covers the camera preview each time onStart
        // is called, remove and add both back into the FrameLayout.
        // Removing GLSurfaceView also appears to cause the GL surface to be disposed of.
        // To work around this, we also recreate GLSurfaceView. This is not a lot of extra
        // work, since Android has already destroyed the OpenGL context too, requiring us to
        // recreate that and reload textures etc.

        // Create the camera view.
        camSurface = CameraSurface(this)

        if (camSurface!!.gettingCameraAccessPermissionsFromUser())
        //No need to go further, must ask user to allow access to the camera first.
            return

        // Create/recreate the GL view.
        glView = GLSurfaceView(this)
        //glView.setEGLConfigChooser(8, 8, 8, 8, 16, 0); // Do we actually need a transparent surface? I think not, (default is RGB888 with depth=16) and anyway, Android 2.2 barfs on this.
        glView!!.setRenderer(Renderer())
        glView!!.setZOrderMediaOverlay(true) // Request that GL view's SurfaceView be on top of other SurfaceViews (including CameraPreview's SurfaceView).
        //segundos = TextView(this)
        /*
        letraRanTV = TextView(this)
        time = TextView(this)
        //nombreMarcador = TextView(this)
        loadIView = ImageView(this)
        palabraIView = ImageView(this)
        leccionView = ImageView(this)
        botonFelic = ImageView(this)
        imagenPalabra = ImageView(this)
        //nombreMarcador!!.textSize = 24F
        //nombreMarcador!!.setTextColor(Color.parseColor("#FFFFFF"))
        //var params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.START)
        var paramsSeg = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER or Gravity.TOP)
        var paramsTimeTV = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.RIGHT)
        //paramsSeg.topMargin = pxFromDp(this, 5f).toInt()
        paramsSeg.leftMargin = pxFromDp(this, 5f).toInt()
        paramsSeg.rightMargin = pxFromDp(this, 5f).toInt()
        paramsSeg.bottomMargin = pxFromDp(this, 5f).toInt()
        //paramsTimeTV.topMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.leftMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.rightMargin = pxFromDp(this, 5f).toInt()
        paramsTimeTV.bottomMargin = pxFromDp(this, 5f).toInt()
        var paramsLIView = FrameLayout.LayoutParams(450, 450, Gravity.CENTER)
        var paramsPIView = FrameLayout.LayoutParams(375, 675, Gravity.BOTTOM or Gravity.LEFT)
        var paramsImPalabra = FrameLayout.LayoutParams(650, 650, Gravity.CENTER)
        var paramsLeccionView = FrameLayout.LayoutParams(200, 200, Gravity.TOP or Gravity.LEFT)
        paramsLeccionView.topMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.leftMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.rightMargin = pxFromDp(this, 5f).toInt()
        paramsLeccionView.bottomMargin = pxFromDp(this, 5f).toInt()
        var paramsBotFelic = FrameLayout.LayoutParams(200, 200, Gravity.BOTTOM or Gravity.RIGHT)
        letraRanTV!!.gravity = Gravity.TOP or Gravity.CENTER
        time!!.gravity = Gravity.BOTTOM or Gravity.LEFT
        time!!.textSize = 18F
        //segundos!!.gravity = Gravity.TOP or Gravity.RIGHT

        //funButton!!.foregroundGravity = Gravity.TOP or Gravity.RIGHT
        //loadIView!!.layoutParams = paramsLIView
        if (juego == 0)
            letraRanTV!!.textSize = 20F
        else
            letraRanTV!!.textSize = 26F
        //segundos!!.textSize = 24F
        //segundos!!.text = ""
        letraRanTV!!.text = ""
        letraRanTV!!.setTextColor(Color.parseColor("#FFFFFF"))
        letraRanTV!!.maxLines=2
        //letraRanTV!!.setBackgroundColor(Color.parseColor("#88000000"))
        letraRanTV!!.background = resources.getDrawable(R.drawable.corner_time)
        letraRanTV!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())
        //segundos!!.setTextColor(Color.parseColor("#FFFFFF"))

        time!!.text = ""
        time!!.setTextColor(Color.parseColor("#FFFFFF"))
        time!!.maxLines=2
        //time!!.setBackgroundColor(Color.parseColor("#88000000"))
        //time!!.background = getDrawable(R.drawable.corner_time)
        time!!.background = resources.getDrawable(R.drawable.corner_time)
        time!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())

        tv1 = TextView(this)
        tv1!!.textSize = 22F
        tv1!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tv1!!.visibility = View.GONE
        tv1!!.setTextColor(Color.parseColor("#FFFFFF"))
        tv1!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())
        tv1!!.text = "\u2611"

        funnyContainer = LinearLayout(this)
        funnyContainer!!.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        funnyContainer!!.orientation = LinearLayout.VERTICAL
        var fBParams : FrameLayout.LayoutParams

        if(juego == 0)
            fBParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP or Gravity.RIGHT)
        else
            fBParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM or Gravity.RIGHT)
        fBParams.bottomMargin = pxFromDp(this, 5f).toInt()
        fBParams.leftMargin = pxFromDp(this, 5f).toInt()
        fBParams.rightMargin = pxFromDp(this, 5f).toInt()
        fBParams.topMargin = pxFromDp(this, 5f).toInt()

        funnyButton = FunnyButton(this)
        funnyButton!!.layoutParams = fBParams
        funnyButton!!.setCardBackgroundColor(Color.parseColor("#8d6e63"))
        funnyButton!!.setText("" + numVistos + "/" + numMarcadores)
        funnyButton!!.setTextColor(Color.parseColor("#FFFFFF"))
        funnyButton!!.setTextSize(spToPx(15f, this).toFloat())
        funnyButton!!.setMinimumWidth(pxFromDp(this, 100f).toInt())

        funny = Funny(this)
        funny!!.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        val mensajeParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)

        tvMensaje = TextView(this)
        tvMensaje!!.gravity = Gravity.CENTER
        tvMensaje!!.textSize = 20F
        tvMensaje!!.text = "Coloca un marcador frente a la cámara.\nRecuerda que el marcador debe pertenecer\na la lección actual."
        tvMensaje!!.setTextColor(Color.parseColor("#FFFFFF"))
        //tvMensaje!!.setBackgroundColor(Color.parseColor("#88000000"))
        tvMensaje!!.background = resources.getDrawable(R.drawable.corner_msj)
        tvMensaje!!.setPadding(pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt(), pxFromDp(this, 10F).toInt())

        layoutMensaje = FrameLayout(this)
        layoutMensaje!!.setBackgroundColor(Color.parseColor("#00000000"))
        */



        mainLayout!!.addView(camSurface, ViewGroup.LayoutParams(128, 128))
        mainLayout!!.addView(glView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        /*
        mainLayout!!.addView(leccionView, paramsLeccionView)
        mainLayout!!.addView(loadIView, paramsLIView)
        mainLayout!!.addView(botonFelic, paramsBotFelic)
        mainLayout!!.addView(letraRanTV, paramsSeg)
        //mainLayout!!.addView(nombreMarcador, params)
        //mainLayout!!.addView(letraRanTV, paramsSeg)
        //mainLayout!!.addView(segundos, paramsSeg)
        botonFelic!!.visibility = View.INVISIBLE
        imagenPalabra!!.visibility = View.INVISIBLE
        if(juego == 0)
            crearTabla()
        else
            mainLayout!!.addView(imagenPalabra, paramsImPalabra)
        if(leccion0 == 1) {
            Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.abc).into(leccionView!!)
            funnyButton!!.setCardBackgroundColor(Color.parseColor("#c75bfa"))
        } else if(leccion0 == 2) {
            Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.num).into(leccionView!!)
            funnyButton!!.setCardBackgroundColor(Color.parseColor("#33ccff"))
        } else if(leccion0 == 3) {
            Glide.with(this@leccionesARActivity).asDrawable().load(R.drawable.iconopron).into(leccionView!!)
            funnyButton!!.setCardBackgroundColor(Color.parseColor("#ff9999"))
        } else if(leccion0 == 4) {
            Glide.with(this@leccionesARActivity).asDrawable().load(R.drawable.iconopronombres).into(leccionView!!)
            funnyButton!!.setCardBackgroundColor(Color.parseColor("#fbac37"))
        } else if(leccion0 == 5) {
            Glide.with(this@leccionesARActivity).asDrawable().load(R.drawable.iconoracionsimple).into(leccionView!!)
            funnyButton!!.setCardBackgroundColor(Color.parseColor("#49ad3c"))
            //funnyButton!!.setCardBackgroundColor(Color.parseColor("#66ff99"))
        }
        Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.iconpregunta).into(botonFelic!!)
        Glide.with(this@leccionesARActivity).asGif().load(R.drawable.load).into(loadIView!!)


        botonFelic!!.setOnClickListener {
            if(dioClic == 0) {
                dioClic = 1
                val intent = Intent(this@leccionesARActivity, Instrucciones::class.java)
                intent.putExtra("usuario", usuario)
                intent.putExtra("leccion", leccion0)
                intent.putExtra("juego", 0)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
            }
        }

        */

        /*
        //Inicia Job
        job1 = GlobalScope.launch {

            if(juego == 0)
                renovarVistos()
            while(nftCargado == 0){
                nftCargado = nativeGetNFTLoaded()

            }

            Handler(Looper.getMainLooper()).post(object : Runnable {
                override fun run(){
                    //letraRanTV!!.text = ""

                    loadIView!!.visibility = View.INVISIBLE

                    Glide.with(this@leccionesARActivity).clear(loadIView!!)
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
                    else if(leccion0 == 3)
                        stringNombreMarcador = "Lección 'Pronombres'\nNo se encuentra el marcador"
                    else if(leccion0 == 4)
                        stringNombreMarcador = "Lección 'Verbos'\nNo se encuentra el marcador"
                    else if(leccion0 == 5)
                        stringNombreMarcador = "Lección 'Oración Simple'\nNo se encuentra el marcador"
                } else if(leccion0 == 1)
                    stringNombreMarcador = "Lección 'Abecedario'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nLetra "+ abecedario[marcadorDetectado])
                else if(leccion0 == 2)
                    stringNombreMarcador = "Lección 'Números'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nNúmero "+ numeros[marcadorDetectado])
                else if(leccion0 == 3)
                    stringNombreMarcador = "Lección 'Pronombres'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nPronombre "+ pronombres[marcadorDetectado])
                else if(leccion0 == 4)
                    stringNombreMarcador = "Lección 'Verbos'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nVerbo "+ verbos[marcadorDetectado])
                else if(leccion0 == 5)
                    stringNombreMarcador = "Lección 'Oración Simple'" + if (marcadorDetectado >= numMarcadores)  "" else ("\nVerbo "+ oracion[marcadorDetectado])
                numVistos = vistos.sum()
                if (marcadorDetectado >= 0 && marcadorDetectado < numMarcadores && numVistos < numMarcadores) {
                    if (vistos[marcadorDetectado] == 0) {
                        verHazSeña()
                        vistos[marcadorDetectado] = 1
                        viendoMarcador = 1
                        marcadorPrevio = marcadorDetectado
                    } else if (vistos[marcadorDetectado] == 1 && viendoMarcador == 0) {
                        verMarcadorVisto()
                        viendoMarcador = 1
                        marcadorPrevio = marcadorDetectado
                    }
                } else if (marcadorDetectado >= numMarcadores && viendoMarcador == 0) {
                    if ((marcadorDetectado - numMarcadores + 1) != leccion0) {
                        verMarcadorNoLeccion()
                        viendoMarcador = 1
                        marcadorPrevio = marcadorDetectado
                    }
                } else if(marcadorDetectado >= 0){
                    viendoMarcador = 1
                }
                if (marcadorPrevio != marcadorDetectado || marcadorDetectado < 0) {
                    viendoMarcador = 0
                }
                renovarVistos()
                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run(){
                        funnyButton!!.setText("" + numVistos + "/" + numMarcadores)
                        letraRanTV!!.text = stringNombreMarcador
                    }
                })
                if(numVistos == numMarcadores){
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            botonFelic!!.visibility = View.VISIBLE
                            Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.siguienteopc).into(botonFelic!!)

                            botonFelic!!.setOnClickListener {
                                if(dioClic == 0) {
                                    dioClic = 1
                                    val intent = Intent(this@leccionesARActivity, felicitacion_leccion::class.java)
                                    intent.putExtra("usuario", usuario)
                                    intent.putExtra("leccion", leccion0)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                    startActivity(intent)
                                }
                            }
                        }
                    })
                }
                marcadorPrevio = marcadorDetectado
            }
        }
        //Finaliza Job

        */

        //asd

        /*
        if(juego == 0){

            //crearTabla()
            mainLayout!!.addView(layoutMensaje)
            layoutMensaje!!.addView(tvMensaje, mensajeParams)
            mainLayout!!.addView(funny)
            funny!!.addView(funnyButton)
            funnyButton!!.addView(funnyContainer)
            funnyContainer!!.addView(tbl1)
            var ancho = 0f

            layoutMensaje!!.visibility = View.GONE
            if(leccion0 < 3)
                ancho = 320f
            else if (leccion0 < 5)
                ancho = 640f
            else
                ancho = 660f

            funControl = FunControl.Builder()
                .setFunnyLayout(funny)
                .setFunnyButton(funnyButton)
                .setContainer(funnyContainer)
                .setAnimationDuration(400)
                .setGravityToExpand(Gravity.CENTER)
                .setWidthToExpand(pxFromDp(this, ancho).toInt()/*tbl1.width*/)
                .build()

            funnyButton!!.setOnClickListener { funControl!!.expand() }

            funnyContainer!!.setOnClickListener { funControl!!.collapse() }


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



            //Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()


        }else if(leccion0 == 1 && juego == 1){
            mainLayout!!.removeView(botonFelic)
            mainLayout!!.addView(time, paramsTimeTV)
            var paramsBotFelic2 = FrameLayout.LayoutParams(95, 95, Gravity.TOP or Gravity.RIGHT)
            paramsBotFelic2.rightMargin = pxFromDp(this, 150f).toInt()
            paramsBotFelic2.topMargin = pxFromDp(this, 5f).toInt()
            mainLayout!!.addView(botonFelic, paramsBotFelic2)
            Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.iconpregunta).into(botonFelic!!)
            botonFelic!!.setOnClickListener {
                if(dioClic == 0) {
                    dioClic = 1
                    val intent = Intent(this@leccionesARActivity, InstruccionesJuegos::class.java)
                    intent.putExtra("usuario", usuario)
                    intent.putExtra("leccion", leccion0)
                    intent.putExtra("juego", 1)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                }
            }
            botonFelic!!.visibility = View.VISIBLE
            mainLayout!!.addView(palabraIView, paramsPIView)
            mainLayout!!.addView(funny)
            funny!!.addView(funnyButton)
            funnyButton!!.addView(funnyContainer)
            palabraIView!!.visibility = View.INVISIBLE
            funnyButton!!.setText("0/5")


            job2 = GlobalScope.launch {
                while(nftCargado == 0)
                    continue

                while(palabrasTotales < 5){

                    if(palabrasTotales == 0){
                        palabraActual = Random().nextInt(0..39)
                        guardarPalabra[palabraActual] = 1
                    } else {
                        do{
                            iguales = 0
                            palabraActual = Random().nextInt(0..39)

                            if(guardarPalabra[palabraActual] == 0)
                                guardarPalabra[palabraActual] = 1
                            else
                                iguales = 1
                        }while(iguales == 1)
                    }
                    palabraTV = SpannableString(palabras[palabraActual])
                    if(palabraActual == 21)
                        palabraTV.setSpan(colorSpan, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                    else
                        palabraTV.setSpan(colorSpan, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            letraRanTV!!.text = palabraTV
                            palabraIView!!.visibility = View.VISIBLE
                            Glide.with(this@leccionesARActivity).asGif().load(letraGif[palabrasSeparadas[palabraActual][contarLetras]]).into(palabraIView!!)
                        }
                    })
                    while(contarLetras < palabrasSeparadas[palabraActual].size){
                        delay(100L)
                        marcadorDetectado = nativeGetDetectedPage()

                        Log.e(TAG, "Letra a detectar: " + palabrasSeparadas[palabraActual][contarLetras])

                        if(marcadorDetectado < 0)
                            viendoMarcador = 0
                        if(marcadorDetectado == palabrasSeparadas[palabraActual][contarLetras]) {
                            viendoMarcador = 1
                            contarLetras++
                            if(contarLetras >= 0 && palabraActual == 21) {
                                if((contarLetras + 1) < palabras[palabraActual].length) {
                                    palabraTV = SpannableString(palabras[palabraActual])
                                    palabraTV.setSpan(colorSpanV, 0, contarLetras + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    palabraTV.setSpan(colorSpan, contarLetras + 1, contarLetras + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    Handler(Looper.getMainLooper()).post(object : Runnable {
                                        override fun run(){
                                            letraRanTV!!.text = palabraTV
                                            Glide.with(this@leccionesARActivity).asGif().load(letraGif[palabrasSeparadas[palabraActual][contarLetras]]).into(palabraIView!!)
                                        }
                                    })
                                }
                            } else if(contarLetras >= 2 && (palabraActual == 26)){
                                if(contarLetras == 2) {
                                    palabraTV = SpannableString(palabras[palabraActual])
                                    palabraTV.setSpan(colorSpanV, 0, contarLetras, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    palabraTV.setSpan(colorSpan, contarLetras, contarLetras + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    Handler(Looper.getMainLooper()).post(object : Runnable {
                                        override fun run(){
                                            letraRanTV!!.text = palabraTV
                                            Glide.with(this@leccionesARActivity).asGif().load(letraGif[palabrasSeparadas[palabraActual][contarLetras]]).into(palabraIView!!)
                                        }
                                    })
                                } else {
                                    if((contarLetras + 1) < palabras[palabraActual].length) {
                                        palabraTV = SpannableString(palabras[palabraActual])
                                        palabraTV.setSpan(colorSpanV, 0, contarLetras + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                        palabraTV.setSpan(colorSpan, contarLetras + 1, contarLetras + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                        Handler(Looper.getMainLooper()).post(object : Runnable {
                                            override fun run(){
                                                letraRanTV!!.text = palabraTV
                                                Glide.with(this@leccionesARActivity).asGif().load(letraGif[palabrasSeparadas[palabraActual][contarLetras]]).into(palabraIView!!)
                                            }
                                        })
                                    }
                                }
                            } else {
                                if(contarLetras < palabras[palabraActual].length) {
                                    palabraTV = SpannableString(palabras[palabraActual])
                                    palabraTV.setSpan(colorSpanV, 0, contarLetras + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    palabraTV.setSpan(colorSpan, contarLetras, contarLetras + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                    Handler(Looper.getMainLooper()).post(object : Runnable {
                                        override fun run(){
                                            letraRanTV!!.text = palabraTV
                                            Glide.with(this@leccionesARActivity).asGif().load(letraGif[palabrasSeparadas[palabraActual][contarLetras]]).into(palabraIView!!)
                                        }
                                    })
                                }
                            }
                        } else if(marcadorDetectado >= 0 && viendoMarcador == 0){
                            viendoMarcador = 1
                            Handler(Looper.getMainLooper()).post(object : Runnable {
                                override fun run(){
                                    CookieBar.build(this@leccionesARActivity)
                                        .setTitle("Este no es el marcador")
                                        .setMessage("Intenta colocando otro marcador.")
                                        .setCookiePosition(CookieBar.BOTTOM)
                                        .setBackgroundColor(R.color.fondoCookieSeña4)
                                        .setIcon(R.drawable.muneco_toast)
                                        .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                                        .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                                        .setDuration(4000)
                                        .show()
                                }
                            })
                        }
                    }
                    contarLetras = 0
                    palabrasTotales++
                    palabraTV = SpannableString(palabras[palabraActual])
                    palabraTV.setSpan(colorSpanV, 0, palabras[palabraActual].length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                    //verPalabra(palabraActual)
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            letraRanTV!!.text = palabraTV
                            Glide.with(this@leccionesARActivity).clear(palabraIView!!)
                            palabraIView!!.visibility = View.INVISIBLE
                            verPalabra(palabraActual)
                            funnyButton!!.setText("" + palabrasTotales + "/5")
                        }
                    })
                    delay(4000)
                }
                delay(200)
                felicitacion()
            }

            job4 = GlobalScope.launch {
                while (nftCargado == 0)
                    continue

                millisPasados = 0L
                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run(){
                        time!!.text = "Tiempo: 00:00"
                    }
                })
                while (true){
                    delay(1000)
                    millisPasados += 1000
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            time!!.text = String.format("Tiempo: %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisPasados), TimeUnit.MILLISECONDS.toSeconds(millisPasados) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisPasados)))
                        }
                    })
                }
            }

        } else if(leccion0 == 5 && juego == 1){
            mainLayout!!.removeView(botonFelic)
            mainLayout!!.addView(time, paramsTimeTV)
            var paramsBotFelic2 = FrameLayout.LayoutParams(95, 95, Gravity.TOP or Gravity.RIGHT)
            paramsBotFelic2.rightMargin = pxFromDp(this, 150f).toInt()
            paramsBotFelic2.topMargin = pxFromDp(this, 5f).toInt()
            mainLayout!!.addView(botonFelic, paramsBotFelic2)
            Glide.with(this@leccionesARActivity).asDrawable().load(R.mipmap.iconpregunta).into(botonFelic!!)
            botonFelic!!.setOnClickListener {
                if(dioClic == 0) {
                    dioClic = 1
                    val intent = Intent(this@leccionesARActivity, InstruccionesJuegos::class.java)
                    intent.putExtra("usuario", usuario)
                    intent.putExtra("leccion", leccion0)
                    intent.putExtra("juego", 1)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                }
            }
            botonFelic!!.visibility = View.VISIBLE
            mainLayout!!.addView(palabraIView, paramsPIView)
            mainLayout!!.addView(funny)
            funny!!.addView(funnyButton)
            funnyButton!!.addView(funnyContainer)
            palabraIView!!.visibility = View.INVISIBLE
            funnyButton!!.setText("0/10")
            //Glide.with(this@leccionesARActivity).asDrawable().load(R.drawable.el_aprende).into(imagenPalabra!!)
            //imagenPalabra!!.visibility = View.VISIBLE

            job2 = GlobalScope.launch {
                while(nftCargado == 0)
                    continue

                while(palabrasTotales < 10){
                    if(palabrasTotales == 0){
                        palabraActual = Random().nextInt(0..24)
                        guardarPalabra[palabraActual] = 1
                    } else {
                        do{
                            iguales = 0
                            palabraActual = Random().nextInt(0..24)

                            if(guardarPalabra[palabraActual] == 0)
                                guardarPalabra[palabraActual] = 1
                            else
                                iguales = 1
                        }while(iguales == 1)
                    }
                    palabraTV = SpannableString(pronombres[oracionesSeparadas[palabraActual][0]] + " + " + verbos[oracionesSeparadas[palabraActual][1]-5])
                    palabraTV.setSpan(colorSpan, 0, pronombres[oracionesSeparadas[palabraActual][0]].length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            letraRanTV!!.text = palabraTV
                            palabraIView!!.visibility = View.VISIBLE
                            Glide.with(this@leccionesARActivity).asGif().load(pronGif[oracionesSeparadas[palabraActual][0]]).into(palabraIView!!)
                        }
                    })
                    while(palabraCompleta == 0){
                        delay(100L)
                        marcadorDetectado = nativeGetDetectedPage()
                        //Toast.makeText(this@leccionesARActivity, "" + marcadorDetectado, Toast.LENGTH_SHORT).show()
                        if (marcadorDetectado < 0)
                            viendoMarcador = 0
                        if(marcadorDetectado > 4 && pronombrePuesto == -1 && viendoMarcador == 0){
                            viendoMarcador = 1
                            Handler(Looper.getMainLooper()).post(object : Runnable {
                                override fun run(){
                                    CookieBar.build(this@leccionesARActivity)
                                        .setTitle("Este no es el marcador")
                                        .setMessage("Intenta colocando otro marcador.")
                                        .setCookiePosition(CookieBar.BOTTOM)
                                        .setBackgroundColor(R.color.fondoCookieSeña4)
                                        .setIcon(R.drawable.muneco_toast)
                                        .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                                        .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                                        .setDuration(4000)
                                        .show()
                                }
                            })
                        } else if(marcadorDetectado >=0 && marcadorDetectado < 5 && pronombrePuesto == -1 && viendoMarcador == 0){
                            viendoMarcador = 1
                            if(marcadorDetectado == oracionesSeparadas[palabraActual][0]){
                                pronombrePuesto = marcadorDetectado
                                palabraTV = SpannableString(pronombres[oracionesSeparadas[palabraActual][0]] + " + " + verbos[oracionesSeparadas[palabraActual][1]-5])
                                palabraTV.setSpan(colorSpanV, 0, pronombres[oracionesSeparadas[palabraActual][0]].length + 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                palabraTV.setSpan(colorSpan, pronombres[oracionesSeparadas[palabraActual][0]].length + 3, palabraTV.lastIndex + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                Handler(Looper.getMainLooper()).post(object : Runnable {
                                    override fun run(){
                                        letraRanTV!!.text = palabraTV
                                        Glide.with(this@leccionesARActivity).asGif().load(verbGif[oracionesSeparadas[palabraActual][1] - 5]).into(palabraIView!!)
                                    }
                                })
                            } else {
                                Handler(Looper.getMainLooper()).post(object : Runnable {
                                    override fun run(){
                                        CookieBar.build(this@leccionesARActivity)
                                            .setTitle("Este no es el marcador")
                                            .setMessage("Intenta colocando otro marcador.")
                                            .setCookiePosition(CookieBar.BOTTOM)
                                            .setBackgroundColor(R.color.fondoCookieSeña4)
                                            .setIcon(R.drawable.muneco_toast)
                                            .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                                            .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                                            .setDuration(4000)
                                            .show()
                                    }
                                })
                            }
                        } else if(marcadorDetectado >=0 && marcadorDetectado < 5 && verboPuesto == -1 && pronombrePuesto >= 0 && viendoMarcador == 0){
                            viendoMarcador = 1
                            Handler(Looper.getMainLooper()).post(object : Runnable {
                                override fun run(){
                                    CookieBar.build(this@leccionesARActivity)
                                        .setTitle("Este no es el marcador")
                                        .setMessage("Intenta colocando otro marcador.")
                                        .setCookiePosition(CookieBar.BOTTOM)
                                        .setBackgroundColor(R.color.fondoCookieSeña4)
                                        .setIcon(R.drawable.muneco_toast)
                                        .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                                        .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                                        .setDuration(4000)
                                        .show()
                                }
                            })
                        } else if(marcadorDetectado > 4 && verboPuesto == -1 && pronombrePuesto >= 0 && viendoMarcador == 0){
                            viendoMarcador = 1
                            if(marcadorDetectado == oracionesSeparadas[palabraActual][1]){
                                verboPuesto = marcadorDetectado
                                palabraTV = SpannableString(pronombres[oracionesSeparadas[palabraActual][0]] + " + " + verbos[oracionesSeparadas[palabraActual][1]-5])
                                palabraTV.setSpan(colorSpanV, 0, palabraTV.lastIndex + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                //palabraTV.setSpan(colorSpan, pronombres[oracionesSeparadas[palabraActual][0]].length + 3, verbos[oracionesSeparadas[palabraActual][1]].length - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                Handler(Looper.getMainLooper()).post(object : Runnable {
                                    override fun run(){
                                        letraRanTV!!.text = palabraTV
                                        Glide.with(this@leccionesARActivity).clear(palabraIView!!)
                                        palabraIView!!.visibility = View.INVISIBLE
                                    }
                                })
                                palabraCompleta = 1
                            } else {
                                Handler(Looper.getMainLooper()).post(object : Runnable {
                                    override fun run(){
                                        CookieBar.build(this@leccionesARActivity)
                                            .setTitle("Este no es el marcador")
                                            .setMessage("Intenta colocando otro marcador.")
                                            .setCookiePosition(CookieBar.BOTTOM)
                                            .setBackgroundColor(R.color.fondoCookieSeña4)
                                            .setIcon(R.drawable.muneco_toast)
                                            .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                                            .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                                            .setDuration(4000)
                                            .show()
                                    }
                                })
                            }
                        }
                    }
                    pronombrePuesto = -1
                    verboPuesto = -1
                    palabraCompleta = 0
                    palabrasTotales++
                    //verOracion(palabraActual)
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            verOracion(palabraActual)
                            funnyButton!!.setText("" + palabrasTotales + "/10")
                        }
                    })
                    delay(4000)
                }
                delay(200)
                felicitacion()
            }

            job4 = GlobalScope.launch {
                while (nftCargado == 0)
                    continue

                millisPasados = 0L
                Handler(Looper.getMainLooper()).post(object : Runnable {
                    override fun run(){
                        time!!.text = "Tiempo: 00:00"
                    }
                })
                while (true){
                    delay(1000)
                    millisPasados += 1000
                    Handler(Looper.getMainLooper()).post(object : Runnable {
                        override fun run(){
                            time!!.text = String.format("Tiempo: %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisPasados), TimeUnit.MILLISECONDS.toSeconds(millisPasados) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisPasados)))
                        }
                    })
                }
            }
        }
        */

        if (glView != null) glView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        nftCargado = 0
        if (glView != null) glView!!.onPause()

        // System hardware must be release in onPause(), so it's available to
        // any incoming activity. Removing the CameraPreview will do this for the
        // camera. Also do it for the GLSurfaceView, since it serves no purpose
        // with the camera preview gone.

        /*
        if(job1 != null) {
            GlobalScope.launch(UI) {
                job1!!.cancelAndJoin()
            }
        }

        if(juego == 0){
            /*
            GlobalScope.launch(UI){
                job4!!.cancelAndJoin()
            }
            */
            if(job2 != null) {
                GlobalScope.launch(UI) {
                    job2!!.cancelAndJoin()
                    //job3!!.cancelAndJoin()
                }
            }
            if(funnyContainer != null && tbl1 != null)
                funnyContainer!!.removeView(tbl1)
            if(funnyButton != null && funnyContainer != null)
                funnyButton!!.removeView(funnyContainer)
            if(funny != null && funnyButton != null)
                funny!!.removeView(funnyButton)
            if(funny != null)
                mainLayout!!.removeView(funny)
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
            if(job3 != null) {
                GlobalScope.launch(UI) {
                    job3!!.cancelAndJoin()
                }
            }
            if(job4 != null) {
                GlobalScope.launch(UI) {
                    job4!!.cancelAndJoin()
                }
            }
            //mainLayout!!.removeView(segundos)
            if(imagenPalabra != null)
                mainLayout!!.removeView(imagenPalabra)
            if(funny != null && funnyButton != null)
                funny!!.removeView(funnyButton)
            if(funny != null)
                mainLayout!!.removeView(funny)
        }

         */

        /*
        if(nombreMarcador != null)
            mainLayout!!.removeView(nombreMarcador)*/

        /*
        if(letraRanTV != null)
            mainLayout!!.removeView(letraRanTV)
        if(botonFelic != null)
            mainLayout!!.removeView(botonFelic)
        if(loadIView != null)
            mainLayout!!.removeView(loadIView)
        if(leccionView != null)
            mainLayout!!.removeView(leccionView)

         */
        mainLayout!!.removeView(glView)
        mainLayout!!.removeView(camSurface)
    }

    public override fun onStop() {
        super.onStop()

        nftCargado = 0

        /*
        if(juego == 0){
            var archivo = ""

            for (i in 0..(vistos.size-1)){
                if(i < (vistos.size-1))
                    archivo += "" + vistos[i] + "\n"
                else
                    archivo += "" + vistos[i]
            }

            this.openFileOutput(usuario.replace("@","_").replace(".", "_")+"_" + leccion0 + ".dat", Context.MODE_PRIVATE).use {
                it.write(archivo.toByteArray())
            }

            var db = ManejadorDB(this)

            var progreso = (db.checarCalificacion(usuario, leccion0) * 3) + (vistos.sum() * 70)/numMarcadores
            db.actualizarAvance(usuario, leccion0, progreso.toInt())


        }

        GlobalScope.launch(UI){
            job1!!.cancelAndJoin()
        }


        GlobalScope.launch(UI){
            job2!!.cancelAndJoin()
            //job3!!.cancelAndJoin()
        }
        //}

        if(juego == 1){
            if(job3 != null) {
                GlobalScope.launch(UI) {
                    job3!!.cancelAndJoin()
                }
            }
            if(job4 != null) {
                GlobalScope.launch(UI) {
                    job4!!.cancelAndJoin()
                }
            }
        }

        */
        nativeStop()
    }

    public override fun onDestroy() {
        super.onDestroy()

        nftCargado = 0

        /*
        GlobalScope.launch(UI){
            job1!!.cancelAndJoin()
        }


        GlobalScope.launch(UI){
            job2!!.cancelAndJoin()
            //job3!!.cancelAndJoin()
        }
        //}
        if(juego == 1){
            if(job3 != null) {
                GlobalScope.launch(UI) {
                    job3!!.cancelAndJoin()
                }
            }
            if(job4 != null) {
                GlobalScope.launch(UI) {
                    job4!!.cancelAndJoin()
                }
            }
        }
        */
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

        // We won't use the orientation from the config, as it only tells us the layout type
        // and not the actual orientation angle.
        //int nativeOrientation;
        //int orientation = newConfig.orientation; // Only portrait or landscape.
        //if (orientation == Configuration.ORIENTATION_LANSCAPE) nativeOrientation = 0;
        //else /* orientation == Configuration.ORIENTATION_PORTRAIT) */ nativeOrientation = 1;
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
                /*
                Toast.makeText(applicationContext,
                        "Application will not run with camera access denied",
                        Toast.LENGTH_LONG).show()
                */
            } else if (1 <= permissions.size) {
                /*
                Toast.makeText(applicationContext,
                        String.format("Camera access permission \"%s\" allowed", permissions[0]),
                        Toast.LENGTH_SHORT).show()
                */
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