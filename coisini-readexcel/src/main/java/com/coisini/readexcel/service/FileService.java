package com.coisini.readexcel.service;

import com.alibaba.fastjson.JSON;
import com.coisini.readexcel.util.ExcelImportHelper;
import com.coisini.readexcel.util.FileHelper;
import com.coisini.readexcel.util.FileType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

        // Excel导入
        public void importExcel(MultipartFile file) {

            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String curDateTime = formatter.format(now);

            String year = curDateTime.substring(0, 4);
            String mouth = curDateTime.substring(5, 7);
            String day = curDateTime.substring(8, 10);
            String stringFilepath = "";


            if (file != null) {
//                for (MultipartFile oneFile : files) {
                    if (file != null && file.getOriginalFilename() != null
                            && !file.getOriginalFilename().trim().equals("")) {
                        String forldpath = "\\yy\\" + year + "\\" + mouth + "\\" + day + "\\";

                        File forld = new File("E:\\" + forldpath);
                        if (!forld.exists()) {
                            forld.mkdirs();
                        }
                        String fileName = file.getOriginalFilename();
                        String fjhzm = fileName.substring(fileName.lastIndexOf("."), fileName.length());


                        // String fjmc = fileName.substring(0, fileName.lastIndexOf("."));
//                        String uuid = UUID.randomUUID().toString();
                        String filepath = forldpath + fjhzm;
                        File localFile = new File("E:\\" + filepath);
                        String stringFile = "E:\\" + filepath;

                        stringFilepath = stringFile.replace("\\", "\\\\");

                        InputStream isFile = null;

                        try {
                            isFile = file.getInputStream();
                            FileType fileType = FileHelper.getFileType(isFile);

                            if (fileType != null) {
                                String fType = fileType.toString();
                                String[] array = { "XLS_DOC", "ZIP" };
                                if (FileHelper.checkFileType(fType, array)) {
                                    file.transferTo(localFile);
                                } else {
                                    throw new RuntimeException("允许上传的文件类型：XLS");
                                }

                            } else {
                                throw new RuntimeException("允许上传的文件类型：XLS");
                            }
                        } catch (IOException e) {
                            // return "文件上传失败：保存文件时出现错误:" + e.getMessage();
                            e.printStackTrace();
                            throw new RuntimeException("文件上传失败：保存文件时出现错误:" + e.getMessage());
                        } finally {
                            if (isFile != null) {
                                try {
                                    isFile.close();
                                } catch (Exception e2) {
                                    // TODO: handle exception
                                }
                            }
                        }


                        // GzjhFjxxService obj = new GzjhFjxxService();
                        File filedr = new File(stringFilepath);
                        List<List<List<String>>> excelSheetList;
                        try {
                            excelSheetList = ExcelImportHelper.importExcel(filedr);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < excelSheetList.size(); i++) {
                            if (i == 0) {
                                List<List<String>> excelRowList = excelSheetList.get(i);
                                System.out.println(JSON.toJSONString(excelRowList));
                                for (int j = 1; j < excelRowList.size(); j++) {
                                    List list = (List) excelRowList.get(j);
                                }
                            } else {
                                System.out.println("Excel中请保留唯一的标签页");
                            }
                        }

                    }
                }
//            }


    }


}
