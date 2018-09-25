package org.softwire.training.bookish.databaseModels;

public class Book {
    private int id;
    private String bookName;
    private boolean checkedOut;
    private String isbn;
    private int copyNo;

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
}
