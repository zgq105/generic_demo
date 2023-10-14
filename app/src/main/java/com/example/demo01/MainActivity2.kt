package com.example.demo01

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.demo01.retrofit.RetrofitHelper
import kotlinx.coroutines.launch
import com.bumptech.glide.request.target.Target


class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn).setOnClickListener {
            test2()
        }

    }

    private fun test() {
        lifecycleScope.launch {
//            withContext(Dispatchers.IO){
//                val call = RetrofitHelper.getGitHubService().listRepos("octocat")
//                Log.d("zgq",call.execute().body().toString())
//            }
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