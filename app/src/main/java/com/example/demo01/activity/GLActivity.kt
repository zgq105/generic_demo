package com.example.demo01.activity

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo01.R
import com.example.demo01.opengl.MyGLSurfaceView

class GLActivity : AppCompatActivity() {

    private lateinit var mGLView: GLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建 GLSurfaceView 实例
        mGLView = MyGLSurfaceView(this)

        // 设置为ContentView
        setContentView(mGLView)
    }
}