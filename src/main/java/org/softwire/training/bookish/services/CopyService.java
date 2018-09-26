package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.databaseModels.Copy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CopyService {
        private SubService service = new SubService();
        private String hostname = "localhost";
        private String database = "bookish";
        private String user = "bookish";
        private String password = "bookish";
        private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        public void addCopy(Copy copy){

            service.addCopy(copy);
        }

        public void deleteCopy(int id) {
            service.delete(id, "copies");
        }
}
