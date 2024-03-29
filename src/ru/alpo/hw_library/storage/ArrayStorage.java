package ru.alpo.hw_library.storage;

import ru.alpo.hw_library.author.domain.*;
import ru.alpo.hw_library.book.domain.*;
import ru.alpo.hw_library.common.utils.*;

import java.util.*;

public final class ArrayStorage {
    private static final int CAPACITY = 10;

    private static Book[] books = new Book[CAPACITY];
    private static int bookIndex = 0;

    private static Author[] authors = new Author[CAPACITY];
    private static int authorIndex = 0;

    private ArrayStorage() {
    }

    //-----------Books---------------------------------------------------------

    public static Book[] getBooks() {
        return books;
    }

    public static int getTotalBooksQuantity() {
        return bookIndex;
    }

    public static void addBook(Book book) {
        book.setId(IdGenerator.generateId());

        if (bookIndex % (CAPACITY) == 0 && authorIndex != 0) {
            increaseBooksArrayStorage();
        }
        books[bookIndex] = book;
        bookIndex++;
    }

    public static void increaseBooksArrayStorage() {
        Book[] books = new Book[bookIndex + CAPACITY];
        ArrayUtils.copyElements(ArrayStorage.books, books);
        ArrayStorage.books = books;
    }

    public static void removeBook(Book book) {
        /**
         *  [0] = A
         *  [1] = B
         *  [2] = C
         *  [3] = NULL
         */
        for (int i = 0; i < books.length; i++) {

            if (book.getId().equals(books[i].getId())) {
                books[i] = null;
                bookIndex--;
                break;
            }
        }

        /**
         *  [0] = A
         *  [1] = NULL
         *  [2] = C
         *  [3] = NULL
         */

        Book[] newBooks = new Book[books.length];
        ArrayUtils.copyNotNullElements(books, newBooks);

        /**
         *  [0] = A
         *  [1] = C
         *  [2] = NULL
         *  [3] = NULL
         */
        books = newBooks;
    }

    //-----------Authors---------------------------------------------------------

    public static Author[] getAuthors() {
        return authors;
    }

    public static int getTotalAuthorsQuantity() {
        return authorIndex;
    }

    public static void addAuthor(Author author) {
        author.setId(IdGenerator.generateId());

        if (authorIndex % (CAPACITY) == 0 && authorIndex != 0) {
            increaseAuthorsArrayStorage();
        }
        authors[authorIndex] = author;
        authorIndex++;
    }

    public static void increaseAuthorsArrayStorage() {
        Author[] authors = new Author[authorIndex + CAPACITY];
        ArrayUtils.copyElements(ArrayStorage.authors, authors);
        ArrayStorage.authors = authors;
    }

    public static void removeAuthor(Author author) {
        /**
         *  [0] = A
         *  [1] = B
         *  [2] = C
         *  [3] = NULL
         */
        for (int i = 0; i < authors.length; i++) {

            if (author.getId().equals(authors[i].getId())) {
                authors[i] = null;
                authorIndex--;
                break;
            }

        }
        /**
         *  [0] = A
         *  [1] = NULL
         *  [2] = C
         *  [3] = NULL
         */

        Author[] newAuthors = new Author[ArrayStorage.authors.length];
        ArrayUtils.copyNotNullElements(authors, newAuthors);

        /**
         *  [0] = A
         *  [1] = C
         *  [2] = NULL
         *  [3] = NULL
         */
        authors = newAuthors;
    }

    public static void searchAuthorByLastName(String searchLastName) {
        for (Author author : authors) {
            if (searchLastName.equals(author.getLastName())) {
                System.out.println(author.toString());
            } else System.out.println("The author " + searchLastName + " not found.");
        }
    }

    public static void sortAuthorsByLastName() {
        for (int i = authors.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++) {
                String[] lastNamesForSort = {authors[i].getLastName(),
                        authors[i + 1].getLastName()};
                Arrays.sort(lastNamesForSort);
            }
    }

}