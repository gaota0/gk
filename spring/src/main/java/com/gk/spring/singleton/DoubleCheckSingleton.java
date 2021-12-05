package com.gk.spring.singleton;

/**
 * @author gaot
 * @date 2021/12/4
 */
public class DoubleCheckSingleton {
    public static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
    private DoubleCheckSingleton() {

    }
}
