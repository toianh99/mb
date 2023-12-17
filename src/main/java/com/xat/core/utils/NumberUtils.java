package com.xat.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {
   public static Double round(Double val, int newScale) {
      return val != null && val > 0.0D ? (new BigDecimal(val.toString())).setScale(newScale, RoundingMode.HALF_UP).doubleValue() : 0.0D;
   }
}
