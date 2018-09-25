package org.softwire.training.bookish.viewModels;

import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.services.BookService;

import java.util.List;

public class BooksPageModel {
    public List<Book> books;

    public static String getAuthors(Book x){
        BookService bs = new BookService();
        return bs.getAuthors(x);
    }
}
