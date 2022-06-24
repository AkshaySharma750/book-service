package com.mycompany.bookservice.service;

import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.entity.BookEntity;
import com.mycompany.bookservice.repository.BookRepository;
import com.mycompany.bookservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void addBookTest(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(1L);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        BookDTO dto= bookService.addBook(bookDTO);
        Assertions.assertEquals(1L,dto.getBookId());


    }
    @Test
    void deleteBookTest(){
        //Mockito.when(bookRepository.deleteById(Mockito.any())).thenReturn("hello");
    }

    @Test
    void updateBookPriceTest(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setBookId(1L);
        BookDTO bookDTO=new BookDTO();
        bookDTO.setPricePerQty(100.0);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(bookEntity));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(bookEntity);
        bookDTO=bookService.updateBookPrice(bookDTO,1L);
        Assertions.assertEquals(1L,bookDTO.getBookId());

    }

    @Test
    void getAllBookTest(){
        List<BookDTO> bookDTOList=new ArrayList<>();
        BookDTO bookDtO=new BookDTO();
        bookDTOList.add(bookDtO);
        BookEntity be=new BookEntity();
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(be));
        bookDTOList=bookService.getAllBooks();
        Assertions.assertEquals(1,bookDTOList.size());

    }
    @Test
    void getBookTest(){
        BookEntity be=new BookEntity();
        be.setBookId(1L);
        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(be));
        BookDTO bookDTO=bookService.getBook(1L);
        Assertions.assertEquals(1L,bookDTO.getBookId());
    }


    @Test
    void updateBookTest(){
        Long bookId=1L;
        BookEntity be=new BookEntity();
        BookDTO bookDTO=new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setName("Harry");
        bookDTO.setAvailableQty(23.4);
        bookDTO.setPricePerQty(23.3);
        bookDTO.setAuthorEmail("aa");
        bookDTO.setAuthorName("aa");
        bookDTO.setDescription("hjlk");
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(be));
        Mockito.when(bookRepository.save(be)).thenReturn(be);
        bookDTO=bookService.updateBook(bookDTO,bookId);
        Assertions.assertEquals("Harry",bookDTO.getName());


    }

}
