package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Loan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LoanService {
    private SubService service = new SubService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addLoan(int ownerID, int bookID, Date dateOfLoan, Date dateOfExpectedReturn){
        ArrayList<Loan> loans = service.getAll(Loan.class, "loans");

        Loan loan = null;
        for(Loan i: loans){
            if(i.getOwnerID() == ownerID && i.getBookID() == bookID && i.getDateOfLoan() == dateOfLoan && i.getDateOfExpectedReturn() == dateOfExpectedReturn){
                loan = i;
                break;
            }
        }
        if(loan == null){
            loan = new Loan();
            loan.setOwnerID(ownerID);
            loan.setBookID(bookID);
            loan.setDateOfLoan(dateOfLoan);
            loan.setDateOfExpectedReturn(dateOfExpectedReturn);
            service.addLoan(loan);
        }

    }

    public void deleteLoan(int id){
        service.delete(id, "loans");
    }
}
