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
    private SubService service = new SubService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addBookToLibrary(Book book){
        service.addBook(book);

        ArrayList<Book> books = service.getAll(Book.class, "books");
        int highestCopyNo = 0;

        for(Book i: books){
            if(i.getCopyNo() > highestCopyNo){
                highestCopyNo = i.getCopyNo();
            }
        }

        book.setCopyNo(highestCopyNo+1);
        book.setCheckedOut(false);

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

    public void addAuthorToBook(Book book, Author author){
        service.addAuthor(author);
        AuthorToBook authorToBook = new AuthorToBook();
        authorToBook.setAuthorID(author.getIdAuthor());
        authorToBook.setBookID(book.getIdBooks());
        service.addauthorToBook(authorToBook);

    }
}
