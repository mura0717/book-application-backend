package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.BookListBookRefResponse;
import dat3.book_app.dto.bookLists.response.BookListUpdateResponse;
import dat3.book_app.dto.bookLists.response.BookListWithBooks;
import dat3.book_app.dto.bookLists.response.BookListWithReferences;
import dat3.book_app.entity.Member;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.security.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookListsServiceTest {

    @Mock
    BooklistRepository bookListRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    IGoogleBooksApi googleBooksApi;

    @InjectMocks
    BookListsService bookListsService;

    private Booklist booklist1 = new Booklist();
    private Booklist booklist2 = new Booklist();
    private Booklist booklist3 = new Booklist();

    private Member member1;
    private Member member2;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String encodedPassword = encoder.encode("test12");

    @BeforeEach
    void setUp() {

        String bookId1 = "book1";
        String bookId2 = "book2";
        String bookId3 = "book3";
        String bookId4 = "book4";
        String bookId5 = "book5";

        booklist1.setTitle("MyBookList1");
        booklist2.setTitle("MyBookList2");
        booklist3.setTitle("MyBookList3");

        booklist1.setBookReferences(List.of(bookId1, bookId2, bookId3));
        booklist2.setBookReferences(List.of(bookId4, bookId5));
        booklist3.setBookReferences(List.of(bookId1, bookId2, bookId3, bookId4, bookId5));

        member1 = new Member("user1", encodedPassword, "user1.&a.dk");
        member2 = new Member("user2", encodedPassword, "user2.&a.dk");
        member1.setBooklists(Arrays.asList(booklist1, booklist2));
        //member1.getBooklists().add(booklist1);
        //member1.getBooklists().add(booklist2);
        member2.setBooklists(List.of(booklist3));

        bookListsService = new BookListsService(bookListRepository, googleBooksApi, memberRepository);
    }


    @Test
    void getBookListWithReferences() {
        when(bookListRepository.findByMember_UsernameLike("user1")).thenReturn(List.of(booklist1));
        assertEquals(1, bookListsService.getBookListWithReferences("user1").size());
        System.out.println(member1.getBooklists().get(1).getId());
        assertEquals("MyBookList1", bookListsService.getBookListWithReferences("user1").get(0).getTitle());
        assertEquals(3, bookListsService.getBookListWithReferences("user1").get(0).getListCount());
        when(bookListRepository.findByMember_UsernameLike("user2")).thenReturn(List.of(booklist2));
        assertEquals(1, bookListsService.getBookListWithReferences("user2").size());
        assertEquals("MyBookList2", bookListsService.getBookListWithReferences("user2").get(0).getTitle());
        assertEquals(2, bookListsService.getBookListWithReferences("user2").get(0).getListCount());
    }

    @Test
    void getBookListWithTitles() {
        when(bookListRepository.findByMember_UsernameLike("user1")).thenReturn(List.of(booklist1));
        assertEquals(1, bookListsService.getBookListWithTitles("user1").size());
        assertEquals("MyBookList1", bookListsService.getBookListWithTitles("user1").get(0).getTitle());
        when(bookListRepository.findByMember_UsernameLike("user2")).thenReturn(List.of(booklist2));
        assertEquals(1, bookListsService.getBookListWithTitles("user2").size());
        assertEquals("MyBookList2", bookListsService.getBookListWithTitles("user2").get(0).getTitle());
    }

    @Test
    void getBookListWithBooks() {
        when(bookListRepository.findById("booklist1")).thenReturn(java.util.Optional.ofNullable(booklist1));
        List<String> expectedBookTitles = booklist1.getBookReferences();
        List<BookListBookRefResponse> actualBookTitles = bookListsService.getBookListWithBooks("booklist1").getBooks().stream().toList();
        System.out.println(bookListsService.getBookListWithBooks("booklist1").getBooks().size());
        assertEquals(expectedBookTitles, actualBookTitles);
    }

    @Test
    void addToBookList() {

        // Arrange
        String bookListId = "bookList123";
        String bookId = "book123";
        Booklist bookList = new Booklist();

        BookListUpdateRequest request = new BookListUpdateRequest();
        request.setBookId(bookId);
        request.setBookListId(bookListId);

        when(bookListRepository.findById(bookListId)).thenReturn(Optional.of(bookList));
        when(bookListRepository.save(bookList)).thenReturn(bookList);

        // Act
        BookListUpdateResponse response = bookListsService.addToBookList(request);

        // Assert
        assertEquals("Ok", response.getMessage());
        assertEquals(true, response.isStatus());
        assertEquals(1, bookList.getBookReferences().size());
        assertEquals(bookId, bookList.getBookReferences().get(0));
    }


    @Test
    void removeFromBookList() {

        // Arrange
        String bookListId = "bookList123";
        String bookId = "book123";
        Booklist bookList = mock(Booklist.class);
        bookList.setBookReferences(List.of(bookId));

        BookListUpdateRequest request = new BookListUpdateRequest();
        request.setBookId(bookId);
        request.setBookListId(bookListId);

        when(bookList.getBookReferences()).thenReturn(new ArrayList<>(Arrays.asList("bookId1", "bookId2")));
        when(bookListRepository.findById(bookListId)).thenReturn(Optional.of(bookList));
        when(bookListRepository.save(bookList)).thenReturn(bookList);

        // Act
        boolean response = bookListsService.removeFromBookList(request);

        // Assert
        assertTrue(response);
        assertFalse(bookList.getBookReferences().contains("book123"));

    }

    @Test
    void createBookList() {

    }

    @Test
    void bookAlreadyAdded() {
        // Arrange
        String bookListId = "1";
        String bookReference = "978-0451524935";
        var bookReferences = new ArrayList<String>();
        bookReferences.add(bookReference);
        var bookList = new Booklist();
        bookList.setBookReferences(bookReferences);
        when(bookListRepository.findById(bookListId)).thenReturn(Optional.of(bookList));

        // Act
        boolean result = bookListsService.bookAlreadyAdded(bookListId, bookReference);

        // Assert
        assertTrue(result);

    }

    @Test
    void removeBookList() {

    }

    @Test
    void editBookList() {

    }
}