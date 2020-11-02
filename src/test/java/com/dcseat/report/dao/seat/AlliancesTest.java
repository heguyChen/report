package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CorporationInfo;
import com.dcseat.report.util.PoiTest;
import com.dcseat.report.util.PropertiesUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AlliancesTest {

    private ApplicationContext app;

    private Alliances alliances;

    private static Logger log =  LoggerFactory.getLogger(AlliancesTest.class);

    @Before
    public void setUp() throws Exception {
        app = new ClassPathXmlApplicationContext("applicationContext.xml");
        alliances = (Alliances) app.getBean("alliances");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCorpInfosByAllianceName() {
        String name = PropertiesUtil.getProperty("dc.name");
        List<CorporationInfo> infos = alliances.getCorpInfosByAllianceName(name);
        log.info("info:{}", infos);
    }
}