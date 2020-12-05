package com.dcseat.report.module.corporation;

import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.dao.seat.Paps;
import com.dcseat.report.module.Alliance;
import com.dcseat.report.util.CollectionUtils;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * pap详细清单模块
 */
public class PapModule extends CorporationTemplate implements Alliance {

    private static final Logger log = LoggerFactory.getLogger(PapModule.class);

    private final Paps paps = SpringContextUtil.getBean("paps");

    public PapModule(CorporationTemplate corporation) {
        super();
        this.corps = corporation.corps;
        this.chars = corporation.chars;
        this.initData();
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        Row row1 = sheet.getRow(row);
        row1.createCell(col++).setCellValue("pap数");
        return col;
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        for (int i = 0; i < chars.size(); i++) {
            CharacterInfo characterInfo = chars.get(i);
            if (characterInfo.getPaps() == null) {
                sheet.getRow(row+i).createCell(col).setCellValue(0);
            } else {
                sheet.getRow(row+i).createCell(col).setCellValue(characterInfo.getPaps());
            }
        }
        return col;
    }

    @Override
    public void initData() {
        // 统计每个角色pap
        List<CharacterInfo> paps = this.paps.getCharacterPaps(chars,
                StringUtils.getThisYear(),
                StringUtils.getThisMonth());
        // 拷贝参数
        CollectionUtils.copy(paps, chars, "paps");
        // 暂无进一步计算
    }
}
