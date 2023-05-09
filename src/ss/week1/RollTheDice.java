package ss.week1;

public class RollTheDice {
    public static void main(String[] args) {
        Dice dice = new Dice();

        int firstRoll = dice.roll();
        int secondRoll = dice.roll();
        int finalResult = firstRoll + secondRoll;

        System.out.printf("The first dice comes up %s%n" +
                        "The second dice comes up %s%n" +
                        "Your final result is %s%n",
                firstRoll, secondRoll, finalResult);
    }
}

class Dice {
    int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}
