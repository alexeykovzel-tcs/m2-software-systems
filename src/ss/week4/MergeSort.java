package ss.week4;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static <E extends Comparable<E>> List<E> mergeSort(List<E> data) {
        int upperLimit = data.size();
        if (upperLimit <= 1) {
            return data;
        }

        List<E> fst = mergeSort(data.subList(0, upperLimit / 2));
        List<E> snd = mergeSort(data.subList(upperLimit / 2, upperLimit));
        List<E> result = new ArrayList<>();
        int fi = 0, si = 0;

        while (fi < fst.size() && si < snd.size()) {
            if (fst.get(fi).compareTo(snd.get(si)) < 0) {
                result.add(fst.get(fi));
                fi += 1;
            } else {
                result.add(snd.get(si));
                si += 1;
            }
        }

        if (fi < fst.size()) {
            result.addAll(fst.subList(fi, fst.size()));
        } else if (si < snd.size()) {
            result.addAll(snd.subList(si, snd.size()));
        }

        return result;
    }
}
