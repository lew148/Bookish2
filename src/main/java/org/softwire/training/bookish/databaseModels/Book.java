package org.softwire.training.bookish.databaseModels;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private int id;
    private String bookName;
    private boolean checkedOut;
    private String isbn;
    private int copyNo;
    private ArrayList<Author> authors = new ArrayList<>();
    private String authorString = new String();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(int copy) {
        this.copyNo = copy;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
        this.setAuthorString();
    }

    public void addAuthors(Author author) {
        //System.out.println(author.getAuthorFirstName());
        this.authors.add(author);
        this.setAuthorString();
    }

    public void setAuthorString() {
        for(Author x : authors){
            authorString += x.getAuthorFirstName() + " " + x.getAuthorLastName() + ", ";
        }
    }

    public String getAuthorString() {
        return authorString;
    }
}
