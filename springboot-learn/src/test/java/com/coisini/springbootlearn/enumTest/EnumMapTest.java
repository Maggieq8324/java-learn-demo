package com.coisini.springbootlearn.enumTest;

import java.util.EnumMap;
import java.util.Map;

public enum EnumMapTest {
    MON, TUE, WED, THU, FRI, SAT, WEE;

    public static void main(String[] args) {
        EnumMap<EnumMapTest, String> enumMap = new EnumMap<EnumMapTest, String>(EnumMapTest.class);
        enumMap.put(MON, "Monday");
        enumMap.put(TUE, "Tuesday");

        for (Map.Entry<EnumMapTest, String> em : enumMap.entrySet()) {
            System.out.println(em.getKey() + " " + em.getValue()); // MON Monday TUE Tuesday
        }
    }
}

