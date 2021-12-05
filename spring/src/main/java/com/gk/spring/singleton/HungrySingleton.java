package com.gk.spring.singleton;

/**
 * @author gaot
 * @date 2021/12/4
 */
public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
    private HungrySingleton() {

    }
}
