package com.plusapplc.minhaviagem.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Objects {

    public static boolean in(Object object, Object... others) {
        if (object == null || others == null) return false;
        for (Object other : others) {
            if (equals(object, other)) return true;
        }
        return false;
    }

    public static boolean equals(Object object, Object other) {
        if (object == other) return true;
        if (object == null || other == null) return false;
        if (object.getClass() != other.getClass()) return false;

        return object.hashCode() == other.hashCode();
    }

    public static int hashCode(Object... values) {
        return Arrays.hashCode(values);
    }

    public static String toString(Object o) {
        if (o == null) return Empty.STRING;

        Class classType = o.getClass();
        Field[] fields = classType.getDeclaredFields();
        StringBuilder sb = new StringBuilder(fields.length * 7);
        sb.append(classType.getSimpleName());
        sb.append(": {");
        boolean hasNext = false;
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers) && !Modifier.isTransient(modifiers)) {
                f.setAccessible(true);
                if (hasNext) {
                    sb.append(',');
                } else {
                    hasNext = true;
                }
                try {
                    sb.append(f.getName());
                    sb.append("=");
                    sb.append(f.get(o));
                } catch (IllegalAccessException e) {
                    Log.e(classType.getName(), e.getLocalizedMessage());
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public static Compare compare(Comparable lhs, Comparable rhs) {
        Compare comparator = new Compare();
        return comparator.compare(lhs, rhs);
    }

    public static Compare compareIgnoreCase(String lhs, String rhs) {
        Compare comparator = new Compare();
        return comparator.compareIgnoreCase(lhs, rhs);
    }

    public static final class Compare {

        Collection<Entry> entrySet = new ArrayList<>();

        public Compare compare(Comparable lhs, Comparable rhs) {
            Entry entry = new Entry();
            entry.lhs = lhs;
            entry.rhs = rhs;
            entrySet.add(entry);
            return this;
        }

        public Compare compareIgnoreCase(String lhs, String rhs) {
            Entry entry = new EntryIgnoreCase();
            entry.lhs = lhs;
            entry.rhs = rhs;
            entrySet.add(entry);
            return this;
        }

        public int result() {
            for (Entry e : entrySet) {
                int result = e.compare();
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }

    }

    static class EntryIgnoreCase extends Entry<String> {
        @Override
        public int compare() {
            if (lhs.equals(rhs)) return 0;
            if (lhs == null) return -1;
            if (rhs == null) return 1;

            return lhs.compareToIgnoreCase(rhs);
        }
    }

    static class Entry<T extends Comparable<T>> {
        T lhs;
        T rhs;

        public int compare() {
            if (lhs == rhs) return 0;
            if (lhs == null) return -1;
            if (rhs == null) return 1;

            return lhs.compareTo(rhs);
        }

    }

}
