package book;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private Integer pageNumber;
    private List<Page> choices;

    public Page(Integer pageNumber) {
        this.pageNumber = pageNumber;
        this.choices = new ArrayList<>();
    }

    public Page(Integer pageNumber, List<Page> choices) {
        this.pageNumber = pageNumber;
        this.choices = choices;
    }

    public void addChoice(Page page) {
        this.choices.add(page);
    }
    
    public Integer getPageNumber() {
        return pageNumber;
    }

    public List<Page> getChoices() {
        return choices;
    }
}
