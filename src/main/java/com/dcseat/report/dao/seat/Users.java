package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.base.CorporationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主角色相关的sql语句映射实体类
 */
@Repository
public interface Users {

    /**
     * 按照联盟旗下选定公司获取所有角色信息，以活跃人口为基准
     * @param list      公司信息集合
     * @param date      日期,格式为yyyy-MM-dd 活跃标准
     * @return 公司全部活跃成员信息
     */
    List<CharacterInfo> getUsers(List<CorporationInfo> list, String date);

    /**
     * 根据公司id得到本公司指定时间内的活跃主角色数量(该日期当月)
     * @param list      公司信息集合
     * @param date      日期,格式为yyyy-MM-dd
     * @return 公司的活跃主角色数(统计已注册seat成员)
     */
    List<CorporationInfo> getActiveUsersNumberByCorp(List<CorporationInfo> list, String date);

    /**
     * 根据公司id得到本公司指定时间内的活跃游戏角色数量(该日期当月)
     * @param list      公司信息集合
     * @param date      日期,格式为yyyy-MM-dd
     * @return 公司的活跃角色数(统计未注册seat成员)
     */
    List<CorporationInfo> getActiveUnknownNumberByCorp(List<CorporationInfo> list, String date);


}
