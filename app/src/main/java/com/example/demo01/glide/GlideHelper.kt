package com.example.demo01.glide

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

object GlideHelper {

    fun loadImage(
        context: Context,
        imageView:ImageView,
        imgUrl: String = "https://img95.699pic.com/photo/50136/1351.jpg_wh300.jpg"
    ) {
        //显示图片
        Glide.with(context)
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