package org.softwire.training.bookish.databaseModels;

import java.util.Date;

public class Loan {
    private int idLoans;
    private int ownerID;
    private int bookID;
    private Date dateOfLoan;
    private Date dateOfExpectedReturn;

    public int getIdLoans() {
        return idLoans;
    }

    public void setIdLoans(int idLoans) {
        this.idLoans = idLoans;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public Date getDateOfLoan() {
        return dateOfLoan;
    }

    public void setDateOfLoan(Date dateOfLoan) {
        this.dateOfLoan = dateOfLoan;
    }

    public Date getDateOfExpectedReturn() {
        return dateOfExpectedReturn;
    }

    public void setDateOfExpectedReturn(Date dateOfExpectedReturn) {
        this.dateOfExpectedReturn = dateOfExpectedReturn;
    }
}
