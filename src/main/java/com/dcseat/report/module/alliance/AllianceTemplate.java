package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Alliances;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class AllianceTemplate implements Alliance {

    private static Logger log = LoggerFactory.getLogger(AllianceTemplate.class);

    private Alliances alliances = SpringContextUtil.getBean("alliances");

    // 标题
    private String corpTitle = "公司名称";

    // 列数
    private Integer col = 1;

    // 月报模块
    private ArrayList<Alliance> module = new ArrayList<>();

    //联盟名称
    protected String allianceName;

    // 公司信息
    protected List<CorporationInfo> corps = new ArrayList<>();

//    private AllianceTemplate() {};
    /**
     * 构造方法 指定联盟名称
     * @param allianceName
     */
    public AllianceTemplate(String allianceName) {
        this.allianceName = allianceName;
    }

    public AllianceTemplate() {

    }

    public void add(Alliance a) {
        module.add(a);
    }

    public void remove(Alliance a) {
        module.remove(a);
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 完成大标题输入 在循环结束后 把大标题合并居中
        Row row0 = sheet.createRow(row++);
        row0.setRowStyle(cellStyle);
        Cell topTitle = row0.createCell(col);
        topTitle.setCellValue(allianceName+" "+ StringUtils.getTitleDate()+" 月报");
        // 输入公司列
        Row titleRow = sheet.createRow(row);
        titleRow.setRowStyle(cellStyle);
        Cell allianceTemplateCell = titleRow.createCell(col);
        allianceTemplateCell.setCellValue(corpTitle);
        CellRangeAddress region=new CellRangeAddress(row, row+1, col, col);
        sheet.addMergedRegion(region);
        sheet.createRow(row+1).setRowStyle(cellStyle);
        for (Alliance m : module) {
            col = m.printExcelTitle(sheet, row, ++col);
        }

        return col;

    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        row+=3;// topTitle、title1、title2
        for (int i = 0; i < corps.size(); i++) {
            CorporationInfo corp = corps.get(i);
            Row row_ = sheet.createRow(i + row);
            Cell corpCell = row_.createCell(col);
            corpCell.setCellValue(corp.getName());
        }

        for (Alliance m : module) {
            col = m.printExcelValue(sheet, row, ++col);
        }
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void initData() {
        // 处理 corps成员变量
        try {
            corps = this.alliances.getCorpInfosByAllianceName(allianceName);
        } catch (Exception e) {
            log.error("获取联盟公司信息失败,异常信息:{}", e.getMessage());
        }
    }

}
