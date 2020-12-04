package com.dcseat.report.module.alliance;

import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.module.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Alliances;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
public class AllianceTemplate implements Alliance {

    private static final Logger log = LoggerFactory.getLogger(AllianceTemplate.class);

    private final Alliances alliances = SpringContextUtil.getBean("alliances");

    // 标题
    private final String corpTitle = "公司名称";

    // 月报模块
    private final ArrayList<Alliance> module = new ArrayList<>();

    //联盟名称
    protected String allianceName;

    // 公司信息
    protected List<CorporationInfo> corps = new ArrayList<>();

    /**
     * 构造方法 指定联盟名称
     * @param allianceName  联盟名称参数
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
        // 完成大标题输入 在循环结束后 把大标题合并居中
        Row row0 = sheet.createRow(row++);
        Cell topTitle = row0.createCell(col);
        topTitle.setCellValue(allianceName+" "+ StringUtils.getTitleDate()+" 月报");
        // 设置一级标题+合并
        sheet.createRow(row).createCell(col).setCellValue(corpTitle);
        CellRangeAddress region=new CellRangeAddress(row, row+1, col, col++);
        sheet.addMergedRegion(region);

        sheet.createRow(row+1);
        for (Alliance m : module) {
            col = m.printExcelTitle(sheet, row, col);
        }
        setCellStyle(sheet.getRow(row).createCell(col)).setCellValue("总分");
        CellRangeAddress region2 = new CellRangeAddress(row, row+1, col, col++);
        sheet.addMergedRegion(region2);
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
        col++;
        for (Alliance m : module) {
            col = m.printExcelValue(sheet, row, col);
        }
        for (CorporationInfo corp :corps) {
            Row row_ = sheet.getRow(row++);
            row_.createCell(col).setCellValue(corp.getScore());
        }
        return col;
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

    /**
     *  设置单元格样式
     * @param cell      单元格
     * @return 返回该单元格
     */
    protected Cell setCellStyle(Cell cell) {
        CellStyle cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cell.setCellStyle(cellStyle);
        return cell;
    }
}
