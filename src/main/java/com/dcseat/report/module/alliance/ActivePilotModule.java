package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Users;
import com.dcseat.report.util.CollectionUtils;
import com.dcseat.report.util.MathUtils;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 统计活跃成员数和得分情况
 */
public class ActivePilotModule extends AllianceTemplate implements Alliance {

    private static final int MIN_CORP_NUMBER = 10;

    private static final float MAX_SCORE = 5;

    // 列数
    protected  static final Integer col = 2;

    private static Logger log = LoggerFactory.getLogger(ActivePilotModule.class);

    private Users users = SpringContextUtil.getBean("users");

    private String ActivePilot_title = "活跃人头达标(5分)";

    private String activePilotNumber_title = "活跃人头";

    private String score_title = "分数";



    /**
     * 构造方法 指定联盟名称
     *
     * @param alliance 父类对象
     */
    public ActivePilotModule(AllianceTemplate alliance) {
        super();
        this.corps = alliance.corps;
        this.initData();
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        // 设置一级标题
        setCellStyle(sheet.getRow(row).createCell(col)).setCellValue(ActivePilot_title);
//        try {
//            sheet.setColumnWidth(col, titleCell.getStringCellValue().getBytes("GBK").length * 256);
//        } catch (UnsupportedEncodingException e) {
//            log.error("编解码不匹配");
//        }
        // 合并一级标题
        CellRangeAddress region=new CellRangeAddress(row, row, col, col+1);
        sheet.addMergedRegion(region);
        // 设置二级标题
        setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(activePilotNumber_title);
        setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(score_title);
        return col;
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        int temp_col = col;
        for (CorporationInfo corp : corps) {
            col = temp_col;
            Row row_ = sheet.getRow(row++);
            Cell numberCell = row_.createCell(col++);
            Cell scoreCell = row_.createCell(col++);
            numberCell.setCellValue(corp.getActivePilotNumber());
            scoreCell.setCellValue(corp.getActivePilotNumberScore());
        }
        return col;
    }

    @Override
    public void clear() {

    }

    @Override
    public void initData() {
        // 获取父类公司集合信息进行遍历
        List src = users.getActiveUsersNumberByCorp(corps, StringUtils.getSqlDate());
        // 拷贝参数
        CollectionUtils.copy(src, corps, "activePilotNumber");

        for (CorporationInfo corp : corps) {
            // 读取最近时间公司活跃用户数
//            Integer number = users.getActiveUsersNumberByCorp(corp.getId(), StringUtils.getSqlDate());

            /*
            临时代码 合并老数据
             */
            addNumber(corp);
            Integer number = corp.getActivePilotNumber();

            // 计算本模块得分 人口大于10即五分，否则按照人口比例乘以五分  IF(D13>10,5,5/10*D13)
            if (number > MIN_CORP_NUMBER) {
                corp.setActivePilotNumberScore(MAX_SCORE);
            } else {
                try {
                    Float score = MathUtils.division(String.valueOf(MAX_SCORE * number), String.valueOf(MIN_CORP_NUMBER));
                    corp.setActivePilotNumberScore(score);
                } catch (NumberFormatException e) {
                    log.error("{}模块,公司{}人数错误.", ActivePilot_title, corp.getName());
                }
            }
        }
    }

    static void addNumber(CorporationInfo corp) {
        switch (corp.getName()) {
            case "Vision CN":
                corp.setActivePilotNumber(124);
                break;
            case "B.O.P Supplication For Glorious":
                corp.setActivePilotNumber(67);
                break;
            case "Faceless.":
                corp.setActivePilotNumber(57);
                break;
            case "Perseus War Alliance":
                corp.setActivePilotNumber(29);
                break;
            case "Lang Jiang Hu Elite":
                corp.setActivePilotNumber(18);
                break;
            case "Shadow of Dragon":
                corp.setActivePilotNumber(12);
                break;
            case "Dark Portal":
            case "Southern Economic Cooperation Organization":
                corp.setActivePilotNumber(4);
                break;
            case "Supremacy of Strength's Classroom":
            case "To Another Galaxy":
                corp.setActivePilotNumber(3);
                break;
            case "Gaizack Process Development Corp":
            case "Steel Feather Special Air Corps":
                corp.setActivePilotNumber(1);
                break;
        }
    }
}
