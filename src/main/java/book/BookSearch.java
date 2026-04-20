package book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookSearch {

    private Boolean currentlyBacktracking = false;

    public List<List<Page>> findPaths(Map<Integer, Page> pageMap, Integer startPageNum, Integer targetPageNum) {
        ArrayList<Page> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        ArrayList<List<Page>> allPaths = new ArrayList<>();

        Page startPage = pageMap.get(startPageNum);
        if (startPage == null) {
            System.err.println("Start page " + startPageNum + " not found in the book.");
            return null; // Start page not found
        }

        System.out.println("Starting search from page " + startPageNum);
        
        // Kick off recursive depth-first search from the start page
        dfs(startPage, targetPageNum, path, visited, allPaths);

        System.out.println("\nSearch complete.");
        return allPaths;
    }

    private void dfs(Page currentPage, Integer targetPageNum, ArrayList<Page> currentPath, Set<Integer> visited, List<List<Page>> allPaths) {

        Boolean foundTarget = false;

        // Mark the current page as visited
        visited.add(currentPage.getPageNumber());
        currentPath.add(currentPage);

        // Check if we've reached the target page. If so, add the current path to the list
        // of all paths
        //System.out.println("Visiting page " + currentPage.getPageNumber() + ", current path: " + getPathString(currentPath));
        if (currentPage.getPageNumber() == targetPageNum) {
            allPaths.add(new ArrayList<>(currentPath));
            foundTarget = true;
        } else {
            currentlyBacktracking = false;
            // Explore each choice from the current page
            for (Page choice : currentPage.getChoices()) {
                if (currentlyBacktracking) {
                    System.out.println("\nBacktracked to page " + currentPage.getPageNumber() + ", now branching to page " + choice.getPageNumber());
                    currentlyBacktracking = false;
                }
                if (!visited.contains(choice.getPageNumber())) {
                    dfs(choice, targetPageNum, currentPath, visited, allPaths);
                }
            }
        }

        // Backtrack: remove the current page from the path and mark it as unvisited
        if (!currentlyBacktracking) {
            System.out.println("Reached end of path: "+ getPathString(currentPath) + (foundTarget ? " ** Target found **" : ""));
            System.out.print("Backtracking: ");
        }
        System.out.print(" " + currentPage.getPageNumber());
        currentPath.remove(currentPath.size() - 1);
        visited.remove(currentPage.getPageNumber());
        currentlyBacktracking = true;

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
