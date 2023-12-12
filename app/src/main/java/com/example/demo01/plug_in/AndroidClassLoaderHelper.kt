package com.example.demo01.plug_in

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import com.example.demo01.R
import dalvik.system.DexClassLoader
import java.io.File


object AndroidClassLoaderHelper {

    private const val TAG = "AndroidClassLoaderHelper"

    /**
     * 加载代码
     */
    fun loadClassTest(context: Context) {
        val dexPath = "file:///android_asset/teat.apk"
        val dexOutput = context.cacheDir.absolutePath + File.separator + "DEX"
        if (!File(dexOutput).exists()) {
            File(dexOutput).mkdirs()
        }
        Log.d(TAG, dexOutput)
        val dexClassLoader = DexClassLoader(dexPath, dexOutput, null, context.classLoader)
        try {
            val cls = dexClassLoader.loadClass("com.example.demo01.adapter.MyAdapter")
            Log.d(TAG, "cls:$cls")
        } catch (e: ClassNotFoundException) {
        }
    }

    //加载资源
    fun getApkDrawable(context: Context, drawableName: String): Drawable? {
        val dexPath = "file:///android_asset/teat.apk"
        val dexOutput = context.cacheDir.absolutePath + File.separator + "DEX"
        if (!File(dexOutput).exists()) {
            File(dexOutput).mkdirs()
        }
        Log.d(TAG, dexOutput)
        val dexClassLoader = DexClassLoader(dexPath, dexOutput, null, context.classLoader)
        try {
            val cls = dexClassLoader.loadClass(context.packageName + ".R\$drawable")
            Log.d(TAG, "cls:$cls")
            val field = cls.getDeclaredField(drawableName)
            val resId = field.getInt(R.id::class.java) //得到图片id
            val resource = getPluginResources(context)!!
            val drawable = resource!!.getDrawable(resId)
            Log.d(TAG, "drawable:$drawable")
            return drawable
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null

    }


    private fun getPluginResources(context: Context): Resources {
        val assetManager = AssetManager::class.java.newInstance()
        val addAssetPath = assetManager.javaClass.getMethod(
            "addAssetPath",
            String::class.java
        )
        val dexPath = "file:///android_asset/teat.apk"
        //将插件包地址添加进行
        addAssetPath.invoke(assetManager, dexPath)
        val superRes = context.resources
        return Resources(
            assetManager, superRes.displayMetrics,
            superRes.configuration
        )
    }

}