import java.util.Map;

import book.BookBuilder;
import book.Page;

public class App {
    public static void main(String[] args) {

        BookBuilder bookBuilder = new BookBuilder();
        Map<Integer, Page> book = bookBuilder.buildBook("transformers");

    }
}