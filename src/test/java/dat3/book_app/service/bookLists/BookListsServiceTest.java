package dat3.book_app.service.bookLists;

import dat3.book_app.dto.bookLists.request.BookListCreateRequest;
import dat3.book_app.dto.bookLists.request.BookListEditRequest;
import dat3.book_app.dto.bookLists.request.BookListUpdateRequest;
import dat3.book_app.dto.bookLists.response.*;
import dat3.book_app.entity.Member;
import dat3.book_app.entity.bookLists.Booklist;
import dat3.book_app.repository.BooklistRepository;
import dat3.book_app.service.googleBooks.IGoogleBooksApi;
import dat3.security.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

    private List<BookListBookRefResponse> mockList1 = new ArrayList<>();

    private Member member1;
    private Member member2;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String encodedPassword = encoder.encode("test12");

    @BeforeEach
    void setUp() {

        String bookId1 = "RJxWIQOvoZUC";
        String bookId2 = "_ojXNuzgHRcC";
        String bookId3 = "zaRoX10_UsMC";

        // BookList 1
        booklist1.setId("MyBookList1");
        booklist1.setTitle("MyBookList1");
        booklist1.setBookReferences(List.of(bookId1));
        mockList1.add(new BookListBookRefResponse("RJxWIQOvoZUC"));
        // BookList 2
        booklist2.setId("MyBookList2");
        booklist2.setTitle("MyBookList2");
        booklist2.setBookReferences(List.of(bookId2, bookId3));
        // BookList 3
        booklist3.setId("MyBookList3");
        booklist3.setTitle("MyBookList3");
        booklist3.setBookReferences(List.of(bookId1, bookId2, bookId3));


        member1 = new Member("user1", encodedPassword, "user1.&a.dk");
        member2 = new Member("user2", encodedPassword, "user2.&a.dk");

        member1.setBooklists(Arrays.asList(booklist1, booklist2));
        member2.setBooklists(Arrays.asList(booklist3));

        bookListsService = new BookListsService(bookListRepository, googleBooksApi, memberRepository);
    }


    @Test
    void getBookListWithReferences() {
        when(bookListRepository.findByMember_UsernameLike("user1")).thenReturn(List.of(booklist1));

        assertEquals(1, bookListsService.getBookListWithReferences(member1.getUsername()).size());
        assertEquals("MyBookList1", bookListsService.getBookListWithReferences("user1").get(0).getTitle());
        assertEquals(1, bookListsService.getBookListWithReferences(member1.getUsername()).get(0).getListCount());

        when(bookListRepository.findByMember_UsernameLike("user2")).thenReturn(List.of(booklist2));
        assertEquals(1, bookListsService.getBookListWithReferences(member2.getUsername()).size());
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
        var inputId = booklist1.getId();
        when(bookListRepository.findById(inputId)).thenReturn(java.util.Optional.ofNullable(booklist1));
        when(googleBooksApi.getBooksByReferences(booklist1.getBookReferences())).thenReturn(mockList1);
        var expectedBookTitles = booklist1.getBookReferences();
        var actualBookTitles = bookListsService.getBookListWithBooks("MyBookList1");
        var actualReferences = actualBookTitles.getBooks().stream().map(BookListBookRefResponse::getId).toList();
        assertEquals(expectedBookTitles, actualReferences);
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
        request.setUsername("user1");

        when(bookListRepository.findById(bookListId)).thenReturn(Optional.of(bookList));
        when(bookListRepository.save(bookList)).thenReturn(bookList);

        // Act
        var response = bookListsService.addToBookList(request);

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
        // Arrange
        Booklist booklist = new Booklist();
        booklist.setTitle("MyBookList4");
        booklist.setId("bookList123");

        when(memberRepository.findByUsernameLike(member1.getUsername())).thenReturn(Optional.ofNullable(member1));
        when(bookListRepository.saveAndFlush(any(Booklist.class))).thenReturn(booklist);
        when(bookListRepository.count()).thenReturn((long)2);

        // Act
        BookListCreateRequest request = new BookListCreateRequest();
        request.setTitle("MyBookList4");
        var bk = request.toBookList(member1);
        bk.setId("abcd");

        System.out.println(request.getTitle());

        BookListCreateResponse response = bookListsService.createBookList(request, member1.getUsername());

        System.out.println(member1.getUsername());
        System.out.println(response.isStatus());
        System.out.println(response.getMessage());

        // Assert
        assertTrue(response.isStatus());
        assertEquals(2, response.getListCount());
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
    void deleteBooklist() {
        final Booklist[] booklist = {new Booklist()};
        booklist[0].setId("MyBookList5");
        booklist[0].setTitle("MyBookList5");
        booklist[0].setMember(member1);

        when(bookListRepository.findById("MyBookList5")).thenReturn(Optional.ofNullable(booklist[0]));
        var response = bookListsService.deleteBookList("MyBookList5");

        assertTrue(response.isStatus());
    }

    @Test
    void editBookList() {
        // Arrange
        var editRequest = new BookListEditRequest();
        editRequest.setBookListId("booklist1");
        editRequest.setTitle("MyBookList1");

        System.out.println(editRequest.getBookListId());
        System.out.println(editRequest.getTitle());

        Booklist editedBookList = new Booklist();
        editedBookList.setId(editRequest.getBookListId());
        editedBookList.setTitle("MyBookList2");
        editedBookList.setMember(member1);
        System.out.println(editedBookList.getId());
        System.out.println(editedBookList.getTitle());
        System.out.println(editedBookList.getMember().getUsername());

        when(bookListRepository.findById("booklist1")).thenReturn(Optional.of(booklist1));
        when(bookListRepository.save(any(Booklist.class))).thenReturn(editedBookList);

        // Act
        BookListUpdateResponse response = bookListsService.editBookList(editRequest);

        // Assert
        assertEquals("OK", response.getMessage());
        assertEquals(true, response.isStatus());


    }
}