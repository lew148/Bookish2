package org.softwire.training.bookish.services;

import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.AuthorToBook;
import org.softwire.training.bookish.databaseModels.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    private SubService service = new SubService();
    private String hostname = "localhost";
    private String database = "bookish";
    private String user = "bookish";
    private String password = "bookish";
    private String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";


    public void addBookToLibrary(Book book){

        ArrayList<Book> books = service.getAll(Book.class, "books");
        book.setCopyNo(0);

        for(Book i: books){
            if(book.getBookName().equals(i.getBookName())){
                System.out.println("Name match");
                if(book.getCopyNo() <= i.getCopyNo()) {
                    System.out.println("updating copy type");
                    book.setCopyNo(i.getCopyNo() + 1);
                }
            }
        }



        book.setCheckedOut(false);
        System.out.println(book.getCopyNo());
        service.addBook(book);

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

    public String getAuthors(Book book){
        String authors = "";
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");
        ArrayList<Author> authorsList = service.getAll(Author.class, "author");
        for(AuthorToBook i : authortobook){
            if(i.getBookID() == book.getId()){
                for(Author j: authorsList){
                    if(j.getId() == i.getAuthorID()){
                        authors += j.getAuthorFirstName() + " " + j.getAuthorLastName() + "  ";
                    }
                }
            }
        }
        return authors;
    }

    public void addAuthorToBook(Book book, Author author){
        service.addAuthor(author);
        AuthorToBook authorToBook = new AuthorToBook();
        authorToBook.setAuthorID(author.getId());
        authorToBook.setBookID(book.getId());
        service.addauthorToBook(authorToBook);

    }
}
