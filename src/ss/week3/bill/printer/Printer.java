package ss.week3.bill.printer;

public interface Printer {
    /**
     * Default printer formatter.
     *
     * @param text  text to format
     * @param price price to put in the text
     * @return formatted line listing the item and price, ending on a newline
     */
    default String format(String text, double price) {
        StringBuilder billText = new StringBuilder();
        int textLimit = 24;
        int textLength = text.length();

        if (textLength >= textLimit){
            billText.append(text, 0, textLimit - 3).append(".. ");
        } else {
            String offset = new String(new char[textLimit - textLength]).replace("\0", " ");
            billText.append(text).append(offset);
        }

        return String.format("%s%.2f", billText, price);
    }

    /**
     * Uses format to send the combination of text and price
     * to the printer in a way that is specific to the implementation.
     *
     * @param text  text to format
     * @param price price to put in the text
     */
    void printLine(String text, double price);
}
