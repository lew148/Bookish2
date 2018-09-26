package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorToBookService {
    private SubService service = new SubService();
    private AuthorService authorService = new AuthorService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addAuthorToBook(Book book, Author author) {
        ArrayList<AuthorToBook> authortobook = service.getAll(AuthorToBook.class, "authortobook");
        ArrayList<Book> books = service.getAll(Book.class, "books");

        AuthorToBook authorToBook = null;

        for (AuthorToBook i : authortobook) {
            if (i.getAuthorID() == author.getId() && i.getBookID() == book.getId()) {
                authorToBook = i;
                break;
            }
        }
        if (authorToBook == null) {
            authorToBook = new AuthorToBook();
            authorToBook.setAuthorID(author.getId());
            authorToBook.setBookID(book.getId());
            service.addauthorToBook(authorToBook);
        }

        book.addAuthors(author);

        for(Book i: books){
            if(book.getBookName().equals(i.getBookName())){
                i.setAuthors(book.getAuthors());
            }
        }
    }
}
