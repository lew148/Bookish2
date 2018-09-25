package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Account;
import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;

@Service
public class AccountService {
    private SubService service = new SubService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addAccount(Account account){

        ArrayList<Account> accountList = service.getAll(Account.class, "accounts");

        for(Account i: accountList){
            if (account.getUsername() == i.getUsername()){
                System.out.println("Username already exists!");
            }
        }


        service.addAccount(account);

    }

    public void deleteBookFromLibrary(int id){
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");

        for(AuthorToBook i : authortobook){
            if(i.getBookID() == id) {
                service.delete(i.getId(),"authortobook");
            }
        }

        service.delete(id, "books");
    }
}
