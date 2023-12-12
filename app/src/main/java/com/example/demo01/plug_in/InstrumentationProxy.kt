package com.example.demo01.plug_in

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils


class InstrumentationProxy(
    private val instrumentation: Instrumentation,
    private val packageManager: PackageManager
) :
    Instrumentation() {

    @Override
    fun execStartActivity(
        who: Context?, contextThread: IBinder?, token: IBinder?, target: Activity?,
        intent: Intent, requestCode: Int, options: Bundle?
    ): ActivityResult? {
        val infos: List<ResolveInfo> =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        if (infos == null || infos.isEmpty()) {
            intent.putExtra(HookHelper.TARGET_INTENT_NAME, intent.component!!.className) //1
            intent.setClassName(who!!, "com.example.demo01.activity.StubActivity") //2占坑activity
        }
        try {
            val execMethod = Instrumentation::class.java.getDeclaredMethod(
                "execStartActivity",
                Context::class.java,
                IBinder::class.java,
                IBinder::class.java,
                Activity::class.java,
                Intent::class.java,
                Int::class.javaPrimitiveType,
                Bundle::class.java
            )
            return execMethod.invoke(
                instrumentation, who, contextThread, token,
                target, intent, requestCode, options
            ) as ActivityResult
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun newActivity(cl: ClassLoader?, className: String?, intent: Intent): Activity {
        val intentName = intent.getStringExtra(HookHelper.TARGET_INTENT_NAME)
        return if (!TextUtils.isEmpty(intentName)) {
            super.newActivity(cl, intentName, intent)
        } else super.newActivity(cl, className, intent)
    }

}