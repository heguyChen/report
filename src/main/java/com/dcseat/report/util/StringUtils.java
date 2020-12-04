package com.dcseat.report.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 获取sql语句date
     * @return
     */
    public static String getSqlDate() {
        String year = getThisYear();
        String month = getThisMonth();
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
        String year = getThisYear();
        String month = getThisMonth();
        return new StringBuilder(year)
                .append("-")
                .append(month).toString();
    }

    /**
     * 读取时间 取出年份
     * @return
     */
    public static String getThisYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 读取时间 取出月份
     * @return
     */
    public static String getThisMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        return String.valueOf(month);
    }

}
