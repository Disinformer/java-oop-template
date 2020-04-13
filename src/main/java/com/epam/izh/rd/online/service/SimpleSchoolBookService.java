package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {

    private BookRepository<SchoolBook> bookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        SchoolBook schoolBook = null;
        if (book instanceof SchoolBook) {
            schoolBook = (SchoolBook) book;
        } else {
            return false;
        }
        String lastName = schoolBook.getAuthorLastName();
        String name = schoolBook.getAuthorName();
        if (authorService.findByFullName(name, lastName) == null) {
            return false;
        }
        return bookRepository.save(schoolBook);
    }

    @Override
    public Book[] findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return bookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] schoolBooks = bookRepository.findByName(name);
        if (schoolBooks.length == 0) {
            return null;
        }
        String authorName = schoolBooks[0].getAuthorName();
        String authorLastName = schoolBooks[0].getAuthorLastName();
        return  authorService.findByFullName(authorName, authorLastName);
    }
}
