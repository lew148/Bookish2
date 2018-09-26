package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubService {

    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";

    private Jdbi jdbi = Jdbi.create(connectionString);


    public ArrayList getAll(Class x, String tableName){
        Jdbi jdbi = Jdbi.create(connectionString);

        List list = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM " + tableName)
                    .mapToBean(x)
                    .list();
        });

        return (ArrayList)list;
    }

    public void addBook(Book book) {
        add(book, "INSERT INTO books (title, checkedOut, isbn) VALUES (:title, :checkedOut, :isbn)");
    }

    public void addAccount(Account account) {
        add(account, "INSERT INTO accounts (firstName, lastName, password, username) VALUES (:firstName, :lastName, :password, :username)");
    }

    public void addAuthor(Author author) {
        add(author, "INSERT INTO authors (authorFirstName, authorLastName) VALUES (:authorFirstName, :authorLastName)");
    }

    public void addLoan(Loan loan) {
        add(loan, "INSERT INTO loans (ownerID, bookID, dateOfLoan, dateOfExpectedReturn) VALUES (:ownerID, :bookID, :dateOfLoan, dateOfExpectedReturn)");
    }

    public void addauthorToBook(AuthorToBook authorToBook) {
        add(authorToBook, "INSERT INTO authortobook (bookID, authorID) VALUES (:bookID, :authorID)");
    }

    public void addCopy(Copy copy) {
        add(copy, "INSERT INTO copies (bookID) VALUES (:bookID)");
    }

    private void add(Object o, String sql) {
        jdbi.withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(o)
                        .execute()
        );
    }

    public void delete(int id, String tableName) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM "+tableName+" WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public void update(int id, String tableName, String column, String columnValue){
        jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE "+tableName+" SET "+column+" = "+columnValue+ " WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );

    }


}
