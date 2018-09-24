package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class GeneralService {

    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

//    private Jdbi jdbi = Jdbi.create(connectionString);

    public static ArrayList generateList(Class x, String tableName, String connection){
        Jdbi jdbi = Jdbi.create(connection);

        List list = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM " + tableName)
                    .mapToBean(x)
                    .list();
        });

        return (ArrayList)list;
    }
}
