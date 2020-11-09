package com.dcseat.report.util;

import com.dcseat.report.module.Alliance;
import com.dcseat.report.module.alliance.AllianceTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PoiTest {

    private static Logger log =  LoggerFactory.getLogger(PoiTest.class);

    public static void main(String[] args)  {

        Alliance templete = new AllianceTemplate("");
        templete.clear();


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("123");
        log.error("ccpL{}",1);
        XSSFRow rows = sheet.createRow(0);
        XSSFCell cell = rows.createCell(0);
        tt(cell);

        tt(cell);
        cell.setCellValue(125.2D);
        FileOutputStream  ops = null;
        try {
            ops = new FileOutputStream("../as.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(ops);
            ops.flush();
            ops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tt(Cell cell) {
        cell.setCellValue("aaaaaaa");
    }
}
