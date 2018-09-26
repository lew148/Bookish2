package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.databaseModels.Account;
import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.services.AuthorService;
import org.softwire.training.bookish.services.AuthorToBookService;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.SubService;
import org.softwire.training.bookish.viewModels.AccountsPageModel;
import org.softwire.training.bookish.viewModels.BooksPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        Author author = authorService.addAuthor("Bob", "Maddden");
        bookService.addAuthorToBook(x, author);

        return new RedirectView("/books");
    }

    @RequestMapping("/books/delete")
    RedirectView deleteBook(@RequestParam int id) {

        bookService.deleteBookFromLibrary(id);

        return new RedirectView("/books");
    }

}
