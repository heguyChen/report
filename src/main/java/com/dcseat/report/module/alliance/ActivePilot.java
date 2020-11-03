package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Users;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;


/**
 * 统计活跃成员数和得分情况
 */
public class ActivePilot extends AllianceTemplate implements Alliance {

    private static final int MIN_CORP_NUMBER = 10;

    private static final float MAX_SCORE = 5;

    private Users users = SpringContextUtil.getBean("users");

    private String ActivePilot_title = "活跃人头达标（5分）";

    private String activePilotNumber_title = "活跃人头";

    private String score_title = "分数";

    // 列数
    protected Integer col = 2;

    /**
     * 构造方法 指定联盟名称
     *
     * @param allianceName
     */
    public ActivePilot(String allianceName) {
        super(allianceName);
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) throws UnsupportedEncodingException {
        sheet.autoSizeColumn(col);
        Cell titleCell = sheet.getRow(row).createCell(col);
        titleCell.setCellValue(ActivePilot_title);
        sheet.setColumnWidth(col, titleCell.getStringCellValue().getBytes("GBK").length * 256);
        return col;
    }

    @Override
    public void printExcelValue(Sheet sheet, int row, int col) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void initData() {
        for (CorporationInfo corp : corps) {
            Integer number = users.getActiveUsersNumberByCorp(corp.getId(), StringUtils.getSqlDate());
            corp.setActivePilotNumber(number);
            //=IF(D13>10,5,5/10*D13)
            if (number > MIN_CORP_NUMBER) {
                corp.setActivePilotNumberScore(MAX_SCORE);
            } else {
//                MAX_SCORE / MIN_CORP_NUMBER * number;
//                corp.setActivePilotNumberScore();
            }
        }


    }
}
