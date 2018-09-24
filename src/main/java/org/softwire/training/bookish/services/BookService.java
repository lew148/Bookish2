package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private SubService service;
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addBookToLibrary(Book book, Author author){
        service.addBook(book);
        service.addAuthor(author);
        AuthorToBook authorToBook = new AuthorToBook();
        authorToBook.setAuthorID(author.getIdAuthor());
        authorToBook.setBookID(book.getIdBooks());
        service.addauthorToBook(authorToBook);

    }

    public void deleteBookFromLibrary(Book book){
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");

        for(AuthorToBook i : authortobook){
            if(i.getBookID() == book.getIdBooks()){
                service.delete(i.getIdAuthorToBook(),"authortobook");
            }
        }

        service.delete(book.getIdBooks(), "books");


    }
}
