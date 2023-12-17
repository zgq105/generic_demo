package com.example.demo01.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: MyGLRenderer

    init {
        // 设置OpenGL ES版本
        setEGLContextClientVersion(2)

        // 设置渲染器
        renderer = MyGLRenderer()
        setRenderer(renderer)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 处理触摸事件
        return true
    }
}
