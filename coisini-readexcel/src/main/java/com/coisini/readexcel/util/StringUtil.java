package com.coisini.readexcel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String toString(Object var0) {
        if (var0 == null) {
            return null;
        } else {
            return var0.toString();
        }
    }

    public static String getType(Object var0) {
        return var0 == null ? "" : getType(var0.getClass());
    }

    public static String toString(Date var0) {
        return toString(var0, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toString(Date var0, String var1) {
        return (new SimpleDateFormat(var1)).format(var0);
    }

}
