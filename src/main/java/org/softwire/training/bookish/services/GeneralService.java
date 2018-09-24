package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralService {

    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    private Jdbi jdbi = Jdbi.create(connectionString);

    public void jdbcMethod() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString);
        try (Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM books";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");

                System.out.println("Book ID: " + bookId + " has author: '" + author + "' and title: '" + title + "'");
            }
        }
    }

    public void jdbiMethod() {
        Jdbi jdbi = Jdbi.create(connectionString);

        List<Book> books = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );

        for (Book book: books) {
            System.out.println("Book ID: " + book.getIdBooks() + "' and title: '" + book.getBookName() + "'");
        }


    }

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
        add(book, "INSERT INTO books (bookName, checkedOut, isbn, copy) VALUES (:bookName, :checkedOut, :isbn, :copy)");
    }

    public void addAccount(Account account) {
        add(account, "INSERT INTO accounts (firstName, lastName, password, username) VALUES (:firstName, :lastName, :password, :username)");
    }

    public void addAuthor(Author author) {
        add(author, "INSERT INTO authors (authorFirstName, authorLastName) VALUES (:authorFirstName, :authorLastName)");
    }

    public void addLoan(Loan loan) {
        add(loan, "INSERT INTO loans (author, title) VALUES (:author, :title)");
    }

    public void addauthorToBook(AuthorToBook authorToBook) {
        add(authorToBook, "INSERT INTO authortobook (bookID, authorID) VALUES (:bookID, :authorID)");
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
                handle.createUpdate("DELETE FROM"+tableName+"WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }


}
