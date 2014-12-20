package com.agilemods.materiamuto.common.emc;

import java.text.DecimalFormat;

public class EMCHelper {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###,##0.00");

    public static String emcToString(double emc) {
        return DECIMAL_FORMAT.format(emc);
    }
}
