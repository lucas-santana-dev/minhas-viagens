package com.plusapplc.minhaviagem.utils;

import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

public class Numbers {

    public static final String TAG = Numbers.class.getSimpleName();

    public static final double MI = 999999999;
    public static final double MIL = 999999;

    public static Number coalesce(Number n, Number defaultValue) {
        return n == null ? defaultValue : n;
    }

    public static String digitisOnly(CharSequence str) {
        if (Strings.isEmpty(str)) return Empty.STRING;
        if (TextUtils.isDigitsOnly(str)) return str.toString();

        final int len = str.length();
        StringBuilder textResult = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                textResult.append(c);
            }
        }
        return textResult.toString();
    }

    public static double strToDouble(CharSequence s) {
        return strToDouble(s, -1);
    }

    public static double strToDouble(CharSequence s, double defaultValue) {
        if (s != null && s.length() > 0) {
            try {
                char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

                String value = s.toString().replace('.', separator).replace("+", "").replace(" ", "");

                NumberFormat formatter = new DecimalFormat("0.###");
                double factor;
                if (value.endsWith("mi")) {
                    value = value.replace("mi", "");
                    factor = MI;
                } else if (value.endsWith("mil")) {
                    value = value.replace("mil", "");
                    factor = MIL;
                } else {
                    return formatter.parse(value).doubleValue();
                }
                return formatter.parse(value).doubleValue() * factor;
            } catch (ParseException e) {
                Log.w(TAG, e.getMessage(), e);
            }
        }
        return defaultValue;
    }

    public static double strToDoubleDesconto(CharSequence s, double defaultValue) {
        if (s != null && s.length() > 0) {
            try {
                char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();

                String value = s.toString().replace('.', separator);//.replace("+", "");
                String value2 = value.replace("+", "");

                value = value.equals(value2) ? (value.startsWith("-") ? value : "-" + value) : value2;

                NumberFormat formatter = new DecimalFormat("0.##");
                double factor;
                if (value.endsWith("mi")) {
                    value = value.replace("mi", "");
                    factor = MI;
                } else if (value.endsWith("mil")) {
                    value = value.replace("mil", "");
                    factor = MIL;
                } else {
                    return roundTo(formatter.parse(value).doubleValue(), 1);
                }
                return roundTo(formatter.parse(value).doubleValue(), 1) * factor;
            } catch (ParseException e) {
                Log.w(TAG, e.getMessage(), e);
            }
        }
        return defaultValue;
    }

    public static double round(double value) {
        return roundTo(value, 2);
    }

    public static int doubleToInt(double value) {
        return (int) value;
    }

    public static double roundTo(double value, int round) {
//        double mRound = Math.pow(10, round);
//        return ((int)(value * mRound)) / mRound;
        return BigDecimal.valueOf(value).setScale(round, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double floor(double value) {
        return Math.floor(value);
    }

    public static int strToInt(CharSequence s) {
        return strToInt(s, -1);
    }

    public static int strToInt(CharSequence s, int defaultValue) {
        int intValue = 0;

        if (s != null && s.length() > 0) {
            String value = s.toString().replace(".", "");

            try {
                intValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                intValue = defaultValue;
            }
        }

        return intValue;
    }

    public static String formatCurrency(String s) {
        String v = Numbers.digitisOnly(s);

        if (v.isEmpty()) {
            v = "0";
        }

        if (v.length() == 1) {
            v = "0,0" + v;
        } else if (v.length() == 2) {
            v = "0," + v;
        } else {
            String part1 = v.substring(v.length() - 2);
            String part2 = v.substring(0, v.length() - 2);
            v = part2 + "," + part1;
        }

        return v;
    }

    public static String strZero(int string, int quantidade) {
        String mFormat = "";
        for (int i = 0; i < quantidade; i++) {
            mFormat = mFormat.concat("0");
        }
        return new DecimalFormat(mFormat).format(string);
    }

}
