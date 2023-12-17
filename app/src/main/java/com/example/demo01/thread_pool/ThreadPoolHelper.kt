package com.example.demo01.thread_pool

import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolHelper {

    companion object {
        private const val TAG = "ThreadPoolHelper"
    }

    fun threadPoolTest() {
        //BlockingQueue保存Runnable

        //1.固定线程数量的线程池 （内部LinkedBlockingQueue）
        val executorService = Executors.newFixedThreadPool(5)
//        if (executorService is ThreadPoolExecutor){
//            executorService.allowCoreThreadTimeOut(true)
//        }
        //submit可取消执行的任务
        val future = executorService.submit {

        }

        //2.固定1个线程
        val executorService2 = Executors.newSingleThreadExecutor()
        for (i in 0..30) {
            executorService2.execute {
                Thread.sleep(300)
                //Log.d(TAG, "newSingleThreadExecutor:$i")
            }
        }


        //3缓存线程池
        val executorService3 = Executors.newCachedThreadPool()
        executorService3.execute {

        }

        //4 可调度线程池
        val executorService4 = Executors.newScheduledThreadPool(5)
        executorService4.schedule({
            //Log.d(TAG, "延时任务")
        }, 2, TimeUnit.SECONDS)

        executorService4.scheduleAtFixedRate({
            //Log.d(TAG, "周期任务")
        }, 2, 3, TimeUnit.SECONDS)

    }

    fun test() {
        val executorService = Executors.newSingleThreadExecutor()
//       val future =  executorService.submit(Callable {
//            Thread.sleep(5000)
//            Log.d(TAG, "Callable:"+Thread.currentThread().name)
//        })
//        Executors.newSingleThreadExecutor().execute {
//            Log.d(TAG, "Runnable:"+Thread.currentThread().name)
//            future.cancel(true)
//        }

        Executors.newSingleThreadExecutor().execute {

            val futureTask = FutureTask<String> {
                Thread.sleep(5000)
                "finish"
            }
            executorService.submit(futureTask)
            //阻塞当前线程
            val result = futureTask.get()
            Log.d(TAG, "futureTask-result:$result")
        }
    }


    data class A(val i:Int)

    fun testCustom() {
        val threadPoolExecutor = ThreadPoolExecutor(4,50,3,TimeUnit.SECONDS, LinkedBlockingQueue())
        for (i in 0..6) {
            threadPoolExecutor.execute {
                Thread.sleep(5000)
                Log.d(TAG, "11：" + Thread.currentThread().name)
            }
        }

        threadPoolExecutor.execute {
            Log.d(TAG, "hello：" + Thread.currentThread().name)
        }


    }


}