package com.example.demo01.plug_in

import java.lang.reflect.Field

object FieldUtil {
    fun getField(clazz: Class<*>, target: Any?, name: String?): Any {
        val field: Field = clazz.getDeclaredField(name)
        field.isAccessible = true
        return field.get(target)
    }

    fun getField(clazz: Class<*>, name: String?): Field {
        val field: Field = clazz.getDeclaredField(name)
        field.isAccessible = true
        return field
    }

    fun setField(clazz: Class<*>, target: Any?, name: String?, value: Any?) {
        val field: Field = clazz.getDeclaredField(name)
        field.isAccessible = true
        field.set(target, value)
    }

}