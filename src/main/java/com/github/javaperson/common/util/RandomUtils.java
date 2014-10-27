package com.github.javaperson.common.util;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;

import static org.apache.commons.lang.math.RandomUtils.JVM_RANDOM;

/**
 * Created by wangyang on 14-8-6.
 */
public class RandomUtils {
    private static Random random() {
        return JVM_RANDOM;
    }

    public static <T> T randObject(T... objects) {
        return objects[random().nextInt(objects.length)];
    }

    public static <T> T randObject(List<T> objects, List<Integer> weights) {
        Map<T, Integer> objectWeightMap = Maps.newHashMap();
        for (int i = 0; i < objects.size(); i++) {
            T object = objects.get(i);
            int weight = 0;
            if (i < weights.size()) {
                weight = weights.get(i);
            }
            objectWeightMap.put(object, weight);

        }
        return randObject(objectWeightMap);
    }

    public static <T> T randObject(List<T> objects) {
        return objects.get(random().nextInt(objects.size()));
    }

    public static <T> T randObject(Map<T, Integer> objectWeightMap) {
        RandomCollection<T> rc = new RandomCollection<T>(random());

        for (Map.Entry<T, Integer> entry : objectWeightMap.entrySet()) {
            rc.add(entry.getValue(), entry.getKey());
        }

        return rc.next();
    }

    public static <T> T randObject(List<T> objects, Function<T, Integer> function) {
        RandomCollection<T> rc = new RandomCollection<T>(random());

        for (T object : objects) {
            rc.add(function.apply(object), object);
        }

        return rc.next();
    }

    public static boolean randBoolean(double probability) {
        return random().nextDouble() < probability;
    }

    public static class RandomCollection<E> {
        private final NavigableMap<Double, E> map = Maps.newTreeMap();
        private final Random random;
        private double total = 0;


        public RandomCollection(Random random) {
            this.random = random;
        }

        public void add(double weight, E object) {
            if (weight <= 0) return;
            total += weight;
            map.put(total, object);
        }

        public E next() {
            double value = random.nextDouble() * total;
            Map.Entry<Double, E> entry = map.ceilingEntry(value);
            return entry == null ? null : entry.getValue();
        }
    }
}
