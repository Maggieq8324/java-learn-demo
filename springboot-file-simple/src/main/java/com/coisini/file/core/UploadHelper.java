package com.coisini.file.core;

import com.coisini.file.util.FileUtil;
import java.util.UUID;

/**
 * @Description 文件上传Helper
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
public class UploadHelper {

    /**
     * 单个文件检查
     * @param singleFileLimit 单个文件大小限制
     * @param originName      文件原始名称
     * @param length          文件大小
     * @return 文件的扩展名，例如： .jpg
     */
    public static String checkOneFile(String[] include, String[] exclude, long singleFileLimit, String originName, int length) {
        // 写到了本地
        String ext = FileUtil.getFileExt(originName);
        // 检测扩展
        if (!UploadHelper.checkExt(include, exclude, ext)) {
            throw new RuntimeException(ext + "文件类型不支持");
        }
        // 检测单个大小
        if (length > singleFileLimit) {
            throw new RuntimeException(originName + "文件不能超过" + singleFileLimit);
        }
        return ext;
    }

    /**
     * 检查文件后缀
     * @param ext 后缀名
     * @return 是否通过
     */
    public static boolean checkExt(String[] include, String[] exclude, String ext) {
        int inLen = include == null ? 0 : include.length;
        int exLen = exclude == null ? 0 : exclude.length;
        // 如果两者都有取 include，有一者则用一者
        if (inLen > 0 && exLen > 0) {
            return UploadHelper.findInInclude(include, ext);
        } else if (inLen > 0) {
            // 有include，无exclude
            return UploadHelper.findInInclude(include, ext);
        } else if (exLen > 0) {
            // 有exclude，无include
            return UploadHelper.findInExclude(exclude, ext);
        } else {
            // 二者都没有
            return true;
        }
    }

    /**
     * 检查允许的文件类型
     * @param include
     * @param ext
     * @return
     */
    public static boolean findInInclude(String[] include, String ext) {
        for (String s : include) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查不允许的文件类型
     * @param exclude
     * @param ext
     * @return
     */
    public static boolean findInExclude(String[] exclude, String ext) {
        for (String s : exclude) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得新文件的名称
     * @param ext 文件后缀
     * @return 新名称
     */
    public static String getNewFilename(String ext) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + ext;
    }

}
