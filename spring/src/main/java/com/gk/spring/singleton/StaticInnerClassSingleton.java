package com.gk.spring.singleton;

/**
 * @author gaot
 * @date 2021/12/4
 */
public class StaticInnerClassSingleton {

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
    private StaticInnerClassSingleton() {

    }
}
