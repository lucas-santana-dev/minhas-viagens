package com.plusapplc.minhaviagem.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.StrikethroughSpan;

import androidx.annotation.NonNull;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

    private static final String HEXES = "0123456789ABCDEF";
    private static final Pattern REGEX_RTF = Pattern.compile("(\\{\\\\)(.+?)(\\})|(\\\\)(.+?)(\\b)|\\}");

    public static String trim(CharSequence s) {
        if (s == null) return Empty.STRING;

        return s.toString().trim();
    }

    public static boolean isEmpty(CharSequence s) {
        return trim(s).length() == 0;
    }

    public static String captalize(String s) {
        if (s == null) {
            return null;
        } else if (s.length() < 2) {
            return s.toUpperCase();
        } else {
            return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        }
    }

    public static boolean isSequenceOf(CharSequence s, char c) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!Objects.equals(c, s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static CharSequence coalesce(CharSequence s, CharSequence defaultValue) {
        return isEmpty(s) ? defaultValue : s;
    }

    public static String concat(CharSequence... args) {
        if (args == null || args.length == 0) return null;

        StringBuilder builder = new StringBuilder(args.length * 7);
        for (CharSequence s : args) {
            if (s != null && s.length() > 0) {
                builder.append(s);
            }
        }
        return builder.toString();
    }

    public static String sequenceOf(char c, int length) {
        if (length <= 0) return null;
        return CharBuffer.allocate(length).toString().replace('\0', c);
    }

    public static String toHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return Empty.STRING;
        }
        final StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static CharSequence rtfToPlain(CharSequence s) {
        if (!isEmpty(s)) {
            Matcher matcher = REGEX_RTF.matcher(s);
            String plain = matcher.replaceAll(Empty.STRING);
            return plain.matches("\\w") ? plain : Empty.STRING;
        }
        return Empty.STRING;
    }

    public static int compare(String lhs, String rhs) {
        if (lhs.equals(rhs)) return 0;
        if (lhs == null) return -rhs.length();
        if (rhs == null) return lhs.length();

        return lhs.compareToIgnoreCase(rhs);
    }

    public static boolean containsLetters(CharSequence s) {
        if (s == null) return false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(String lhs, String rhs) {
        if (lhs == null || rhs == null) return false;

        return lhs.toLowerCase().contains(rhs.toLowerCase());
    }

    public static boolean startsWith(String text, String filter) {
        if (text == null) return false;
        if (isEmpty(filter)) return true;

        String mText = text.toUpperCase();
        String[] mFilter = filter.toUpperCase().split(" ");
        for (String s : mFilter) {
            String mSpan = s.trim();
            if (mSpan.length() == 0) continue;

            if (!mText.startsWith(mSpan) && !mText.contains(' ' + mSpan)) {
                return false;
            }
        }
        return true;
    }

    public static CharSequence strikethru(CharSequence text) {
        Spannable span = new SpannableString(text);
        span.setSpan(new StrikethroughSpan(), 0, text.length(), 0);
        return span;
    }

    public static CharSequence spannable(@NonNull String text, String filter) {
        if (isEmpty(filter)) return text;

        return spannable(text, filter.trim().toUpperCase().split(" "));
    }

    public static CharSequence spannable(@NonNull String text, @NonNull String[] spans) {
        Spannable spannableString = new SpannableString(text);

        String mText = text.toUpperCase();
        for (String s : spans) {
            String mSpan = s.trim();
            if (mSpan.length() == 0) continue;

            int start = mText.startsWith(mSpan) ? 0 : mText.indexOf(' ' + mSpan);
            if (start > 0) {
                start = start + 1;
            }
            while (start > -1 && start < mText.length()) {
                spannableString.setSpan(new BackgroundColorSpan(Color.LTGRAY), start, start + mSpan.length(), Spannable.SPAN_COMPOSING);
                start = mText.indexOf(' ' + mSpan, start + 1);
                if (start > 0) {
                    start = start + 1;
                }
            }
        }
        return spannableString;
    }

    /**
     * Author sadao.
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Author sadao.
     */
    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}
