report项目介绍
用途：
    1.匹配联盟月报需求，通过生成exe文件/作为jar包加入总体项目来生成联盟月报excel文档。
    2.可扩展军团月报需求，通过生成exe文件/作为jar包加入总体项目来生成军团月报excel文档。

要求：
    1.月报指标可拆分，能独立成模块
    2.月报信息持久化
    3.可通过配置文件调节

目录：
    com.dcseat.report
        ——base      表实体类
        ——dao       sqlmapper映射类
        ——module    月报模块
        ——util      工具类、对外开放接口
        ——log       日志
        corp.java   公司报表顶层
        all.java    联盟报表顶层

