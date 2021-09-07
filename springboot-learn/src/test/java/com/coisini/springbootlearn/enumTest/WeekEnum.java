package com.coisini.springbootlearn.enumTest;

import java.util.stream.Stream;

public enum WeekEnum {

    Monday(1, "周一"),
    Tuesday(2, "周二"),
    Wednesday(3, "周三"),
    Thursday(4, "周四"),
    Friday(5, "周五"),
    Saturday(6, "周六"),
    weekend(7, "周天");

    private Integer value;
    private String description;

    /**
     * 构造方法
     * @param value
     * @param description
     */
    WeekEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 获取value
     * @return
     */
    public Integer getValue() { return this.value; }

    /**
     * 获取Desc
     * @return
     */
    public String getDesc() { return this.description; }

    /**
     * 枚举值向枚举类型转换
     * @param value
     * @return
     */
    public static WeekEnum toWeekEnum(int value) {
        return Stream.of(WeekEnum.values())
                .filter(item -> item.value == value)
                .findAny()
                .orElse(null);
    }

    /**
     * 重写ToString方法
     * @return
     */
    public String toString() {
        return this.getValue() + " " + this.getDesc();
    }

    public static void main(String[] args) {
        for (WeekEnum item : WeekEnum.values()) {
            System.out.println(item);
        }

        System.out.println(WeekEnum.toWeekEnum(1)); // Monday
    }

}

//enum WeekEnum {
//    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, weekend;
//
//    public static void main(String[] args) {
//        // values() 可以遍历enum实例，其返回enum实例的数组
//        for (WeekEnum item : WeekEnum.values()) {
//            // ordinal()返回每个实例在声明时的次序
//            System.out.println(item.ordinal());
//            // name() 返回enum实例声明时的名称
//            System.out.println(item.name());
//            // getDeclaringClass() 返回其所属的enum类
//            System.out.println(item.getDeclaringClass());
//            // Enum.valueOf() 根据给定的名称返回相应的enum实例
//            System.out.println(Enum.valueOf(WeekEnum.class, item.name()));
//        }
//    }
//}
