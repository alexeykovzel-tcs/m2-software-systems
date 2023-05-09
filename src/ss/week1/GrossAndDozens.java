package ss.week1;

import ss.utils.TextIO;

public class GrossAndDozens {
    public static void main(String[] args) {
        System.out.println("Please input the number of eggs:");
        int eggs = TextIO.getlnInt();

        int grosses = eggs / 144;
        eggs -= grosses * 144;

        int dozens = eggs / 12;
        eggs -= dozens * 12;

        System.out.printf("Your number of eggs is %s gross , %s dozen , and %s", grosses, dozens, eggs);
    }
}