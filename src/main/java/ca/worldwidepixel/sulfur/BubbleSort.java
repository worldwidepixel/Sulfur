package ca.worldwidepixel.sulfur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BubbleSort {
    public static List<Integer> sort(List<Integer> sortList) {
        List<Integer> list = new ArrayList<>(sortList);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if(list.get(i) > list.get(i + 1)) {
                    Collections.swap(list, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
        return list;
    }
}
