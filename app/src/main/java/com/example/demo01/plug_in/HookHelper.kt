package com.example.demo01.plug_in

import android.app.Instrumentation
import android.content.Context
import java.lang.reflect.Field


object HookHelper {
    const val TARGET_INTENT_NAME = "TARGET_INTENT_NAME"

    fun hookInstrumentation(context: Context) {
        val contextImplClass = Class.forName("android.app.ContextImpl")
        val mMainThreadField: Field = FieldUtil.getField(contextImplClass, "mMainThread") //1
        val activityThread: Any = mMainThreadField.get(context) //2
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val mInstrumentationField: Field =
            FieldUtil.getField(activityThreadClass, "mInstrumentation") //3
        FieldUtil.setField(
            activityThreadClass, activityThread, "mInstrumentation", InstrumentationProxy(
                (mInstrumentationField.get(activityThread) as Instrumentation)!!,
                context.packageManager
            )
        )
    }
}