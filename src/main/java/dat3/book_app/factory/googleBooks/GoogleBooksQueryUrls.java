package dat3.book_app.factory.googleBooks;

public interface GoogleBooksQueryUrls {
    String queryRandomBooks();
    String queryRandomBooksWithGenre(String genre);
    String queryBook(String author, String title);
    String queryBookByAuthor(String author);
    String queryBookByReference(String reference);
}
