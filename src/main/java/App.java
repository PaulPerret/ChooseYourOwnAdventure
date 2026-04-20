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

        // Prompt user for what page they want to find
        System.out.print("> Enter the page number you want to find: ");
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            Integer targetPageNum = scanner.nextInt();

            // Do search for a path from page 1 to page 17
            BookSearch bookSearch = new BookSearch();
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
}