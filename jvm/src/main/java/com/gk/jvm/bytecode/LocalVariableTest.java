package com.gk.jvm.bytecode;

/**
 * @author gaot
 * @date 2021/10/31
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        final MovingAverage ma = new MovingAverage();
        int num1=3;
        int num2=4;
        ma.submit(num1);
        ma.submit(num2);
        final double avg = ma.getAvg();
    }
}
