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
                    book.setCopyNo(i.getCopyNo() + 1);
                }
            }
        }

        book.setCheckedOut(false);
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

    public ArrayList<Author> getAuthors(Book book){
        ArrayList<Author> authors = null;
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");
        ArrayList<Author> authorsList = service.getAll(Author.class, "author");
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

    public void addAuthorToBook(Book book, String firstName, String lastName){
        ArrayList<Author> authorList = service.getAll(Author.class, "author");
        ArrayList<AuthorToBook> authortobook= service.getAll(AuthorToBook.class, "authortobook");
        ArrayList<Book> books = service.getAll(Book.class, "books");

        Author author = null;
        AuthorToBook authorToBook = null;

        for(Author i : authorList){
            if(i.getAuthorFirstName().equals(firstName) && i.getAuthorLastName().equals(lastName)){
                author = i;
            }
        }
        if(author == null){
            author = new Author();
            author.setAuthorFirstName(firstName);
            author.setAuthorLastName(lastName);
            service.addAuthor(author);
            ArrayList<Author> x = book.getAuthors();
            x.add(author);
            book.setAuthors(x);
        }

        for(AuthorToBook i : authortobook){
            if(!(i.getAuthorID() == author.getId() && i.getBookID() == book.getId())){
                authorToBook = new AuthorToBook();
                authorToBook.setAuthorID(author.getId());
                authorToBook.setBookID(book.getId());
            }
        }
        if(authorToBook != null) {
            service.addauthorToBook(authorToBook);
        }

        for(Book i: books){
            if(book.getBookName().equals(i.getBookName())){
                i.setAuthors(book.getAuthors());
            }
        }
    }
}
