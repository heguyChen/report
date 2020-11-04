package com.dcseat.report.util;

import java.math.BigDecimal;

public class MathUtils {

    public static Float division (String n1, String n2) {
        BigDecimal divide = new BigDecimal(n1).divide(new BigDecimal(n2), 2, BigDecimal.ROUND_HALF_UP);
        return divide.floatValue();
    }
}
