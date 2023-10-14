package com.example.demo01

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

class MyButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatButton(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return false
    }
}