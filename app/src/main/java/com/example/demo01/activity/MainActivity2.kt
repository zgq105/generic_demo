package com.example.demo01.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.common.IMyService
import com.example.common.ServiceGetter
import com.example.demo01.MyButton
import com.example.demo01.MyHandlerThread
import com.example.demo01.MyLinerLayout
import com.example.demo01.R
import com.example.demo01.Test
import com.example.demo01.cpu.CpuTestHelper
import com.example.demo01.glide.GlideHelper
import com.example.demo01.hashtable.HashTableHelper
import com.example.demo01.jvm.MyClassLoad
import com.example.demo01.leakcanary.FileDescriptorHelper
import com.example.demo01.leakcanary.LeakCanaryHelper
import com.example.demo01.retrofit.RetrofitHelper
import com.example.demo01.service.HelloService
import com.example.demo01.service.MyService1
import com.example.demo01.thread_pool.ThreadPoolHelper
import com.example.nativelib_m3.NativeLib
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity2 : AppCompatActivity() {


    private var myService: MyService1? = null
    private var isBound = false

    private val myHandlerThread = MyHandlerThread("test")

    private lateinit var aa:String
    private val bb by lazy { "hello" }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService1.MyBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    private val resultReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val resultData = intent?.getStringExtra("data")
            Toast.makeText(this@MainActivity2, "广播数据：$resultData", Toast.LENGTH_SHORT).show();
        }
    }

    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aa = "1"
        Log.d("zgq", "MainActivity2 onCreate")
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.btn)
        Log.d("zgq", "onCreate-width:" + button.width)
        button.setOnClickListener {
            //startActivity(Intent(this, Main3Activity::class.java))
            val myService = ServiceGetter.getService(IMyService::class.java)
            myService?.test()
        }
        findViewById<Button>(R.id.btn_stop).setOnClickListener {
//            val view = View.inflate(this,R.layout.example2_fragment,null)
//            val lp = WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                PixelFormat.TRANSLUCENT
//            )
//            lp.gravity = Gravity.LEFT or Gravity.TOP
//            lp.x = 0
//            lp.y = 100
//            val windowManager  = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            windowManager.addView(
//                view,
//                lp
//            )

            RetrofitHelper.test()
        }

        // 注册广播接收器
//        val filter = IntentFilter("com.example.ACTION_FROM_SERVICE")
//        registerReceiver(resultReceiver, filter)


        val myBtn = findViewById<MyButton>(R.id.my_btn)
        myBtn.setOnClickListener {
//            testGlideHelper()
            //mainHandler.sendEmptyMessage(0)
//            FileDescriptorHelper.testNetConnect()
//            FileDescriptorHelper.testFileIo(this)
//            CpuTestHelper.cpuTest()
//            CpuTestHelper.i = 0
//            CpuTestHelper.cpuTest2()
//            CpuTestHelper.cpuTest3()
            HashTableHelper.test()

        }

        val myLiner = findViewById<MyLinerLayout>(R.id.myLiner)
        myLiner.setOnClickListener {
            Log.d("zgq", "myLiner setOnClickListener")
        }

        lifecycleScope.launch {
            lifecycle.whenStarted {
                delay(5000)
                Log.d("zgq", "whenStarted")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("zgq", "onStart")
    }

    override fun onResume() {
        super.onResume()
        //ThreadPoolHelper().testCustom()
//        GlobalScope.launch {
//            LeakCanaryHelper.testWeakReference()
//        }
        val getJniData = NativeLib().getParamsFromClassObj(NativeLib.ClassObj())
        Toast.makeText(this@MainActivity2, getJniData, Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username", "zgq")
        Log.d("zgq", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            val userName = savedInstanceState.getString("username")
            Toast.makeText(this, userName, Toast.LENGTH_SHORT).show()
        }
        Log.d("zgq", "onRestoreInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zgq", "onDestroy")
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
        unregisterReceiver(resultReceiver)
        myHandlerThread.quit()
    }

    override fun onPause() {
        super.onPause()
        Log.d("zgq", "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("zgq", "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("zgq", "onStop")
    }


    private fun testGlideHelper() {
        val imageView = findViewById<ImageView>(R.id.iv)
        GlideHelper.loadImage(this, imageView)
    }

    private val mainHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //模拟内存抖动操作：短期内大量对象进入新生代，触发频繁GC
            for (i in 0..1000) {
                val arr = intArrayOf(100)
                arr[0] = 10
            }
            sendEmptyMessage(0)
        }
    }


}