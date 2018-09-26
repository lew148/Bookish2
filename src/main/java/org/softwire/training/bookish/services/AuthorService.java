package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorService {
    private SubService service = new SubService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public Author addAuthor(String firstName, String lastName) {
        ArrayList<Author> authorList = service.getAll(Author.class, "author");

        Author author = null;

        for (Author i : authorList) {
            if (i.getAuthorFirstName().equals(firstName) && i.getAuthorLastName().equals(lastName)) {
                author = i;
            }
        }
        if (author == null) {
            author = new Author();
            author.setAuthorFirstName(firstName);
            author.setAuthorLastName(lastName);
            service.addAuthor(author);
        }

        return author;
    }
}
