package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.services.GeneralService;

import java.sql.*;
import java.util.List;

public class Main {
    private static String hostname = "localhost";
    private static String database = "bookish";
    private static String user = "bookish";
    private static String password = "bookish";
    private static String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";

    public static void main(String[] args) throws SQLException {

        GeneralService service = new GeneralService();

        System.out.println("JDBC method...");
        service.jdbcMethod();

        System.out.println("\nJDBI method...");
        service.jdbiMethod();
    }



}
