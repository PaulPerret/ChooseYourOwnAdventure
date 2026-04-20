package book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookSearch {
    public List<List<Page>> findPaths(Map<Integer, Page> pageMap, Integer startPageNum, Integer targetPageNum) {
        ArrayList<Page> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        ArrayList<List<Page>> allPaths = new ArrayList<>();

        Page startPage = pageMap.get(startPageNum);
        if (startPage == null) {
            System.err.println("Start page " + startPageNum + " not found in the book.");
            return null; // Start page not found
        }

        dfs(startPage, targetPageNum, path, visited, allPaths);
        return allPaths;
    }

    private void dfs(Page currentPage, Integer targetPageNum, ArrayList<Page> currentPath, Set<Integer> visited, List<List<Page>> allPaths) {
        // Mark the current page as visited
        visited.add(currentPage.getPageNumber());
        currentPath.add(currentPage);

        // Check if we've reached the target page. If so, add the current path to the list
        // of all paths
        System.out.println("Visiting page " + currentPage.getPageNumber() + ", current path: " + getPathString(currentPath));
        if (currentPage.getPageNumber() == targetPageNum) {
            allPaths.add(new ArrayList<>(currentPath));
            System.out.println("- Found target.");
        } else {
            // Explore each choice from the current page
            for (Page choice : currentPage.getChoices()) {
                if (!visited.contains(choice.getPageNumber())) {
                    dfs(choice, targetPageNum, currentPath, visited, allPaths);
                }
            }
        }

        // Backtrack: remove the current page from the path and mark it as unvisited
        System.out.println("Backtracking from page " + currentPage.getPageNumber());
        currentPath.remove(currentPath.size() - 1);
        visited.remove(currentPage.getPageNumber());
    }

    private String getPathString(ArrayList<Page> currentPath) {
        StringBuilder sb = new StringBuilder();
        for (Page page : currentPath) {
            sb.append(page.getPageNumber()).append(" -> ");
        }
        if (sb.length() > 4) {
            sb.setLength(sb.length() - 4); // Remove the last " -> "
        }
        return sb.toString();
    }
}
