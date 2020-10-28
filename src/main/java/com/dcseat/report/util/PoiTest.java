package com.dcseat.report.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PoiTest {

    private static Logger log =  LoggerFactory.getLogger(PoiTest.class);

    public static void main(String[] args)  {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("123");
        log.error("ccpL{}",1);

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
}
