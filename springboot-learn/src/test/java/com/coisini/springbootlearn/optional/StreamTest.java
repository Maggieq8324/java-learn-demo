package com.coisini.springbootlearn.optional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void testStream() {
        /**
         * map 方法用于映射每个元素到对应的结果
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(item -> item * 2)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        /**
         * filter 方法用于通过设置的条件过滤出元素
         */
        List<String> strings = Arrays.asList("ab", "bc", "cd", "ef", "ad");
        // 获取空字符串的数量
        strings.stream()
                .filter(str -> str.contains("a"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        /**
         * parallelStream 是流并行处理程序的代替方法
         */
        strings.parallelStream()
                .filter(str -> str.contains("a"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

}
