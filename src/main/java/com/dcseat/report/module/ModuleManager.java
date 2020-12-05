package com.dcseat.report.module;

import com.dcseat.report.module.alliance.ActivePilotModule;
import com.dcseat.report.module.alliance.AllianceTemplate;
import com.dcseat.report.module.alliance.PapStatisticsModule;
import com.dcseat.report.module.alliance.TaxModule;
import com.dcseat.report.module.corporation.CorporationTemplate;
import com.dcseat.report.module.corporation.PapModule;
import com.dcseat.report.module.corporation.SeatModule;
import com.dcseat.report.util.PropertiesUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ModuleManager {


    public static void sheet1(Sheet sheet) {
        String name = PropertiesUtil.getProperty("dc.name");
        AllianceTemplate allianceTemplate = new AllianceTemplate(name);
        allianceTemplate.initData();

        allianceTemplate.add(new ActivePilotModule(allianceTemplate));
        allianceTemplate.add(new PapStatisticsModule(allianceTemplate));
        allianceTemplate.add(new TaxModule(allianceTemplate));
        // ...继续添加模块


        allianceTemplate.printExcelTitle(sheet, 0, 0);
        allianceTemplate.printExcelValue(sheet, 0, 0);
    }

    public static void sheet2(XSSFSheet sheet) {
        String name = PropertiesUtil.getProperty("dc.name");
        CorporationTemplate corporationTemplate = new CorporationTemplate(name);
        corporationTemplate.initData();

        corporationTemplate.add(new PapModule(corporationTemplate));
        // ...继续添加模块


        corporationTemplate.printExcelTitle(sheet, 0, 0);
        corporationTemplate.printExcelValue(sheet, 0, 0);
    }
}
