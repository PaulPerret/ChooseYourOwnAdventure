import java.util.List;

public class Page {
    Integer pageNumber;
    List<Page> choices;

    public Page(Integer pageNumber, List<Page> choices) {
        this.pageNumber = pageNumber;
        this.choices = choices;
    }
    
}
