package dat3.book_app.service.books;

public interface IBookListUpdate<TRequest> {
    boolean Update(TRequest request);
}
