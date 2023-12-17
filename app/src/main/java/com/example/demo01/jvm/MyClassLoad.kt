package com.example.demo01.jvm

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


class MyClassLoad: ClassLoader() {

    @Throws(ClassNotFoundException::class)
    override fun findClass(name: String?): Class<*> {
        val classData = loadClassData(name!!)
        return if (classData == null) {
            throw ClassNotFoundException()
        } else {
            defineClass(name, classData, 0, classData.size)
        }
    }

    private fun loadClassData(className: String): ByteArray? {
        //val fileName = className.replace('.', File.separatorChar) + ".class"
        try {
            javaClass.getClassLoader().getResourceAsStream(className).use { inputStream ->
                ByteArrayOutputStream().use { byteStream ->
                    var data: Int
                    while (inputStream.read().also { data = it } != -1) {
                        byteStream.write(data)
                    }
                    return byteStream.toByteArray()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }


}