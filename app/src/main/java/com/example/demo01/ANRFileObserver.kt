package com.example.demo01

import android.os.FileObserver
import java.io.File

class ANRFileObserver(private val anrPath: String) : FileObserver(anrPath) {
    override fun onEvent(event: Int, path: String?) {
        
    }
}