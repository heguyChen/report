package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CorporationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司相关的sql语句映射实体类
 */
@Repository
public interface Corporations {

    /**
     * 获取公司的月度赏金税收(该日期当月)
     * @param list      公司信息集合
     * @param date      日期,格式为yyyy-MM-dd
     * @return 返回指定日期下公司的赏金税收
     */
    List<CorporationInfo> getCorpBounty(List<CorporationInfo> list, String date);

    /**
     * 获取公司每个账户的收支求和
     */
}
