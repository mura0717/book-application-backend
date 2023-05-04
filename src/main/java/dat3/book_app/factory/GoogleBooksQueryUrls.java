package dat3.book_app.factory;

public interface GoogleBooksQueryUrls {
    String queryRandomBooks();
    String queryBook(String author, String title);
    String queryBookByAuthor(String author);
    String queryBookByReference(String reference);
}
