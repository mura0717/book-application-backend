package dat3.book_app.service.googleBooks.subjectFactory;

import dat3.book_app.dto.googleBooksApi.BookDetailsResponse;

import java.util.List;

public class BookResponseComparable {
    private final BookDetailsResponse _response;

    public BookResponseComparable(BookDetailsResponse response) {
        _response = response;
    }

    public boolean equals(BookDetailsResponse other){
        if(!_response.getTitle().equals(other.getTitle()))
            return false;
        if(!compareStrings(_response.getAuthors(),other.getAuthors()))
            return false;
        if(!_response.getDescription().equals(other.getDescription()))
            return false;
        return compareBuyLinks(_response.getBuyLink(), other.getBuyLink());
    }

    private boolean compareStrings(List<String> list1, List<String> list2){
        return list1.equals(list2);
    }

    private boolean compareBuyLinks(String link1, String link2){
        var comp1 = link1 != null ? link1 : "null";
        var comp2 = link2 != null ? link2 : "null";
        return comp1.equalsIgnoreCase(comp2);
    }
}
