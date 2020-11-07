package com.dcseat.report.dao.seat;

import com.dcseat.report.util.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PapsTest {

    private static Logger log = LoggerFactory.getLogger(PapsTest.class);

    @Autowired
    private Paps paps;


    @Test
    public void getPapsByCorp() {
//        Float papsByCorp = paps.getPapsByCorp(98547771,
//                PropertiesUtil.getProperty("dc.year"),
//                PropertiesUtil.getProperty("dc.month"));
//        System.out.println(papsByCorp);
    }
}