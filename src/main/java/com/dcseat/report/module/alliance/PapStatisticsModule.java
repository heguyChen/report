package com.dcseat.report.module.alliance;

import com.dcseat.report.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Paps;
import com.dcseat.report.util.MathUtils;
import com.dcseat.report.util.PropertiesUtil;
import com.dcseat.report.util.SpringContextUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pap指标综合实现类
 * 包括基本pap、人均pap奖励、总额pap奖励
 */
public class PapStatisticsModule extends AllianceTemplate implements Alliance {

    private static Logger log = LoggerFactory.getLogger(PapStatisticsModule.class);

    /**
     * 最小人均pap
     */
    private static final Float MIN_PER_PAP = 3.0F;

    /**
     * pap满分
     */
    private static final Float MAX_PAP_SCORE = 55F;

    /**
     * 人均pap奖励满分
     */
    private static final Float MAX_PER_PAP_SCORE = 7.5F;

    /**
     * 总量pap奖励满分
     */
    private static final Float MAX_COUNT_PAP_SCORE = 7.5F;

    private Paps paps = SpringContextUtil.getBean("paps");

    private String papStatistics_title = "PAP达标(55分)";

    private String papCount_title = "PAP数";

    private String perPilotPap_title = "人均PAP数";

    private String score_title = "分数";

    private String perPilotPapReward_title = "人均PAP超额奖励(7.5分)";

    private String papCountReward_title = "PAP总额奖励(7.5分)";

    public PapStatisticsModule(AllianceTemplate alliance) {
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
        for (CorporationInfo corp : corps) {
            Row row_ = sheet.getRow(row++);
            Cell numberCell = row_.createCell(col);
            Cell scoreCell = row_.createCell(col + 1);
            numberCell.setCellValue(corp.getPapCount());
            scoreCell.setCellValue(corp.getPerPilotPap());
        }

        return 1;
    }

    @Override
    public void initData() {
        // 获取评判总量pap的基准
        Float basePap = paps.getCorpPapsByRank(PropertiesUtil.getProperty("dc.name"),
                PropertiesUtil.getProperty("dc.year"),
                PropertiesUtil.getProperty("dc.month"),
                Integer.valueOf(PropertiesUtil.getProperty("dc.rank")));

        // 获取各公司pap基础数据
        for (CorporationInfo corp : corps) {
            // 军团无人口跳过计算
            if (corp.getActivePilotNumber() == 0) continue;

            Float papCount = paps.getPapsByCorp(corp.getId(),
                    PropertiesUtil.getProperty("dc.year"),
                    PropertiesUtil.getProperty("dc.month"));
            corp.setPapCount(papCount);
            // 计算人均pap
            Float perPilotPap = MathUtils.division(String.valueOf(papCount), String.valueOf(corp.getActivePilotNumber()));
            corp.setPerPilotPap(perPilotPap);
            // 计算分数 =IF(G14>3,55,45*G14/3)
            if (perPilotPap > MIN_PER_PAP) {
                corp.setPerPilotPapScore(MAX_PAP_SCORE);
            } else if (perPilotPap >= 0) {
                Float perPilotPapScore = MathUtils.division(String.valueOf((MAX_PAP_SCORE - 10) * perPilotPap), String.valueOf(MIN_PER_PAP));
                corp.setPerPilotPapScore(perPilotPapScore);
            }
            // 计算人均pap奖励分数 =IF(D13>=10,MIN(IF(G13>3,7.5*(G13-3)/3,0),7.5),0)
            if (corp.getActivePilotNumber() <10) {
                corp.setPerPilotPapRewardScore(0F);
            } else if (perPilotPap <= MIN_PER_PAP) {
                corp.setPerPilotPapRewardScore(0F);
            } else {
                Float perPilotPapRewardScore = MathUtils.division(String.valueOf(MAX_PER_PAP_SCORE * (perPilotPap - MIN_PER_PAP)),
                        String.valueOf(MIN_PER_PAP));
                corp.setPerPilotPapRewardScore(
                        perPilotPapRewardScore < MAX_PER_PAP_SCORE ? perPilotPapRewardScore : MAX_PER_PAP_SCORE);
            }
            // 计算综合pap奖励分数 =MIN(7.5*(F15/187),7.5)
            Float papCountRewardScore = MathUtils.division(String.valueOf(MAX_COUNT_PAP_SCORE * papCount), String.valueOf(basePap));
            corp.setPapCountRewardScore(
                    papCountRewardScore < MAX_COUNT_PAP_SCORE ? papCountRewardScore : MAX_COUNT_PAP_SCORE);
        }
    }
}
