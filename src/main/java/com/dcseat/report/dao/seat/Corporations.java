package com.dcseat.report.dao.seat;

import org.springframework.stereotype.Repository;

/**
 * 公司相关的sql语句映射实体类
 */
@Repository
public interface Corporations {

    /**
     * 获取公司的月度赏金税收(该日期当月)
     * @param corpId    公司id
     * @param date      日期,格式为yyyy-MM-dd
     * @return 返回指定日期下公司的赏金税收
     */
    Float getCorpBounty(Integer corpId, String date);

    /**
     * 获取公司每个账户的收支求和
     */
}
