public class Book extends LibraryItem {
    private String author;
    private int pages;

    public Book(String title, String author, int pages) {
        super(title);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public int getLoanPeriodDays() {
        return 21;
    }

    @Override
    public String getType() {
        return "Book";
    }
}