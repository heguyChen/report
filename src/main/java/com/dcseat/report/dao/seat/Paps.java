package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.base.CorporationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * pap相关的sql语句映射实体类
 */
@Repository
public interface Paps {

    /**
     * 获取公司级别的pap数据(该日期当月)
     * @param list    公司id
     * @param year      年份
     * @param month     月份
     * @return 该军团当月pap总量
     */
    List<CorporationInfo> getPapsByCorp(List<CorporationInfo> list, String year, String month);

    /**
     * 获取成员级别的pap数据(该日期当月)
     * @param list    成员id
     * @param year      年份
     * @param month     月份
     * @return 该成员所有角色当月pap总量
     */
    List<CharacterInfo> getCharacterPaps(List<CharacterInfo> list, String year, String month);

    /**
     * 获取公司pap的基准(指定pap排名)
     * @param allianceName  联盟名称
     * @param year          年份
     * @param month         月份
     * @param rank          排名
     * @return  返回排名第几的pap数量
     */
    Float getCorpPapsByRank(String allianceName, String year, String month, Integer rank);

}
