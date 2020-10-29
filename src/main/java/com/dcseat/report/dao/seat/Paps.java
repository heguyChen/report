package com.dcseat.report.dao.seat;

public interface Paps {

    /**
     * 获取公司级别的pap数据
     * @param corpId    公司id
     * @param year      年
     * @param month     月
     * @return 该军团当月pap总量
     */
    Float getPapsByCorp(Integer corpId, Integer year, Integer month);

    /**
     * 获取成员级别的pap数据
     * @param charId    公司id
     * @param year      年
     * @param month     月
     * @return 该成员当月pap总量
     */
    Float getPapsByChar(Integer charId, Integer year, Integer month);
}
