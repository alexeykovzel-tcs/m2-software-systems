package ss.week3.bill.printer;

public class StringPrinter implements Printer {
    private final StringBuilder lines = new StringBuilder();

    @Override
    public void printLine(String text, double price) {
        lines.append(format(text, price))
                .append(System.lineSeparator());
    }

    public String getResult() {
        return lines.toString();
    }
}
