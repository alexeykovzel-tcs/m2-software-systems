package ss.week1;

import java.util.ArrayList;
import java.util.List;

public class MostDivisions {
    public static void main(String[] args) {
        List<Integer> dividendsList = getNumbersWithMostDivisors(1, 10000);
        int maxDivisorsCount = countDivisors(dividendsList.get(0));

        System.out.printf("Among integers between 1 and 10000,%n" +
                "The maximum number of divisors was %s%n" +
                "Numbers with that many divisors include:%n", maxDivisorsCount);
        dividendsList.forEach(System.out::println);
    }

    private static List<Integer> getNumbersWithMostDivisors(int lowerBound, int upperBound) {
        int maxDivisorsCount = 0;
        List<Integer> dividendsList = new ArrayList<>();

        for (int i = lowerBound; i <= upperBound; i++) {
            int divisorsCount = countDivisors(i);

            if (divisorsCount > maxDivisorsCount) {
                maxDivisorsCount = divisorsCount;
                dividendsList.clear();
                dividendsList.add(i);
            } else if (divisorsCount == maxDivisorsCount) {
                dividendsList.add(i);
            }
        }
        return dividendsList;
    }

    private static int countDivisors(int dividend) {
        switch (dividend) {
            case 0:
                return -1;
            case 1:
                return 0;
            default:
                int count = 1; // includes the dividend itself
                for (int i = 1; i < Math.abs(dividend) / 2 + 1; i++) {
                    if (dividend % i == 0) {
                        count++;
                    }
                }
                return count;
        }
    }
}
