package com.example.demo01.design_patterns;

/**
 * 懒汉式优点：在需要时才进行实例化，避免了不必要的资源消耗。
 * 双重检查锁：在多线程环境中保持高性能。
 */
public class Singleton2 {
    private static volatile Singleton2 singleton2;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }

        return singleton2;
    }
}
