package com.dcseat.report.dao.seat;

import org.springframework.stereotype.Repository;

public interface Users {

    /**
     * 根据公司id得到本公司指定时间内的活跃主角色数量
     * @param corpId    公司id
     * @param term      有效日期 一般为30天
     * @return 公司的活跃主角色数
     */
    Integer getActiveUsersByCorp(Integer corpId, Integer term);

    /**
     * 重载
     * 根据公司id得到本公司指定时间内的活跃主角色数量
     * @param corpId    公司id
     * @param year      年
     * @param month     月
     * @return 公司的活跃主角色数
     */
    Integer getActiveUsersByCorp(Integer corpId, Integer year, Integer month);
}
