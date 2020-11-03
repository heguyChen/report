package com.dcseat.report.util;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 获取sql语句date
     * @return
     */
    public static String getSqlDate() {
        String year = PropertiesUtil.getProperty("dc.year");
        String month = PropertiesUtil.getProperty("dc.month");
        String day = PropertiesUtil.getProperty("dc.day");
        return new StringBuilder(year)
                .append("-")
                .append(month)
                .append("-")
                .append(day).toString();
    }

    /**
     * 获取excel文本标题的日期
     * @return
     */
    public static String getTitleDate() {
        String year = PropertiesUtil.getProperty("dc.year");
        String month = PropertiesUtil.getProperty("dc.month");
        return new StringBuilder(year)
                .append("-")
                .append(month).toString();
    }

}
