package com.gk.jvm.homework;

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
/**
 * Compiled from "Hello.java"
 * public class com.gk.jvm.homework.Hello {
 *   public com.gk.jvm.homework.Hello();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: iconst_0                           常量 int 0推送到栈顶
 *        1: istore_1                           栈顶出栈，放到局部变量表的第1个槽位，第0个槽位指向this
 *        2: dconst_0                           常量 double 0压栈
 *        3: dstore_2                           栈顶出栈，放到局部变量表的第2个槽位
 *        4: iconst_0                           常量 int 0 压栈
 *        5: istore        4                    栈顶出栈，放到局部变量表的第4个槽位  double类型占了两个槽位
 *        7: iload         4                    加载第4个槽位的变量 到栈顶
 *        9: bipush        10                   压栈 10
 *       11: if_icmpge     36                   >=栈顶,则go to 36行，否则，继续执行
 *       14: iload         4                    加载第4个槽的变量到栈顶
 *       16: iconst_2                           压栈常量int 2 到栈顶
 *       17: irem                               栈顶两 int类型取模
 *       18: ifne          30                   不等于0时，go to 30行，等于0则继续
 *       21: dload_2                            压栈第二个槽位的double 变量到栈顶
 *       22: iload         4                    压栈第 4个槽位的int 变量到栈顶
 *       24: i2d                                栈顶int -> double
 *       25: dadd                               栈顶double 相加，并压入栈顶
 *       26: dstore_2                           存放栈顶变量到第2 的槽位
 *       27: iinc          1, 1                 给槽位1 的变量+1    count++
 *       30: iinc          4, 1                 给槽位4的变量+1     i++
 *       33: goto          7                    go to 第7行
 *       36: dconst_0                           压栈 double 0     avg=0;
 *       37: dstore        4                    存储 栈顶double 到第4个槽位    此处在for loop的作用域之外，覆盖i的槽位值
 *       39: iload_1                            加载第一个槽位的int
 *       40: ifle          49                   小于等于0时跳转到 49行
 *       43: dload_2                            加载槽位2 的double  sum
 *       44: iload_1                            加载槽位1 的 int    count
 *       45: i2d                                int->double  count
 *       46: ddiv                               sum/count 将结果放在栈顶
 *       47: dstore        4                    存储栈顶变量到第 4个槽位
 *       49: return                             结束
 * }
 */
