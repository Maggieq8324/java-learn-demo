package com.coisini.readexcel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileHelper {

    public static FileType getFileType(InputStream is) throws IOException {
        byte[] src = new byte[28];
        is.read(src, 0, 28);
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            if (stringBuilder.toString().startsWith(fileType.getValue())) {
                return fileType;
            }
        }
        return null;
    }

    public static boolean checkFileType(String fileType,String[] array) {
        //String[] array = { "JPEG", "PNG", "GIF","PDF","ZIP","RAR","XLS_DOC"};
        return Arrays.asList(array).contains(fileType);
    }

    public static boolean checkFjhzm(String fjhzm,String[] fjhzmArray) {
        return Arrays.asList(fjhzmArray).contains(fjhzm);
    }


}
