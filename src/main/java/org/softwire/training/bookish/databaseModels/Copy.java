package org.softwire.training.bookish.databaseModels;

import java.util.ArrayList;
import java.util.List;

public class Copy {
    private int id;
    private int bookID;

    ArrayList<Copy> copies = new ArrayList<>();



    public void Copy(int id) {
        this.setBookID(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
