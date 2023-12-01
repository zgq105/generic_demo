package com.example.demo01

import android.content.ContentProvider
import android.content.Context
import dalvik.system.DexClassLoader
import java.util.Deque

object Test {

    private lateinit var context: Context;
    fun setContext(context: Context) {
        this.context = context
    }

}