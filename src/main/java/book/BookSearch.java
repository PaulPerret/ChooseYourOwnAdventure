package book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookSearch {
    public ArrayList<Page> findPath(Map<Integer, Page> pageMap, Integer startPageNum, Integer targetPageNum) {
        ArrayList<Page> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        Page startPage = pageMap.get(startPageNum);
        if (startPage == null) {
            System.err.println("Start page " + startPageNum + " not found in the book.");
            return null; // Start page not found
        }

        if (dfs(startPage, targetPageNum, path, visited)) {
            return path;
        } else {
            return null; // No path found
        }
    }

    private boolean dfs(Page currentPage, Integer targetPageNum, ArrayList<Page> path, Set<Integer> visited) {
        // Mark the current page as visited
        visited.add(currentPage.getPageNumber());
        path.add(currentPage);

        // Check if we've reached the target page
        if (currentPage.getPageNumber() == targetPageNum) {
            return true;
        }

        // Explore each choice from the current page
        for (Page choice : currentPage.getChoices()) {
            if (!visited.contains(choice.getPageNumber())) {
                if (dfs(choice, targetPageNum, path, visited)) {
                    return true; // Path found through this choice
                }
            }
        }

        // Backtrack: remove the current page from the path and mark it as unvisited
        path.remove(path.size() - 1);
        visited.remove(currentPage.getPageNumber());
        return false; // No path found from this page
    }
}
