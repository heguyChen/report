package com.dcseat.report.util;

public class StringUtils {

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

}
