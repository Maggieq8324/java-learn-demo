package com.coisini.dynamic.util;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 动态操作yml配置文件工具类
 *  【需要将config配置文件夹和项目jar包放在同级别目录下，这样修改config下的配置文件后，jvm才能及时得获取新的配置】
 * @date Mar 24, 2022
 * @version 1.0
 */
public class YmlUtil {

    public static LinkedHashMap loadYaml(String fileName) throws Exception{
        String path = System.getProperty("user.dir");

        File file = new File(path + "/config/" + fileName);
        InputStream in;
        if (file.exists()) {
            in = new FileInputStream(path + "/config/" + fileName);
        } else {
            // TODO 如果没有config文件夹，则从项目的resources目录下找
            in = YmlUtil.class.getClassLoader().getResourceAsStream(fileName);
        }

        LinkedHashMap lhm = new Yaml().loadAs(in, LinkedHashMap.class);
        return lhm;
    }

    public static Object getValByKey(Map lhm, String key) throws Exception{
        String[] keys = key.split("[.]");
        Map ymlInfo = lhm;
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map) value;
            } else if (value == null) {
                throw new Exception("key不存在");
            } else {
                return value;
            }
        }

        return null;
    }

}
