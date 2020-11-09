package com.dcseat.report.util;

import java.math.BigDecimal;

/**
 * 计算工具类
 */
public class MathUtils {

    /**
     * 计算除法
     * @param n1    被除数
     * @param n2    除数
     * @return  返回float型浮点数
     */
    public static Float division (String n1, String n2) {
        BigDecimal divide = new BigDecimal(n1).divide(new BigDecimal(n2), 2, BigDecimal.ROUND_HALF_UP);
        return divide.floatValue();
    }
}
