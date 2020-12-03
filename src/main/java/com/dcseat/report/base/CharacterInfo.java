package com.dcseat.report.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * 角色信息实体类
 */
@Setter
@Getter
public class CharacterInfo implements Serializable {
    // 角色id
    private Integer id;

    // 角色组
    private Integer groupId;

    // 父角色组
    private Integer parentGroupId;

    // 角色名称
    private String characterName;

    // 公司名称
    private String corporationName;

    // 角色pap总数
    private Float paps;

    // 是否在seat注册
    private Boolean isSeat;
}
