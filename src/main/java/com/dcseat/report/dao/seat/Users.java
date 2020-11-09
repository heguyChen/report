package com.dcseat.report.dao.seat;

import com.dcseat.report.base.CorporationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主角色相关的sql语句映射实体类
 */
@Repository
public interface Users {

    /**
     * 重载
     * 根据公司id得到本公司指定时间内的活跃主角色数量(该日期当月)
     * @param list      公司信息集合
     * @param date      日期,格式为yyyy-MM-dd
     * @return 公司的活跃主角色数
     */
    List<CorporationInfo> getActiveUsersNumberByCorp(List<CorporationInfo> list, String date);
}
