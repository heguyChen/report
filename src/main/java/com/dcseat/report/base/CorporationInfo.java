package com.dcseat.report.base;

import lombok.Getter;
import lombok.Setter;
/**
 * 公司信息实体类
 */
@Setter
@Getter
public class CorporationInfo {
    // 公司id
    private Integer id;

    // 公司名称
    private String name;

    // 公司税率
    private Float taxRate;

    // 活跃数
    private Integer activePilotNumber;

    // 活跃数得分
    private Float activePilotNumberScore;

    // PAP总数
    private Float papCount;

    // 人均PAP
    private Float perPilotPap;

    // PAP分数
    private Float perPilotPapScore;

    // 人均PAP奖励分数
    private Float perPilotPapRewardScore;

    // 总量PAP奖励分数
    private Float papCountRewardScore;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Float getTaxRate() {
//        return taxRate;
//    }
//
//    public void setTaxRate(Float taxRate) {
//        this.taxRate = taxRate;
//    }

}




