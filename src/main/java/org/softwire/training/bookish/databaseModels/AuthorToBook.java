package org.softwire.training.bookish.databaseModels;

public class AuthorToBook {
    private int idAuthorToBook;
    private int bookID;
    private int authorID;

    public int getIdAuthorToBook() {
        return idAuthorToBook;
    }

    public void setIdAuthorToBook(int idAuthorToBook) {
        this.idAuthorToBook = idAuthorToBook;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}
