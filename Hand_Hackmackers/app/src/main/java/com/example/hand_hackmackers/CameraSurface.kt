package com.example.hand_hackmackers


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Build
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
//import androidx.core.content.ContextCompat
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import java.io.IOException

class CameraSurface : SurfaceView, SurfaceHolder.Callback, Camera.PreviewCallback {
    private val TAG = "CameraSurface"
    private var camera: Camera? = null
    //var detectedPage = 0

    private var mustAskPermissionFirst = false
    fun gettingCameraAccessPermissionsFromUser(): Boolean {
        return mustAskPermissionFirst
    }

    fun resetGettingCameraAccessPermissionsFromUserState() {
        mustAskPermissionFirst = false
    }

    constructor(context: Context) : super(context){


        Log.i(TAG, "CameraSurface(): ctor called")
        val activityRef = context as Activity

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(
                        activityRef,
                        Manifest.permission.CAMERA)) {
                    mustAskPermissionFirst = true
                    if (activityRef.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        // Will drop in here if user denied permissions access camera before.
                        // Or no uses-permission CAMERA element is in the
                        // manifest file. Must explain to the end user why the app wants
                        // permissions to the camera devices.
                        //Toast.makeText(activityRef.applicationContext,
                        //        "App requires access to camera to be granted",
                        //        Toast.LENGTH_SHORT).show()
                    }
                    // Request permission from the user to access the camera.
                    Log.i(TAG, "CameraSurface(): must ask user for camera access permission")
                    activityRef.requestPermissions(arrayOf(Manifest.permission.CAMERA),
                        Leccion_AR.REQUEST_CAMERA_PERMISSION_RESULT)
                    return
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "CameraSurface(): exception caught, " + ex.message)
            return
        }

        val holder = holder
        holder.addCallback(this)
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS) // Deprecated in API level 11. Still required for API levels <= 10.
    }

    // SurfaceHolder.Callback methods

    @SuppressLint("NewApi")
    override fun surfaceCreated(holder: SurfaceHolder) {

        Log.i(TAG, "Opening camera.")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                val cameraIndex = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString("pref_cameraIndex", "0"))
                camera = Camera.open(cameraIndex)
            } else {
                camera = Camera.open()
            }
        } catch (exception: RuntimeException) {
            Log.e(TAG, "Cannot open camera. It may be in use by another process.")
        }

        if (camera != null) {
            try {

                camera!!.setPreviewDisplay(holder)
                //camera.setPreviewCallback(this);
                camera!!.setPreviewCallbackWithBuffer(this) // API level 8 (Android 2.2)

            } catch (exception: IOException) {
                Log.e(TAG, "Cannot set camera preview display.")
                camera!!.release()
                camera = null
            }

        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

        if (camera != null) {
            Log.i(TAG, "Closing camera.")
            camera!!.stopPreview()
            camera!!.setPreviewCallback(null)
            camera!!.release()
            camera = null
        }
    }

    @SuppressLint("NewApi") // CameraInfo
    override// setPreviewFrameRate
    fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {

        if (camera != null) {

            //val camResolution = PreferenceManager.getDefaultSharedPreferences(context).getString("pref_cameraResolution", resources.getString(R.string.pref_defaultValue_cameraResolution))
            val camResolution = PreferenceManager.getDefaultSharedPreferences(context).getString("pref_cameraResolution", "960x1280")
            val dims = camResolution!!.split("x".toRegex(), 2).toTypedArray()
            var parameters: Camera.Parameters = camera!!.parameters
            parameters.setPreviewSize(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]))
            parameters.previewFrameRate = 30
            camera!!.parameters = parameters

            parameters = camera!!.parameters
            val capWidth = parameters.previewSize.width
            val capHeight = parameters.previewSize.height
            val pixelformat = parameters.previewFormat // android.graphics.imageformat
            val pixelinfo = PixelFormat()
            PixelFormat.getPixelFormatInfo(pixelformat, pixelinfo)
            var cameraIndex = 0
            var frontFacing = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                val cameraInfo = Camera.CameraInfo()
                cameraIndex = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString("pref_cameraIndex", "0")!!)
                Camera.getCameraInfo(cameraIndex, cameraInfo)
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) frontFacing = true
            }

            val bufSize = capWidth * capHeight * pixelinfo.bitsPerPixel / 8 // For the default NV21 format, bitsPerPixel = 12.

            for (i in 0..4) camera!!.addCallbackBuffer(ByteArray(bufSize))

            camera!!.startPreview()

            //detectedPage = leccionesARActivity.nativeGetDetectedPage()
            //Log.i(TAG, "Marcador detectado " + detectedPage)
            Leccion_AR.nativeVideoInit(capWidth, capHeight, cameraIndex, frontFacing)
        }
    }

    // Camera.PreviewCallback methods.

    override fun onPreviewFrame(data: ByteArray, cam: Camera) {

        Leccion_AR.nativeVideoFrame(data)

        cam.addCallbackBuffer(data)
    }
}