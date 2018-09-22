package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.Book;

import java.sql.*;
import java.util.List;

public class Main {
    private static String hostname = "localhost";
    private static String database = "bookish";
    private static String user = "bookish";
    private static String password = "bookish";
    private static String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    public static void main(String[] args) throws SQLException {
        System.out.println("JDBC method...");
        jdbcMethod();

        System.out.println("\nJDBI method...");
        jdbiMethod();
    }

    private static void jdbcMethod() throws SQLException {
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

    private static void jdbiMethod() {
        Jdbi jdbi = Jdbi.create(connectionString);

        List<Book> books = jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM books")
                .mapToBean(Book.class)
                .list()
        );

        for (Book book: books) {
            System.out.println("Book ID: " + book.getId() + " has author: '" + book.getAuthor() + "' and title: '" + book.getTitle() + "'");
        }
    }
}
