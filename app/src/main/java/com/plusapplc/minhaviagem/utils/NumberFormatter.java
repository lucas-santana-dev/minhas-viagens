package com.plusapplc.minhaviagem.utils;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class NumberFormatter {

    public static final String DECIMAL_FORMAT = "0.00";
    public static final String DECIMAL_FORMAT_THREE = "0.00#";
    public static final String DECIMAL_FORMAT_EX = "#,##0.00";
    public static final String NUMBER_FORMAT = "0.###";
    public static final String NUMBER_FORMAT_EX = "#,##0.###";
    private DecimalFormat formatter;
    private DecimalFormat shortFormatter;

    public NumberFormatter(@NonNull String pattern) {
        this(false, pattern, null);
    }

    public NumberFormatter(boolean appendCurrencySymbol, @NonNull String pattern) {
        this(appendCurrencySymbol, pattern, null);
    }

    public NumberFormatter(@NonNull String pattern, String shortPattern) {
        this(false, pattern, shortPattern);
    }

    public NumberFormatter(boolean appendCurrencySymbol, @NonNull String pattern, String shortPattern) {
        if (appendCurrencySymbol) {
            String currencySymbol = DecimalFormatSymbols.getInstance().getCurrencySymbol();
            formatter = new DecimalFormat(currencySymbol + " " + pattern);
            if (!Strings.isEmpty(shortPattern)) {
                shortFormatter = new DecimalFormat(currencySymbol + " " + shortPattern);
            }
        } else {
            formatter = new DecimalFormat(pattern);
            if (!Strings.isEmpty(shortPattern)) {
                shortFormatter = new DecimalFormat(shortPattern);
            }
        }
    }

    public static NumberFormatter currency() {
        return currency(true);
    }

    public static NumberFormatter currency(boolean canonical) {
        if (canonical) {
            return new NumberFormatter(true, DECIMAL_FORMAT_EX, NUMBER_FORMAT_EX);
        } else {
            return new NumberFormatter(true, DECIMAL_FORMAT_EX);
        }
    }

    public static NumberFormatter decimal() {
        return decimal(false);
    }

    public static NumberFormatter decimal(boolean canonical) {
        return decimal(canonical, true);
    }

    public static NumberFormatter decimal(boolean canonical, boolean shortFormatter) {
        if (canonical && shortFormatter) {
            return new NumberFormatter(DECIMAL_FORMAT_EX, NUMBER_FORMAT_EX);
        } else if(canonical) {
            return new NumberFormatter(DECIMAL_FORMAT_EX);
        } else {
            return new NumberFormatter(DECIMAL_FORMAT);
        }
    }

    public static NumberFormatter number() {
        return number(true);
    }

    public static NumberFormatter number(boolean canonical) {
        if (canonical) {
            return new NumberFormatter(NUMBER_FORMAT_EX, NUMBER_FORMAT_EX);
        } else {
            return new NumberFormatter(NUMBER_FORMAT);
        }
    }

    public String format(double value) {
        if (shortFormatter != null) {
            if (value >= Numbers.MI) {
                return shortFormatter.format(Math.floor(value * 0.000001)) + "mi";
            } else if (value >= Numbers.MIL) {
                return shortFormatter.format(Math.floor(value * 0.001)) + "mil";
            }
        }
        return formatter.format(value);
    }


}
