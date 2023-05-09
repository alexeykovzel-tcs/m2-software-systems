package ss.week4;

import java.util.Arrays;

public class ArrayExercises {
    public static int countNegativeNumbers(int[] arr) {
        return (int) Arrays.stream(arr).filter(el -> el < 0).count();
    }

    public static void reverseArray(int[] ints) {
        int lastIdx = ints.length - 1;
        for (int i = 0; i < (lastIdx + 1) / 2; i++) {
            int temp = ints[i];
            ints[i] = ints[lastIdx - i];
            ints[lastIdx - i] = temp;
        }
    }

    class SimpleList {
        public class Element {
        }

        public class Node {
            public Node next;
            public Element element;
        }

        private Node first;

        private Node find(Element element) {
            Node p = first;
            if (p == null) {
                return null;
            }
            while (p.next != null && !p.next.element.equals(element)) {
                p = p.next;
            }
            if (p.next == null) {
                return null;
            } else {
                return p;
            }
        }

        public void remove(Element element) {
            Node p = find(element);
            if (p != null) {
                p.next = p.next.next;
            }
        }
    }
}
