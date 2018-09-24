package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    private Jdbi jdbi = Jdbi.create(connectionString);


    public void addBook(Book book) {
        jdbi.withHandle(handle ->
            handle.createUpdate("INSERT INTO books (author, title) VALUES (:author, :title)")
                .bindBean(book)
                .execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.withHandle(handle ->
            handle.createUpdate("DELETE FROM books WHERE id = :id")
                .bind("id", bookId)
                .execute()
        );
    }
}
