package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;

/**
 * 统计活跃成员数和得分情况
 */
public class ActivePilot implements Alliance {

    private String ActivePilot_title = "活跃人头达标（5分）";
    // 活跃数
    private Integer activePilotNumber;

    private String activePilotNumber_title = "活跃人头";
    // 得分
    private Float score;

    private String score_title = "分数";


    @Override
    public void printExcelTitle(Sheet sheet, int row, int col) {

    }

    @Override
    public void printExcelValue(Sheet sheet, int row, int col) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void initData() {

    }
}
