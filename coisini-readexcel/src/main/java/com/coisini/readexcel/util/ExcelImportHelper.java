package com.coisini.readexcel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Description Excel读取帮助类
 * @author Coisini
 * @date Nov 27, 2021
 * @version 1.0
 */
public class ExcelImportHelper {

    private ExcelImportHelper() {
    }

    public static List<List<List<String>>> importExcel(File file) throws Exception {
        if (file != null && file.getName()!= null && !file.getName().trim().equals("")) {
            Workbook workbook = null;
            InputStream inputStream = null;
            //InputStream inputStream1 = null;

            try {
                inputStream = new FileInputStream(file);
                //inputStream1 = new FileInputStream(file);

                String importFileName = file.getName();
                if(importFileName.endsWith(".xls") || importFileName.endsWith(".xlsx")) {
                    workbook = WorkbookFactory.create(inputStream);
                }else {
                    throw new RuntimeException("请传入Excel导入文件正确的后缀名");
                }

            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            } finally {
                if(inputStream!=null) {
                    try {
                        inputStream.close();
                    } catch(Exception e) {

                    }
                }
            }

            // Excel数据List
            List<List<List<String>>> excelSheetList = new ArrayList<List<List<String>>>();
            // 遍历所有工作簿

            if(workbook!=null) {
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    List<List<String>> excelRowList = new ArrayList<List<String>>();
                    excelSheetList.add(excelRowList);
                    Sheet sheet = workbook.getSheetAt(i);
                    if (sheet == null || sheet.getLastRowNum()<1) {
                        continue;
                    }
                    // 遍历所有行
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                        List<String> excelCellList = new ArrayList<String>();
                        Row row = sheet.getRow(j);
                        if (row == null || row.getCell(2)==null) {
                            continue;
                        }
                        excelRowList.add(excelCellList);
                        // 遍历所有列
                        for (int k = 0; k < row.getLastCellNum(); k++) {
                            Cell cell = row.getCell(k);
                            if (cell == null) {
                                excelCellList.add(null);
                                continue;
                            }
                            String value = getCellValue(cell, workbook);

                            excelCellList.add(value);
                        }
                    }
                }
                try {
                    workbook.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }

            } else {
                throw new RuntimeException("Excel解析失败，请检查Excel文件内容是否规范！");
            }
            return excelSheetList;
        } else {
            throw new RuntimeException("Excel中存在空数据");
        }
    }

    public static String getCellValue(Cell cell, Workbook workbook){
        String value = "";

        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
            value = replaceText(cell.getStringCellValue().trim());
        } else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)){
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                value = dateTimeFormat.format(cell.getDateCellValue());
            } else {
                BigDecimal tempValue = new BigDecimal(cell.getNumericCellValue());
                value = tempValue.toPlainString();

            }
        } else  if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)){
            value = String.valueOf(cell.getBooleanCellValue());
        } else  if (cell.getCellTypeEnum().equals(CellType.FORMULA)){
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = formulaEvaluator.evaluate(cell);

            if (cellValue.getCellTypeEnum().equals(CellType.STRING)) {
                value = replaceText(cellValue.getStringValue().trim());
            } else if (cellValue.getCellTypeEnum().equals(CellType.NUMERIC)){
                value = Double.toString(cellValue.getNumberValue());
                String[] vauleArray = value.split("\\.");
                if (vauleArray != null && vauleArray.length == 2) {
                    if (Long.valueOf(vauleArray[1]) == 0) {
                        value = vauleArray[0];
                    }
                }
            } else  if (cellValue.getCellTypeEnum().equals(CellType.BOOLEAN)){
                value = String.valueOf(cellValue.getBooleanValue());
            }
        }

        return value;

    }

    public static String replaceText(String text) {
        text = text.replaceAll("(\r\n|\r|\n|\n\r)", "");
        if (text.matches(".*'.*")) {
            text = text.replace('\'', ' ');
        }
        if (text.matches(".*<.*")) {
            text = text.replace('<', ' ');
        }

        if (text.matches(".*>.*")) {
            text = text.replace('>', ' ');
        }

        if (text.matches(".*\".*")) {
            text = text.replace('"', ' ');
        }

        if (text.matches(".*\\.*")) {
            text = text.replace('\\', ' ');
        }
        if (text.matches(".*?.*")) {
            text = text.replace('?', ' ');
        }
        return text;
    }


}

