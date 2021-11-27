package com.gk.juc.homework;

/**
 * @author gaot
 * @date 2021/11/27
 */
public class Calculator {
    public static long fibonacci(int index) {
        if (index < 2) {
            return 1;
        }
        return fibonacci(index - 1) + fibonacci(index - 2);
    }
}