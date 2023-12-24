package com.example.demo01.design_patterns;

import java.util.UUID;

/**
 * 枚举（Effective Java 建议的方式）：
 * <p>
 * 优点：简单、线程安全、防止反序列化重新创建新的对象、防止反射攻击。
 * 缺点：不支持懒加载。
 */
public enum EnumSingleton {
    INSTANCE;

    // 添加其他方法
    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
