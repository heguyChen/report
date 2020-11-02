package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CorporationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联盟相关的sql语句映射实体类
 */
@Repository
public interface Alliances {

    /**
     * 根据联盟名称获取旗下所有军团详细信息
     * @param allianceName      联盟全称
     * @return CorporationInfo对象集合
     */
    List<CorporationInfo> getCorpInfosByAllianceName(String allianceName);
}
