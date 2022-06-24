package com.mycompany.bookservice.controller;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.service.BookService;
import com.mycompany.bookservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookServiceImpl bookService;

    @Test
    @DisplayName("test scenario for post book success")
    public void createBookTest(){
        BookDTO book=new BookDTO();
        //  book.setBookId(1L);
        book.setName("James");
        BookDTO savedbook=new BookDTO();
        savedbook.setBookId(1L);
        savedbook.setName("James");

        Mockito.when(bookService.addBook(book)).thenReturn(savedbook);
        ResponseEntity<BookDTO> responseEntity=bookController.addBook(book);
        Assertions.assertNotNull(responseEntity.getBody().getBookId());
        Assertions.assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue(),"expecting status as created");
    }

    @Test
    @DisplayName("test scenario to get all books")
    public void getAllBooksTest()
    {
        BookDTO book=new BookDTO();
        List<BookDTO> bookList=new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);
        ResponseEntity<List<BookDTO>> responseEntity=bookController.getAllBooks();
        Assertions.assertEquals(1,responseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());

    }

    @Test
    @DisplayName("test scenario to get a book")
    public void getABookTest()
    {
        BookDTO book=new BookDTO();
        book.setBookId(1L);
        Long bookId=1L;

        Mockito.when(bookService.getBook(bookId)).thenReturn(book);
        ResponseEntity<BookDTO> responseEntity=bookController.getBook(bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1L,responseEntity.getBody().getBookId());

    }

    @Test
    @DisplayName("test scenario to update a book")
    public void updateABookTest()
    {
        BookDTO updatebook=new BookDTO();
        updatebook.setBookId(1L);
        Long bookId=1L;

        Mockito.when(bookService.updateBook(Mockito.any(), Mockito.any())).thenReturn(updatebook);
        ResponseEntity<BookDTO> responseEntity=bookController.updateBook(updatebook, bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1L,responseEntity.getBody().getBookId());

    }
    @Test
    @DisplayName("test scenario to update a book Price")
    public void updateABookPriceTest()
    {
        BookDTO updatebook=new BookDTO();
        updatebook.setBookId(1L);
        updatebook.setPricePerQty(23.86);
        Long bookId=1L;

        Mockito.when(bookService.updateBookPrice(Mockito.any(), Mockito.any())).thenReturn(updatebook);
        ResponseEntity<BookDTO> responseEntity=bookController.updateBookPrice(updatebook, bookId);

        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1L,responseEntity.getBody().getBookId());
        Assertions.assertEquals(23.86,responseEntity.getBody().getPricePerQty());

    }


    @Test
    @DisplayName("test scenario to delete a book ")
    public void deleteBooktest()
    {
        Long bookId=1L;
        //  Mockito.when(bookService.deleteBook(bookId));
        HttpStatus status=bookController.deleteBook(bookId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,status.getClass());
    }

}
