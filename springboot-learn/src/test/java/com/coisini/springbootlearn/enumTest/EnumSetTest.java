package com.coisini.springbootlearn.enumTest;

import java.util.EnumSet;

enum EnumSetTest {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, weekend;

    public static void main(String[] args) {
        EnumSet<EnumSetTest> points = EnumSet.noneOf(EnumSetTest.class);
        points.add(Monday);
        System.out.println(points); // [Monday]

        points.addAll(EnumSet.of(Tuesday, Wednesday));
        System.out.println(points); // [Monday, Tuesday, Wednesday]

        points = EnumSet.allOf(EnumSetTest.class);
        System.out.println(points); // [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, weekend]

        points.removeAll(EnumSet.of(Monday, Tuesday));
        System.out.println(points); // [Wednesday, Thursday, Friday, Saturday, weekend]

        points = EnumSet.complementOf(points);
        System.out.println(points); // [Monday, Tuesday]
    }
}
