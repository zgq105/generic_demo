package com.example.demo01

import android.content.Context
import java.io.Serializable

object Test {

    private lateinit var context: Context;
    fun setContext(context: Context) {
        this.context = context
        val map = HashMap<String,String>()
        map.put("1","")
        map.get("")

        val list = ArrayList<String>()
        list.add("2")

    }


    internal interface MyFunction {
        fun call()
    }


    class Teacher : Serializable {
        companion object {
            //显示声明，避免兼容性问题导致反序列化失败
            private const val serialVersionUID: Long = 1L
        }
    }

}