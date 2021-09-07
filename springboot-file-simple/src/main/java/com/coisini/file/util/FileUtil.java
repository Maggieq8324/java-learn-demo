package com.coisini.file.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @Description 文件工具类
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
public class FileUtil {

    /**
     * 获取当前文件系统
     * @return
     */
    public static FileSystem getDefaultFileSystem() {
        return FileSystems.getDefault();
    }

    /**
     * 是否绝对路径
     * @param str
     * @return
     */
    public static boolean isAbsolute(String str) {
        Path path = getDefaultFileSystem().getPath(str);
        return path.isAbsolute();
    }

    /**
     * 初始化存储文件夹
     * @param dir
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void initStoreDir(String dir) {
        String absDir;
        if (isAbsolute(dir)) {
            absDir = dir;
        } else {
            String cmd = getCmd();
            Path path = getDefaultFileSystem().getPath(cmd, dir);
            absDir = path.toAbsolutePath().toString();
        }
        File file = new File(absDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取程序当前路径
     * @return
     */
    public static String getCmd() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取文件绝对路径
     * @param dir
     * @param filename
     * @return
     */
    public static String getFileAbsolutePath(String dir, String filename) {
        if (isAbsolute(dir)) {
            return getDefaultFileSystem()
                    .getPath(dir, filename)
                    .toAbsolutePath().toString();
        } else {
            return getDefaultFileSystem()
                    .getPath(getCmd(), dir, filename)
                    .toAbsolutePath().toString();
        }
    }

    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getFileExt(String filename) {
        int index = filename.lastIndexOf('.');
        return filename.substring(index);
    }

    /**
     * 获取文件MD5值
     * @param bytes
     * @return
     */
    public static String getFileMD5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 文件体积
     * @param size
     * @return
     */
    public static Long parseSize(String size) {
        DataSize singleLimitData = DataSize.parse(size);
        return singleLimitData.toBytes();
    }

    /**
     * 是否是绝对路径
     * @param path
     * @return
     */
    public static boolean isAbsolutePath(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        } else {
            return '/' == path.charAt(0) || path.matches("^[a-zA-Z]:[/\\\\].*");
        }
    }

    /**
     * 文件字节
     * @param file 文件
     * @return 字节
     */
    public static byte[] getFileBytes(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            throw new RuntimeException("read file date failed");
        }
        return bytes;
    }
}
