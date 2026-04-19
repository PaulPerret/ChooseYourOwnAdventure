import java.util.ArrayList;
import java.util.Map;

import book.BookBuilder;
import book.BookSearch;
import book.Page;

public class App {
    public static void main(String[] args) {

        BookBuilder bookBuilder = new BookBuilder();
        Map<Integer, Page> book = bookBuilder.buildBook("transformers");

        // Do search for a path from page 1 to page 17
        BookSearch bookSearch = new BookSearch();
        Integer targetPageNum = 17;
        ArrayList<Page> path = bookSearch.findPath(book, 1, targetPageNum);

        if (path != null) {
            System.out.println("Path found from page 1 to page " + targetPageNum + ":");
            for (Page page : path) {
                System.out.print(page.getPageNumber() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path found from page 1 to page " + targetPageNum);
        }
    }
}