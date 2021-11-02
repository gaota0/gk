package com.gk.jvm.gc;

import java.util.Random;

/**
 * @author gaot
 * @date 2021/11/1
 */
public class Jcmd {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (; ; ) {
                    final int random = new Random().nextInt(10);
                    final byte[] bytes = new byte[random * 1024 * 1024];
                    try {
                        Thread.sleep(random * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
