package ss.week3.bill.printer;

public class SysoutPrinter implements Printer {
    public static void main(String[] args) {
        Printer printer = new SysoutPrinter();
        printer.printLine("Car", 12.0);
        printer.printLine("Bottleeeeeeeeeeeeeeeeeee", 50);
    }

    @Override
    public void printLine(String text, double price) {
        System.out.println(format(text, price));
    }
}