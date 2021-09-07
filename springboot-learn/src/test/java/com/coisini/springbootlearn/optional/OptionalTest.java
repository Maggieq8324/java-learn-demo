package com.coisini.springbootlearn.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @Description Optional Test
 * @author coisini
 * @date Aug 17, 2021
 * @Version 1.0
 */
public class OptionalTest {

    @Test
    public void testOptional() {
        /**
         * empty 函数
         * 返回一个值为空的Optional实例
         * 访问 emptyOpt 变量的值会导致 NoSuchElementException。
         */
//        Optional<String> emptyOpt = Optional.empty();
//        emptyOpt.get();

        /**
         * of 函数
         * 如果传入的值存在，就返回包含该值的Optional对象，
         * 否则就抛出NullPointerException异常
         */
//        Optional<String> ofOpt = Optional.of("a");
//        ofOpt.get();

        /**
         * ofNullableOpt 函数
         * 如果传入的值存在，就返回包含该值的Optional对象，否则返回一个空的Optional对象
         * 如果 ofNullableOpt 值为null访问会导致 NoSuchElementException。
         */
        Optional<String> ofNullableOpt = Optional.ofNullable("a");
        System.out.println(ofNullableOpt.get());

//        // ifPresent 值存在为true进入
        ofNullableOpt.ifPresent(t-> System.out.println(t));
//
//        t2.ifPresent(System.out::println);
//
//        System.out.println(t2.orElse("b"));
//
//        t2.orElseThrow(() -> new RuntimeException("is null"));
//
//        /**
//         * 链式操作
//         * map 将其再次包装为Optional
//         */
//        t2.map(t-> t + "b").ifPresent(System.out::println);

    }

}
