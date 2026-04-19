package book;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookBuilder {

    private static class PageData {
        int pageNumber;
        List<Integer> choices;
    }

    public Map<Integer, Page> buildBook(String bookName) {
        Map<Integer, Page> pageMap = new HashMap<>();

        // Open JSON config file
        InputStream is = getClass().getResourceAsStream("/" + bookName + ".json");
        System.out.println("Loading book from: " + (is != null ? bookName + ".json" : "null"));

        Reader reader = new InputStreamReader(is);

        Type listType = new TypeToken<List<PageData>>() {}.getType();
        List<PageData> pageDataList = new Gson().fromJson(reader, listType);

        // Step 1: Add pages with no choices yet to the Map
        for (PageData pageData : pageDataList) {
            Page page = new Page(pageData.pageNumber);
            pageMap.put(page.getPageNumber(), page);
        }

        // Step 2: Now that all pages are in the Map, set the choices
        for (PageData pageData : pageDataList) {
            // Get a reference to the current page in the Map
            Page page = pageMap.get(pageData.pageNumber);

            // For the choices in the pageData, add references to the corresponding Page objects in the Map
            for (Integer choicePageNum : pageData.choices) {
                Page choicePage = pageMap.get(choicePageNum);
                if (choicePage != null) {
                    page.addChoice(choicePage);
                } else {
                    System.err.println("Warning: Page " + choicePageNum + " not found for choice on page " + page.getPageNumber());
                }
            }
        }

        System.out.println("Book loaded successfully with " + pageMap.size() + " pages.");
        // Print out the pages and their choices for verification
        // for (Page page : pageMap.values()) {
        //     System.out.print("Page " + page.getPageNumber() + " has choices: ");
        //     for (Page choice : page.getChoices()) {
        //         System.out.print(choice.getPageNumber() + " ");
        //     }
        //     System.out.println();
        // }   

        return pageMap;
    }
    
}
