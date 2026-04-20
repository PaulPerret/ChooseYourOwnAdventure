import java.util.ArrayList;
import java.util.List;
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
        Integer targetPageNum = 42;
        List<List<Page>> solutionList = bookSearch.findPaths(book, 1, targetPageNum);

        System.out.println("Solutions found: " + (solutionList != null ? solutionList.size() : 0));
        for (List<Page> path : solutionList) {
            for (Page page : path) {
                System.out.print(page.getPageNumber());
                if (page.getPageNumber() != targetPageNum) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}