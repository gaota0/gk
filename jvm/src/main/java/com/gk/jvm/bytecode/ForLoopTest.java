package com.gk.jvm.bytecode;

/**
 * @author gaot
 * @date 2021/10/31
 */
public class ForLoopTest {
    private static int[] numbers = {1, 6, 8};
    public static void main(String[] args) {
        final MovingAverage movingAverage = new MovingAverage();
        for (int number : numbers) {
            movingAverage.submit(number);
        }
        final double avg = movingAverage.getAvg();
    }
}
