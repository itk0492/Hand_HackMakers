package com.example.hand_hackmackers

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Renderer : GLSurfaceView.Renderer {
    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        Leccion_AR.nativeSurfaceCreated()
    }

    override fun onSurfaceChanged(gl: GL10, w: Int, h: Int) {
        Leccion_AR.nativeSurfaceChanged(w, h)
    }

    override fun onDrawFrame(gl: GL10) {
        Leccion_AR.nativeDrawFrame()
    }
}