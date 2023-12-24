package com.example.demo01.design_patterns;

/**
 * 静态内部类：
 * 优点：利用了类加载机制保证线程安全，实现懒加载。
 * 缺点：需要额外的内部类。
 */
public class Singleton3 {

    private Singleton3() {
    }

    private static class InnerSingleton3 {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return InnerSingleton3.INSTANCE;
    }
}
