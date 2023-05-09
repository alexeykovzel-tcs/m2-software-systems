package ss.week1;

import ss.utils.TextIO;

public class BabylonianAlgorithm {
    public static void main(String[] args) {
        System.out.println("Please write the input value:");
        double inputValue = TextIO.getDouble();

        double guess = babylonAlgorithm(inputValue);
        System.out.println(guess);
    }

    public static double babylonAlgorithm(double n) {
        double guess = n / 2;

        while (true) {
            double nextGuess = (guess + n / guess) / 2;
            boolean close = (Math.abs(nextGuess - guess)
                    / Math.max(guess, nextGuess)) < 0.0001; // should be 0.01 to be 1%

            if (close) {
                break;
            }
            guess = nextGuess;
        }
        return guess;
    }
}
