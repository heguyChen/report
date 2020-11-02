package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Alliances;
import com.dcseat.report.util.PropertiesUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AllianceTemplate implements Alliance {

    @Autowired
    private Alliances alliances;

    private String allianceName;

    private ArrayList<Alliance> module = new ArrayList<>();

    protected List<CorporationInfo> corps;

    /**
     * 构造方法 指定联盟名称
     * @param allianceName
     */
    public AllianceTemplate(String allianceName) {
        this.allianceName = allianceName;
    }

    public void add(Alliance a) {
        module.add(a);
    }

    public void remove(Alliance a) {
        module.remove(a);
    }


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
        // 处理 corps成员变量
        corps = alliances.getCorpInfosByAllianceName(allianceName);
        //
    }

}
