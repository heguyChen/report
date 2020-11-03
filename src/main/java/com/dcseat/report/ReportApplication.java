package com.dcseat.report;

import com.dcseat.report.dao.seat.Users;
import com.dcseat.report.module.alliance.ActivePilot;
import com.dcseat.report.module.alliance.AllianceTemplate;
import com.dcseat.report.util.PropertiesUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 接入自定义的main接口
        String name = PropertiesUtil.getProperty("dc.name");
        AllianceTemplate allianceTemplate = new AllianceTemplate(name);
        allianceTemplate.add(new ActivePilot(name));
        // ...继续添加模块
        allianceTemplate.initData();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        allianceTemplate.printExcelTitle(sheet, 0, 0);
        allianceTemplate.printExcelValue(sheet, 0, 0);

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
