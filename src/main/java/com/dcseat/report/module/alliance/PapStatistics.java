package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Paps;
import com.dcseat.report.util.PropertiesUtil;
import com.dcseat.report.util.SpringContextUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * pap指标综合实现类
 * 包括基本pap、人均pap奖励、总额pap奖励
 */
public class PapStatistics extends AllianceTemplate implements Alliance {

    private static Logger log = LoggerFactory.getLogger(PapStatistics.class);

    private Paps paps = SpringContextUtil.getBean("paps");

    private String papStatistics_title = "PAP达标(55分)";

    private String papCount_title = "PAP数";

    private String perPilotPap_title = "人均PAP数";

    private String score_title = "分数";

    private String perPilotPapReward_title = "人均PAP超额奖励(7.5分)";

    private String papCountReward_title = "人均PAP超额奖励(7.5分)";

    public PapStatistics(AllianceTemplate alliance) {
        super();
        this.corps = alliance.corps;
        this.initData();
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        return super.printExcelTitle(sheet, row, col);
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        return super.printExcelValue(sheet, row, col);
    }

    @Override
    public void initData() {
        // 获取各公司pap基础数据
        for (CorporationInfo corp : corps) {
            Float papCount = paps.getPapsByCorp(corp.getId(), PropertiesUtil.getProperty("dc.year"), PropertiesUtil.getProperty("dc.month"));
            corp.setPapCount(papCount);
        }
    }
}
