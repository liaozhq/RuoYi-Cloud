package com.qin.mongo.service;

import java.util.*;

/**
 * 集合工具类
 *
 * @author enilu
 */
public final class Lists {

    private Lists() {
    }


    /**
     * 碾平集合咯，主要针对集合元素为集合的情况有效果
     *
     * @param list
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> flatten(List<?> list) {
        List<T> result = new ArrayList<T>();
        for (Object o : list) {
            if (o instanceof List) {
                List<T> subResult = flatten((List<?>) o);
                result.addAll(subResult);
            } else {
                result.add((T) o);
            }
        }
        return result;
    }

    /**
     * 压缩集合，去掉集合中的null记录
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> compact(List<T> list) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }


    /**
     * 移除与value相等的值, 原数组不发生变化.
     *
     * @param list
     * @param value
     * @return
     */
    public static <T> List<T> without(List<T> list, T value) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if ((value == null && t == null)
                    || (value != null && value.equals(t))) {
                continue;
            }
            result.add(t);
        }
        return result;
    }

    /**
     * 对集合去重，原集合不发生变化
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> List<T> uniq(List<T> input) {
        LinkedHashMap<T, T> map = new LinkedHashMap<>();
        for (T t : input) {
            map.put(t, t);
        }
        return new ArrayList<T>(map.values());
    }

    /**
     * 将数组按n个一份拆分.
     *
     * @param <T>
     * @param input
     * @param n
     * @return
     */
    public static <T> List<List<T>> group(List<T> input, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }

        int size = input.size();
        int m = (size + n - 1) / n;
        List<List<T>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<T> items = new ArrayList(n);
            int end = i < m - 1 ? n : size - i * n;
            for (int j = 0; j < end; j++) {
                items.add(input.get(i * n + j));
            }
            result.add(items);
        }
        return result;
    }



    public static boolean containAny(Set parent, Set child) {
        if (parent == null || child == null) {
            return false;
        }
        Iterator iter = child.iterator();
        while (iter.hasNext()) {
            return parent.contains(iter.next());
        }
        return false;
    }

    public static <V> List<V> newArrayList(V... vs) {
        List<V> list = new ArrayList<V>();
        for (V v : vs) {
            list.add(v);
        }
        return list;
    }
}
