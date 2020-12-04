package com.dcseat.report.module.corporation;

import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Alliances;
import com.dcseat.report.dao.seat.Users;
import com.dcseat.report.module.Alliance;
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

/**
 * 公司模板
 */
public class CorporationTemplate implements Alliance {

    private static final Logger log = LoggerFactory.getLogger(CorporationTemplate.class);

    // 联盟接口
    private final Alliances alliances = SpringContextUtil.getBean("alliances");

    // 用户接口
    private final Users users = SpringContextUtil.getBean("users");

    // 月报模块
    private final ArrayList<Alliance> module = new ArrayList<>();

    //联盟名称
    protected String allianceName;

    // 公司信息
    protected List<CorporationInfo> corps = new ArrayList<>();

    // 人员信息
    protected List<CharacterInfo> chars = new ArrayList<>();

    public CorporationTemplate(String allianceName) {
        this.allianceName = allianceName;
    }

    public CorporationTemplate() {

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
        topTitle.setCellValue(allianceName+" "+ StringUtils.getTitleDate()+" 成员细则");
        // 首列标题
        sheet.createRow(row++).createCell(col).setCellValue("角色名");
        for (Alliance m : module) {
            col = m.printExcelTitle(sheet, row, col);
        }
        return col;
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        row+=1;
        // 输入成员名称列
        for (int i = 0; i < chars.size(); i++) {
            CharacterInfo characterInfo = chars.get(i);
            Row row_ = sheet.createRow(i + row);
            Cell charCell = row_.createCell(col);
            charCell.setCellValue(characterInfo.getCharacterName());
        }
        col++;// 首列被占用
        // 遍历模块
        for (Alliance m : module) {
            col = m.printExcelValue(sheet, row, col);
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
        // 处理 chars成员变量
        try {
            chars = this.users.getUsers(corps, StringUtils.getSqlDate());
        } catch (Exception e) {
            log.error("获取公司成员信息失败,异常信息:{}", e.getMessage());
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
