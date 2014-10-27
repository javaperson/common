package com.github.javaperson.common.util;

import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

/**
 * User: jude.wang
 * Date: 14-4-23
 * Time: 下午6:49
 */
public class GuavaUtils {
    public static <T> Map<T, Integer> toMap(Multiset<T> multiset) {
        Map<T, Integer> map = Maps.newHashMap();
        if (multiset != null) {
            for (Multiset.Entry<T> entry : multiset.entrySet()) {
                map.put(entry.getElement(), entry.getCount());
            }
        }
        return map;
    }

    public static <T> Multiset<T> toMultiset(Map<T, Integer> map) {
        Multiset<T> multiset = HashMultiset.create();
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            multiset.add(entry.getKey(), entry.getValue());
        }
        return multiset;
    }


    public static <T> boolean isNullOrEmpty(T[] array) {
        return _isNullOrEmpty(array);
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(byte[] array) {
        return _isNullOrEmpty(array);
    }

    private static boolean _isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        }

        Class<?> clazz = object.getClass();
        checkState(clazz.isArray(), "only support array.");
        return Array.getLength(object) == 0;
    }

    public static <T extends Comparable> Comparator<List<T>> comparator() {
        return new Comparator<List<T>>() {
            @Override
            public int compare(List<T> o1, List<T> o2) {
                int len1 = o1.size();
                int len2 = o2.size();
                int lim = Math.min(len1, len2);

                int k = 0;
                while (k < lim) {
                    T t1 = o1.get(k);
                    T t2 = o2.get(k);
                    if (!t1.equals(t2)) {
                        return t1.compareTo(t2);
                    }
                    k++;
                }
                return len1 - len2;
            }
        };
    }

    public static <T> List<T> splitToList(CharSequence sequence, char separator, Converter<String, T> converter) {
        return Lists.transform(Splitter.on(separator).splitToList(sequence), converter);
    }
}
