package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.util.PropertiesUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlliancesTest {

    @Autowired
    private Alliances alliances;

    private static Logger log = LoggerFactory.getLogger(AlliancesTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCorpInfosByAllianceName() {
        String name = PropertiesUtil.getProperty("dc.name");
        List<CorporationInfo> infos = alliances.getCorpInfosByAllianceName(name);
        for (CorporationInfo corp : infos) {
            log.info(corp.getName());
        }
        log.info("info:{}", infos);
    }
}