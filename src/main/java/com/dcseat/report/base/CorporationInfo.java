package com.dcseat.report.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
    private int activePilotNumber;

    // 活跃数得分
    private float activePilotNumberScore;

    // PAP总数
    private float papCount;

    // 人均PAP
    private float perPilotPap;

    // PAP分数
    private float perPilotPapScore;

    // 人均PAP奖励分数
    private float perPilotPapRewardScore;

    // 总量PAP奖励分数
    private float papCountRewardScore;

    // 军团税收
    private float corpTax;

    // 联盟税
    private float allianceTax;

    // 军团税收排名
    private int taxRank;

    // 军团税收得分
    private float taxScore;

    // 最后得分
    private float score;

    public void sumScore() {
        score = activePilotNumberScore +
                perPilotPapScore +
                perPilotPapRewardScore +
                papCountRewardScore +
                taxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorporationInfo that = (CorporationInfo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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




