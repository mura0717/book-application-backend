package dat3.book_app.service.googleBooks.subjectFactory;

import dat3.book_app.dto.googleBooksApi.BookDetailsResponse;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.ArrayList;

public class BooksTestResponses {
    public BookDetailsResponse fateFulTriangleByNoamChomsky(){
        var response = new BookDetailsResponse();
        response.setReference("aHphMCIkhK0C");
        response.setTitle("Fateful Triangle");
        response.setAuthors(new ArrayList<>(){{
            add("Noam Chomsky");
        }});
        response.setDescription("Since its original publication in 1983, Fateful Triangle has become a classic in the fields of political science and Middle East affairs. This new edition features new chapters and a new introduction by Noam Chomsky and a foreword by Edward Said.Examining Americaâ__s search for a â__reliable allyâ__ in the Middle East, Chomsky untangles the intricacies of the US-Israeli-Palestinian relationship and lays bare the contortions, lies and misinformation that have been used over the years to obscure the real agenda. In the process he reveals the extent to which modern nation-states make claims for peace while actively pursuing very different objectives. In three new chapters Chomsky examines the Palestinian Uprising, the 'Limited War' in Lebanon and the Israeli-PLO Accords after the Oslo signings. This is a timely and much-needed corrective to the mythmaking that has obscured the real history of peace negotiations in the Middle East.");
        response.setBuyLink(null);
        response.setImage("http://books.google.com/books/content?id=aHphMCIkhK0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api");
        return response;
    }

    public BookDetailsResponse responseSubject2(){
        throw new NotYetImplementedException();
    }

    public BookDetailsResponse responseSubject3(){
        throw new NotYetImplementedException();
    }
}
