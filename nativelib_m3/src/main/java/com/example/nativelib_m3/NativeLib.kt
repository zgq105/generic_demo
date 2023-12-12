package com.example.nativelib_m3

class NativeLib {

    /**
     * A native method that is implemented by the 'nativelib_m3' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun getParamsFromJava(int: Int): String

    external fun getParamsFromClassObj(obj: ClassObj): String

    companion object {
        // Used to load the 'nativelib_m3' library on application startup.
        init {
            System.loadLibrary("nativelib_m3")
        }
    }

    class ClassObj {
        fun test(value: String): String {
            return value
        }
    }
}