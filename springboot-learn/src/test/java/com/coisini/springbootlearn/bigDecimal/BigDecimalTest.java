package com.coisini.springbootlearn.bigDecimal;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalTest {

    public static void main(String[] args) {

//        BigDecimal num1 = new BigDecimal("5"); // String
//        BigDecimal num2 = new BigDecimal(5); // int
//        BigDecimal num3 = new BigDecimal(2147483648L); // Long
//        BigDecimal num4 = new BigDecimal(new Double("12.12")); // Double
//
//        System.out.println(num1);
//        System.out.println(num2);
//        System.out.println(num3);
//        System.out.println(num4);


//        BigDecimal num1 = new BigDecimal("5");
//        BigDecimal num2 = new BigDecimal("2");
//
//        // 加法
//        System.out.println(num1.add(num2));
//        // 减法
//        System.out.println(num1.subtract(num2));
//        // 乘法
//        System.out.println(num1.multiply(num2));
//        // 除法
//        System.out.println(num1.divide(num2));
//
//        /**
//         * 大小比较
//         * compare = -1,表示num1小于num2；
//         * compare = 0,表示num1等于num2；
//         * compare = 1,表示num1大于num2；
//         */
//        int compare = num1.compareTo(num2);
//        System.out.println(compare);

        BigDecimal num1 = new BigDecimal("1");
        BigDecimal num2 = new BigDecimal("3");

        System.out.println(num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP));
    }
}
