package com.coisini.springbootlearn;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "k1");
        map.put("k2", "k2");

        System.out.println(map); // {k1=k1, k2=k2}

        if (map.containsKey("k1")) {
            map.remove("k1");
        }

        System.out.println(map); // {k2=k2}
    }

    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "k1");
        map.put("k2", "k2");

        System.out.println(map); // {k1=k1, k2=k2}

        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            if ("k1".equals(key)) {
                iter.remove();
            }
        }

        System.out.println(map); // {k2=k2}

    }

}
