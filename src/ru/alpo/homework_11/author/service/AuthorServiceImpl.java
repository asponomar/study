package ru.alpo.homework_11.author.service;

import ru.alpo.homework_11.author.domain.*;
import ru.alpo.homework_11.author.repo.*;
import ru.alpo.homework_11.book.domain.*;
import ru.alpo.homework_11.book.repo.*;
import ru.alpo.homework_11.common.repo.*;
import ru.alpo.homework_11.storage.*;

import java.util.*;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public int count() {
        return authorRepo.count();
    }

    @Override
    public void print() {
        authorRepo.print();
    }

    @Override
    public void delete(Author author) {
        Book[] booksWithAuthor = bookRepo.findAsArray(author.getId());

        if (booksWithAuthor != null) {
            for (Book book : booksWithAuthor) {
                book.deleteAuthor(author);

                if (book.withoutAuthors()) {
                    bookRepo.delete(book);
                }
            }
        }
    }

    @Override
    public Long add(Author author) {
        ArrayStorage.addAuthor(author);
        return author.getId();
    }

    @Override
    public Author getById(Long id) {
        return authorRepo.getById(id);
    }

    @Override
    public Author[] findAuthorByBookAsArray(Long bookId) {
        return authorRepo.findAsArray(bookId);
    }

    @Override
    public List findAuthorByBookAsAsList(Long bookId) {
        List<Author> found = new ArrayList<>();

        for (Author a : CollectionStorage.getAllAuthors()) {
            List<Book> books = a.getBooks();
            for (Book b : books) {
                if (b != null && bookId == b.getId()) {
                    found.add(a);
                    break;
                }
            }
        }
        return found;
    }
}
