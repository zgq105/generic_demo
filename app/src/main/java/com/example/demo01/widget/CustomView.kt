package com.example.demo01.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.demo01.R


class CustomView : LinearLayout {

    private lateinit var titleTextView: TextView
    private lateinit var iconImageView: ImageView

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        // 使用布局填充器加载 XML 布局
        LayoutInflater.from(context).inflate(R.layout.custom_view_layout, this, true)

        // 初始化子视图
        titleTextView = findViewById(R.id.titleTextView)
        iconImageView = findViewById(R.id.iconImageView)

        // 获取自定义属性值
        // 获取自定义属性值
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView)
        try {
            val title = typedArray.getString(R.styleable.CustomView_customTitle)
            val iconResId = typedArray.getResourceId(R.styleable.CustomView_customIcon, 0)

            // 设置属性值
            titleTextView.text = title
            iconImageView.setImageResource(iconResId)
        } finally {
            typedArray.recycle()
        }
    }

    // 提供方法设置标题文本
    fun setTitleText(title: String) {
        titleTextView.text = title
    }

    // 提供方法设置图标资源
    fun setIcon(resId: Int) {
        iconImageView.setImageResource(resId)
    }
}
