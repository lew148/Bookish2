package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.databaseModels.Copy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    private SubService service = new SubService();
    private AuthorToBookService authorToBookService = new AuthorToBookService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

    public void addBookToLibrary(Book book){

        ArrayList<Book> books = service.getAll(Book.class, "books");

        for(Book i: books){
            if(book.getTitle().equals(i.getTitle())){
                System.out.println("Name match");
                Copy copy = new Copy();
                copy.setBookID(book.getId());
            }
        }
        book.setCheckedOut(false);
        service.addBook(book);
        books = service.getAll(Book.class, "books");
        book.setId(books.get(books.size()-1).getId());
        return book;

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

    public ArrayList<Author> getAuthors(Book book){
        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");
        ArrayList<Author> authorsList = service.getAll(Author.class, "authors");
        for(AuthorToBook i : authortobook){
            if(i.getBookID() == book.getId()){
                for(Author j: authorsList){
                    if(j.getId() == i.getAuthorID()){
                        authors.add(j);
                    }
                }
            }
        }
        return authors;
    }

    public void addAuthorToBook(Book book, Author author){
        authorToBookService.addAuthorToBook(book, author);

    }
}
