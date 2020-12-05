package com.dcseat.report;

import com.dcseat.report.module.ModuleManager;
import com.dcseat.report.module.alliance.*;
import com.dcseat.report.util.PropertiesUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SpringBoot 启动器
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dcseat.report"})
//@ComponentScan(basePackages = {"com.dcseat.report"})
public class ReportApplication implements CommandLineRunner {

    /**
     * SpringBoot 主程序入口
     */
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 接入自定义的main接口
        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet1 = workbook.createSheet("联盟月报");
//        ModuleManager.sheet1(sheet1);
        XSSFSheet sheet2 = workbook.createSheet("成员月报");
        ModuleManager.sheet2(sheet2);

        FileOutputStream  ops = null;
        try {
            ops = new FileOutputStream("../联盟月报.xlsx");
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
