package com.example.demo01

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.demo01.leakcanary.LeakCanaryHelper
import com.example.demo01.retrofit.Repo
import com.example.demo01.retrofit.RetrofitHelper
import com.example.demo01.service.MyService1
import com.example.demo01.thread_pool.ThreadPoolHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity2 : AppCompatActivity() {

    private var myService: MyService1? = null
    private var isBound = false

    private val myHandlerThread = MyHandlerThread("test")

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
            Toast.makeText(this@MainActivity2, "广播数据：$resultData",Toast.LENGTH_SHORT).show();
        }
    }

    private lateinit var  button :Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("zgq","onCreate")
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.btn)
        Log.d("zgq","onCreate-width:"+button.width)
        button.setOnClickListener {
//            test2()
            startActivity(Intent(this, Main3Activity::class.java))
            //startService(Intent(this, HelloService::class.java))
            //startService(Intent(this, HelloIntentService::class.java))
            //bindService(Intent(this,MyService::class.java),serviceConnection, Context.BIND_AUTO_CREATE)
            //startService(Intent(this, MyForegroundService::class.java))
        }
        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            val view = View.inflate(this,R.layout.example2_fragment,null)
            val lp = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
            lp.gravity = Gravity.LEFT or Gravity.TOP
            lp.x = 0
            lp.y = 100
            val windowManager  = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.addView(
                view,
                lp
            )
        }

        // 注册广播接收器
        val filter = IntentFilter("com.example.ACTION_FROM_SERVICE")
        registerReceiver(resultReceiver, filter)


        val myBtn = findViewById<MyButton>(R.id.my_btn)
        myBtn.setOnClickListener {
            Log.d("zgq","myBtn setOnClickListener")
        }

        val myLiner = findViewById<MyLinerLayout>(R.id.myLiner)
        myLiner.setOnClickListener {
            Log.d("zgq","myLiner setOnClickListener")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("zgq","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("zgq","onResume")
        Log.d("zgq","onResume-width:"+button.width)
        Log.d("zgq","onResume-measuredWidth:"+button.measuredWidth)
        button.post {
            Log.d("zgq","onResume-width-post:"+button.width)
        }

        ThreadPoolHelper().threadPoolTest()
        GlobalScope.launch {
            LeakCanaryHelper.testReferenceQueueUsage()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username","zgq")
        Log.d("zgq","onSaveInstanceState")
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            val userName = savedInstanceState.getString("username")
            Toast.makeText(this, userName, Toast.LENGTH_SHORT).show()
        }
        Log.d("zgq","onRestoreInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zgq","onDestroy")
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
        unregisterReceiver(resultReceiver)
        myHandlerThread.quit()
    }

    override fun onPause() {
        super.onPause()
        Log.d("zgq","onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("zgq","onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("zgq","onStop")
    }

    private fun test() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val call = RetrofitHelper.getGitHubService().listRepos("octocat")
                //同步请求
                Log.d("zgq",call.execute().body().toString())
                //异步请求
                call.enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(
                        call: Call<List<Repo>>,
                        response: Response<List<Repo>>
                    ) {

                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                    }
                })
            }
            //挂起函数写法
            val list = RetrofitHelper.getGitHubService().listRepos2("octocat")
        }
    }

    private fun test2() {
       val imageView =  findViewById<ImageView>(R.id.iv)
        val imgUrl = "https://img95.699pic.com/photo/50136/1351.jpg_wh300.jpg"
        //显示图片
        Glide.with(this)
            .load(imgUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("zgq", "网络访问失败，请检查是否开始网络或者增加http的访问许可")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("zgq", "网络访问成功，可以显示图片")
                    return false
                }
            })
            .into(object : ImageViewTarget<Drawable?>(imageView) {
                //图片开始加载
                override fun onLoadStarted(@Nullable placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    Log.d("zgq", "图片开始加载")
                }

                override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Log.d("zgq", "图片加载失败")
                }

                //图片加载完成
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    super.onResourceReady(resource, transition)
                    // 图片加载完成
                    imageView.setImageDrawable(resource)
                    Log.d("zgq", "图片加载完成")
                }

                override fun setResource(@Nullable resource: Drawable?) {
                    Log.d("zgq", "设置资源")
                }
            })
    }


}