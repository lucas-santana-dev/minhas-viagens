package com.plusapplc.minhaviagem.utils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public final class Empty {

    public static final Object[] ARRAY = {};

    public static final List LIST = new ArrayList(0);

    public static final Object OBJECT = new EmptyObject();

    public static final String STRING = "";

    public static final Number NUMBER = 0;

    public static final Set SET = new HashSet<>(0);

    private static class EmptyObject implements Parcelable, Comparable {

        public static final Creator CREATOR = new Creator() {

            @Override
            public Object createFromParcel(Parcel source) {
                return OBJECT;
            }

            @Override
            public Object[] newArray(int size) {
                return new EmptyObject[size];
            }

        };

        @NonNull
        @Override
        public String toString() {
            return STRING;
        }

        @Override
        public int hashCode() {
            return -1;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || o == null || hashCode() == o.hashCode();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        @Override
        public int compareTo(@NonNull Object another) {
            return equals(another) ? 0 : -1;
        }

    }

}
