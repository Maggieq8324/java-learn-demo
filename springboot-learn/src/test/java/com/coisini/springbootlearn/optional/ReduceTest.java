package com.coisini.springbootlearn.optional;

import java.util.Arrays;
import java.util.List;

public class ReduceTest {

//    public static void main(String[] args) {
//
//        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//
//        System.out.println(arrayList); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
//
//        /**
//         * 求和
//         */
//        Integer sum = arrayList.stream().reduce(Integer::sum).orElse(0);
//        System.out.println(sum); // 45
//
//        /**
//         * 求最大值
//         */
//        Integer max = arrayList.stream().reduce(Integer::max).orElse(0);
//        System.out.println(max); // 9
//
//        /**
//         * 求最小值
//         */
//        Integer min = arrayList.stream().reduce(Integer::min).orElse(0);
//        System.out.println(min); // 1
//
//        /**
//         * 逻辑运算
//         * 求最小值
//         */
//        Integer num = arrayList.stream()
//                .reduce((pre, pro) -> {
//                    System.out.println(pre + " " + pro);
//                    return pre + pro;
//                }).orElse(0);
//
//        System.out.println(num); // 1
//
//    }


    public static void main(String[] args) {
        Integer a = 1;

        /**
         * Example 1
         */
        System.out.println("Example1 start");
        switch (a) {
            case 0:
                System.out.println(0);
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            default:
                System.out.println("#");
        }
        System.out.println("Example1 end");

        /**
         * Example 2
         */
        System.out.println("Example2 start");
        switch (a) {
            case 1:
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println("#");
        }
        System.out.println("Example2 end");
    }

}
