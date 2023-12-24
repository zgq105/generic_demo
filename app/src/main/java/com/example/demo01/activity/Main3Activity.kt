package com.example.demo01.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.demo01.R
import com.example.demo01.fragment.ExampleFragment
import com.example.module1.IMyAidlInterface
import com.example.module1.IResponse
import com.example.module1.Person
import com.example.module1.RemoteService


class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, ExampleFragment().apply {
                    arguments = Bundle().apply {
                        putString("key1", "zgq")
                    }
                })
                //addToBackStack("fr1")
            }
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add(R.id.fragment_container_view, Example2Fragment().apply {
//                    arguments = Bundle().apply {
//                        putString("key1", "zgq2")
//                    }
//                })
//                addToBackStack("fr2")
//            }
        }

        supportFragmentManager
            .setFragmentResultListener("requestKey", this) { requestKey, bundle ->
                // We use a String here, but any type that can be put in a Bundle is supported.
                val result = bundle.getString("bundleKey")
                Log.d("zgq", "Main3Activity：$result")
                // Do something with the result.
            }

        //模拟内存泄漏
        //Test.setContext(this)
    }

    override fun onResume() {
        super.onResume()
        //bindService(Intent(this, RemoteService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    private var serviceObj: IMyAidlInterface? = null
    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceObj = IMyAidlInterface.Stub.asInterface(service)
            serviceObj?.registerCallBack(NotifyCallLister())
            Log.d("zgq", "get aidl data:" + serviceObj?.data)
            serviceObj?.data = 100
            serviceObj?.addPerson(
                Person("zgq")
            )

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceObj = null
        }

    }


    //客户端实现回调接口
     class NotifyCallLister : IResponse.Stub() {
         override fun notifySuccess(data: String?) {
             Log.d("zgq", "客户端收到异步回调：$data")
         }

         override fun notifyFail(status: Int) {

         }

     }

}