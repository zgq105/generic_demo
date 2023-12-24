package com.example.demo01

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

class MyButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatButton(context, attrs) {

    companion object {
        const val TAG = "123"
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("zgq","onTouchEvent")
        return super.onTouchEvent(event)
    }
}