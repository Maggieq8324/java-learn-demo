package com.coisini.readexcel.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description Excel读取工具类 合并单元格读取
 * @author Coisini
 * @date Nov 14, 2021
 * @version 1.0
 */
public class ExcelImportUtil {

    public static void main(String[] args) throws Exception {
        importExcel(new File("C:\\Users\\epsoft-hy\\Downloads\\test.xlsx"));
    }

    public static List<List<String>> importExcel(File file) throws Exception {
        if (Objects.nonNull(file) && !file.getName().trim().equals("")) {
            Workbook workbook = null;
            InputStream inputStream = null;

            try {
                inputStream = new FileInputStream(file);
                String importFileName = file.getName();
                if(importFileName.endsWith(".xls") || importFileName.endsWith(".xlsx")) {
                    workbook = WorkbookFactory.create(inputStream);
                }else {
                    throw new RuntimeException("Excel导入文件后缀名应为.xls或.xlsx");
                }
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            } finally {
                if(Objects.nonNull(inputStream)) {
                    try {
                        inputStream.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Excel数据List
            List<List<String>> excelSheetList = new ArrayList<>();
            // 只读取第一个Sheet
            if(Objects.nonNull(workbook) && workbook.getNumberOfSheets() > 0) {
                Sheet sheet = workbook.getSheetAt(0);

                if (Objects.nonNull(sheet) && sheet.getLastRowNum() > 1) {
                    // 遍历所有行
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                        List<String> excelRowList = new ArrayList<>();
                        excelSheetList.add(excelRowList);
                        Row row = sheet.getRow(j);

                        if (Objects.isNull(row) || Objects.isNull(row.getCell(2))) {
                            continue;
                        }

                        // 遍历所有列
                        for (int k = 0; k < row.getLastCellNum(); k++) {
                            if(isMergedRegion(sheet, j, k)){
                                String value = getMergedRegionValue(sheet, j, k, workbook);
                                excelRowList.add(value);
                            }else{
                                String value = getCellValue(sheet.getRow(j).getCell(k), workbook);
                                excelRowList.add(value);
                            }
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

            System.out.println(JSON.toJSONString(excelSheetList));
            return excelSheetList;
        } else {
            throw new RuntimeException("Excel中存在空数据");
        }
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet ,int row , int column,Workbook workbook){
        for(int i = 0; i < sheet.getNumMergedRegions(); i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            if(row >= ca.getFirstRow() && row <= ca.getLastRow()){
                if(column >= ca.getFirstColumn() && column <= ca.getLastColumn()){
                    return getCellValue(sheet.getRow(ca.getFirstRow()).getCell(ca.getFirstColumn()), workbook);
                }
            }
        }

        return null ;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if(row >= range.getFirstRow() && row <= range.getLastRow()){
                if(column >= range.getFirstColumn() && column <= range.getLastColumn()){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取Cell值
     * @param cell
     * @param workbook
     * @return
     */
    public static String getCellValue(Cell cell, Workbook workbook){
        String value = "";

        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
            value = replaceText(cell.getStringCellValue().trim());
        } else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)){
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                value = dateTimeFormat.format(cell.getDateCellValue());
            } else {
                BigDecimal tempValue = BigDecimal.valueOf(cell.getNumericCellValue());
                value = tempValue.toPlainString();

            }
        } else if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)){
            value = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum().equals(CellType.FORMULA)){
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = formulaEvaluator.evaluate(cell);

            if (cellValue.getCellTypeEnum().equals(CellType.STRING)) {
                value = replaceText(cellValue.getStringValue().trim());
            } else if (cellValue.getCellTypeEnum().equals(CellType.NUMERIC)){
                value = Double.toString(cellValue.getNumberValue());
                String[] vauleArray = value.split("\\.");
                if (vauleArray.length == 2) {
                    if (Long.parseLong(vauleArray[1]) == 0) {
                        value = vauleArray[0];
                    }
                }
            } else  if (cellValue.getCellTypeEnum().equals(CellType.BOOLEAN)){
                value = String.valueOf(cellValue.getBooleanValue());
            }
        }

        return value;
    }

    /**
     * 特殊字符替换
     * @param text
     * @return
     */
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
