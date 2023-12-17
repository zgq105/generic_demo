package com.example.demo01.leakcanary

import android.content.Context
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object FileDescriptorHelper {

    fun testNetConnect() {
        GlobalScope.launch {
            val url = URL("https://blog.51cto.com/u_16175498/8636485")
            val connection = url.openConnection() as HttpURLConnection
            try {
                val inputStream = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (inputStream.readLine().also { line = it } != null) {
                    response.append(line)
                }
                Log.d("zgq", "response:$response")
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                //connection.disconnect()
            }

        }
    }

    fun testFileIo(context: Context) {
        GlobalScope.launch {
            var fileInputStream: InputStream? = null
            var content = StringBuilder()
            try {
                fileInputStream = context.assets.open("test.txt")
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (fileInputStream.read(buffer).also { bytesRead = it } != -1) {
                    content.append(String(buffer, 0, bytesRead))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    //fileInputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            Log.d("zgq", "content:$content")
        }
    }
}