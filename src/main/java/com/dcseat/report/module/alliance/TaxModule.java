package com.dcseat.report.module.alliance;

import com.dcseat.report.module.Alliance;
import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.dao.seat.Corporations;
import com.dcseat.report.util.CollectionUtils;
import com.dcseat.report.util.MathUtils;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 税收模块综合实现类
 */
public class TaxModule extends AllianceTemplate implements Alliance {

    private static final Logger log = LoggerFactory.getLogger(PapStatisticsModule.class);

    /**
     * 税收最大得分
     */
    private static final Float MAX_TAX_SCORE = 5.0F;

    private static final Float ALLIANCE_TAX = 0.05F;

    private Corporations corporations = SpringContextUtil.getBean("corporations");

    private String taxModule_title = "税收贡献(5分)";

    private String tax_title = "总税收";

    private String allianceTax_title = "联盟税";

    private String taxRank_title = "税收排名";

    private String score_title = "分数";

    /**
     * 构造方法 指定联盟名称
     *
     * @param alliance 父类对象
     */
    public TaxModule(AllianceTemplate alliance) {
        super();
        this.corps = alliance.corps;
        this.initData();
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        try {
            // 设置一级标题
            setCellStyle(sheet.getRow(row).createCell(col)).setCellValue(taxModule_title);
            // 合并一级标题
            CellRangeAddress region = new CellRangeAddress(row, row, col, col + 2);
            sheet.addMergedRegion(region);
            // 设置二级标题
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(tax_title);
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(allianceTax_title);
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(taxRank_title);
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(score_title);
        } catch (Exception e) {
            log.error("设置单元格title出错,{}", e.getMessage());
        }
        return col;
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        int temp_col = col;
        for (CorporationInfo corp : corps) {
            col = temp_col;
            Row row_ = sheet.getRow(row++);
            row_.createCell(col++).setCellValue(corp.getCorpTax());
            row_.createCell(col++).setCellValue(corp.getAllianceTax());
            row_.createCell(col++).setCellValue(corp.getTaxRank());
            row_.createCell(col++).setCellValue(corp.getTaxScore());
        }
        return col;
    }

    @Override
    public void initData() {
        List<CorporationInfo> src = corporations.getCorpBounty(corps, StringUtils.getSqlDate());
        // 拷贝参数
        CollectionUtils.copy(src, corps, "corpTax");
        // 根据税收排名
        CollectionUtils.sort(corps, false, "corpTax");
        for (int i = 0; i < corps.size(); i++) {
            CorporationInfo corp = corps.get(i);
            corp.setTaxRank(i+1);
            //=(15+1-L13)/15*5
            Float taxScore = MathUtils.division(String.valueOf(MAX_TAX_SCORE * (corps.size() - i)),
                    String.valueOf(corps.size()));
            corp.setTaxScore(taxScore);
            corp.sumScore();
        }
        // 根据总分排名
        CollectionUtils.sort(corps, false, "score");
        for (CorporationInfo corp : corps) {
            float corpTax = corp.getCorpTax();
            if (corp.getTaxRate() == 0) continue;
            if (corps.indexOf(corp) == 0) {
                // 排名第一 减税1%
                Float allianceTax = MathUtils.division(String.valueOf(corpTax * (ALLIANCE_TAX - 0.01)),
                        String.valueOf(corp.getTaxRate()));
                corp.setAllianceTax(allianceTax);
            } else if (corps.indexOf(corp) == 1) {
                // 排名第二 减税0.5%
                Float allianceTax = MathUtils.division(String.valueOf(corpTax * (ALLIANCE_TAX - 0.005)),
                        String.valueOf(corp.getTaxRate()));
                corp.setAllianceTax(allianceTax);
            } else {
                Float allianceTax =  MathUtils.division(String.valueOf(corpTax * (ALLIANCE_TAX)),
                        String.valueOf(corp.getTaxRate()));
                corp.setAllianceTax(allianceTax);
            }
        }
    }
}
