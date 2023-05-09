package ss.week1;

import ss.utils.TextIO;

public class Fibonacci {
    public static long fibonacciRecursive(int index) {
        if (index < 2) {
            return index;
        }
        return fibonacciRecursive(index - 2) + fibonacciRecursive(index - 1);
    }

    public static long fibonacciWithArray(int index) {
        if (index <= 1) {
            return index;
        }

        long[] array = new long[index + 1];
        array[0] = 0;
        array[1] = 1;

        for (int i = 2; i <= index; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[array.length - 1];
    }

    public static void main(String[] args) {
        System.out.println("Please write the Fibonacci index:");
        int index = TextIO.getlnInt();

        long fibonacci = fibonacciRecursive(index);
        System.out.println(fibonacci);
    }
}
