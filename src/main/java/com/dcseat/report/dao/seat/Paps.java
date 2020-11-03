package com.dcseat.report.dao.seat;

import org.springframework.stereotype.Repository;

/**
 * pap相关的sql语句映射实体类
 */
@Repository
public interface Paps {

    /**
     * 获取公司级别的pap数据(该日期当月)
     * @param corpId    公司id
     * @param year      年份
     * @param month     月份
     * @return 该军团当月pap总量
     */
    Float getPapsByCorp(Integer corpId, String year, String month);

    /**
     * 获取成员级别的pap数据(该日期当月)
     * @param charId    公司id
     * @param date      日期,格式为yyyy-MM-dd
     * @return 该成员当月pap总量
     */
    Float getPapsByChar(Integer charId, String date);


}
