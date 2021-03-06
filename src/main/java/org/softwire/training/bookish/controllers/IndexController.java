package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.databaseModels.Account;
import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.databaseModels.Copy;
import org.softwire.training.bookish.services.AuthorService;
import org.softwire.training.bookish.services.AuthorToBookService;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CopyService;
import org.softwire.training.bookish.services.SubService;
import org.softwire.training.bookish.viewModels.AccountsPageModel;
import org.softwire.training.bookish.viewModels.AuthorsPageModel;
import org.softwire.training.bookish.viewModels.BooksPageModel;
import org.softwire.training.bookish.viewModels.CopiesPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the index page
 */
@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @Autowired
    private SubService subService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorToBookService authorToBookService;

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }





    @RequestMapping("/accounts")
    ModelAndView accounts() {

        List<Account> allAccounts = subService.getAll(Account.class, "accounts");

        AccountsPageModel accountsPageModel = new AccountsPageModel();
        accountsPageModel.accounts = allAccounts;

        return new ModelAndView("accounts", "model", accountsPageModel);

    }





    @RequestMapping("/books")
    ModelAndView books() {
        List<Book> allBooks = subService.getAll(Book.class, "books");

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.books = allBooks;
        for(Book i: booksPageModel.books){
            i.setAuthors(bookService.getAuthors(i));
        }


        return new ModelAndView("books", "model", booksPageModel);

    }

    @RequestMapping("/books/add")
    RedirectView addBook(@ModelAttribute Book book) {

        Book x = bookService.addBookToLibrary(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/books/delete")
    RedirectView deleteBook(@RequestParam int id) {

        bookService.deleteBookFromLibrary(id);

        return new RedirectView("/books");
    }






    @RequestMapping("/copies")
    ModelAndView copies() {
        List<Copy> allCopies = subService.getAll(Copy.class, "copies");

        CopiesPageModel copiesPageModel = new CopiesPageModel();
        copiesPageModel.copies = allCopies;

        return new ModelAndView("copies", "model", copiesPageModel);

    }


    @RequestMapping("/copies/add")
    RedirectView addCopy(@ModelAttribute Copy copy) {
        try {
            List<Book> allBooks = subService.getAll(Book.class, "books");

            for (Book i : allBooks) {
                if (i.getId() == copy.getBookID()) {
                    copyService.addCopy(copy);
                }
            }
        } catch (Exception e) {}
        return new RedirectView("/copies");
    }

    @RequestMapping("/copies/delete")
    RedirectView deleteCopy(@RequestParam int id) {

        copyService.deleteCopy(id);

        return new RedirectView("/copies");
    }

    @RequestMapping("/authors")
    ModelAndView authors() {
        List<Author> allAuthors = subService.getAll(Author.class, "authors");

        AuthorsPageModel authorsPageModel = new AuthorsPageModel();
        authorsPageModel.authors = allAuthors;

        return new ModelAndView("authors", "model", authorsPageModel);

    }

    @RequestMapping("/authors/add")
    RedirectView addAuthor(@RequestParam String firstName, String lastName) {

        authorService.addAuthor(firstName, lastName);

        return new RedirectView("/authors");
    }

    @RequestMapping("/authors/delete")
    RedirectView deleteAuthor(@RequestParam int id) {

        authorService.deleteAuthor(id);

        return new RedirectView("/authors");
    }
}
