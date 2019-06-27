package com.dayuanit.movie.movie.utils;

import java.math.BigDecimal;

public class MoneyUtils {

    public static String mul(String num1,String num2){
        BigDecimal bigDecimal1 = new BigDecimal(num1);
        BigDecimal bigDecimal2= new BigDecimal(num2);
        return bigDecimal1.multiply(bigDecimal2).setScale(2,BigDecimal.ROUND_HALF_EVEN).toString();
    }
}
