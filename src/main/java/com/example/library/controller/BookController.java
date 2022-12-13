package com.example.library.controller;

import com.example.library.domain.BookDto;
import com.example.library.exception.ObjectNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.CopyMapper;
import com.example.library.service.BookService;
import com.example.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    @Autowired
    private BookService dbServiceBook;
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CopyService dbServiceCopy;

    @Autowired
    private CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbServiceBook.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws ObjectNotFoundException {
        return bookMapper.mapToBookDto(dbServiceBook.getBook(bookId).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookTitle")
    public BookDto getBookTitle(@RequestParam String bookTitle) throws ObjectNotFoundException {
        return bookMapper.mapToBookDto(dbServiceBook.getBookTitle(bookTitle).orElseThrow(ObjectNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
        dbServiceBook.deleteBook(bookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(dbServiceBook.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        dbServiceBook.saveBook(bookMapper.mapToBook(bookDto));
    }
}