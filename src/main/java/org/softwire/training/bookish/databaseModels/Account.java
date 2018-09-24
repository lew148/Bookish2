package org.softwire.training.bookish.databaseModels;

public class Account {
    private int idAccounts;
    private String firstName;
    private String lastName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getIdAccounts() {
        return idAccounts;
    }

    public void setIdAccounts(int idAccounts) {
        this.idAccounts = idAccounts;
    }
}
