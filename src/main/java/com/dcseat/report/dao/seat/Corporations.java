package com.dcseat.report.dao.seat;

public interface Corporations {

    /**
     * 获取公司的月度赏金税收
     * @param corpId    公司id
     * @param year      年
     * @param month     月
     * @return 返回指定月份下公司的赏金税收
     */
    Float getCorpBounty(Integer corpId, Integer year, Integer month);

    /**
     * 获取公司每个账户的收支求和
     */
}
