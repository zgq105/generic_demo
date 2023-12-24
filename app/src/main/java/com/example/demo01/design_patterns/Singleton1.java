package com.example.demo01.design_patterns;

/**
 * 饿汉式（Eager Initialization）：
 *
 * 优点：线程安全，实现简单。
 * 缺点：在类加载时即进行实例化，可能导致资源浪费。
 */
public class Singleton1 {
    private static final Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return singleton1;
    }

    public static void test() {
        //假设外部调用了这个静态方法，而且没有调用getInstance方法，也会导致singleton1对象的创建，因为在类加载时即进行实例化，可能导致浪费资源
    }
}
