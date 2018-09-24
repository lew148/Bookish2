package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.Book;

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
}
