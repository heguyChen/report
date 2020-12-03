package com.dcseat.report.module.corporation;

import com.dcseat.report.dao.seat.Paps;
import com.dcseat.report.module.Alliance;
import com.dcseat.report.module.alliance.AllianceTemplate;
import com.dcseat.report.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterPapModule extends CorporationTemplate implements Alliance {

    private static final Logger log = LoggerFactory.getLogger(CharacterPapModule.class);

    private final Paps paps = SpringContextUtil.getBean("paps");

    public CharacterPapModule(CorporationTemplate corporation) {
        super();
        this.corps = corporation.corps;
        this.initData();
    }
}
