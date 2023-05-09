package ss.week4;

import java.util.*;

public class MapUtil {
    public static <K, V> boolean isOneOnOne(Map<K, V> map) {
        /*int occurrences = 0;
        for (K k1 : map.keySet()) {
            for (K k2 : map.keySet()) {
                if (k1.equals(k2)) {
                    occurrences += 1;
                    if (occurrences > 1) {
                        return false;
                    }
                }
            }
        }
        return true;*/
        return map.values().stream().allMatch(new HashSet<V>()::add);
    }

    public static <K, V> boolean isSurjectiveOnRange(Map<K, V> map, Set<V> range) {
        /*for (V v : range) {
            if (!map.containsValue(v)) {
                return false;
            }
        }
        return true;*/
        return range.stream().allMatch(map::containsValue);
    }

    public static <K, V> Map<V, Set<K>> inverse(Map<K, V> map) {
        Map<V, Set<K>> inversed = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V key = entry.getValue();
            K value = entry.getKey();

            if (inversed.containsKey(key)) {
                inversed.get(key).add(entry.getKey());
            } else {
                inversed.put(key, new HashSet<>(Collections.singleton(value)));
            }
        }
        return inversed;
    }

    public static <K, V> Map<V, K> inverseBijection(Map<K, V> map) {
        Map<V, K> inversed = new HashMap<>();
        map.forEach((key, value) -> inversed.put(value, key));
        return inversed;
    }

    public static <K, V, W> boolean compatible(Map<K, V> f, Map<V, W> g) {
        Set<V> gKeys = g.keySet();
        if (isOneOnOne(f) && isSurjectiveOnRange(f, gKeys)) {
            return gKeys.containsAll(inverseBijection(f).keySet());
        }
        return gKeys.containsAll(inverse(f).keySet());
    }

    public static <K, V, W> Map<K, W> compose(Map<K, V> f, Map<V, W> g) {
        if (compatible(f, g)) {
            Map<K, W> composed = new HashMap<>();
            for (K k : f.keySet()) {
                composed.put(k, g.get(f.get(k)));
            }
            return composed;
        }
        return null;
    }

}
