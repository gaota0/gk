package com.gk.jvm.homework.week1;

/**
 * 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码
 * <br>
 * 求 [0,10) 之间所有偶数的平均值
 *
 * @author gaot
 * @date 2021/10/31
 */
public class Hello {
    public static void main(String[] args) {
        int count = 0;
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sum += i;
                count++;
            }
        }
        double avg = 0;
        if (count > 0) {
            avg = sum / count;
        }
    }
}
