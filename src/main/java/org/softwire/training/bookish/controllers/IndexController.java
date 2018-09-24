package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.databaseModels.Author;
import org.softwire.training.bookish.databaseModels.Book;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.SubService;
import org.softwire.training.bookish.viewModels.BooksPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/books")
    ModelAndView books() {

        List<Book> allBooks = subService.getAll(Book.class, "books");

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.books = allBooks;

        return new ModelAndView("books", "model", booksPageModel);
    }

    @RequestMapping("/books/add")
    RedirectView addBook(@ModelAttribute Book book) {

        bookService.addBookToLibrary(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/books/delete")
    RedirectView deleteBook(@RequestParam Book book) {

        bookService.deleteBookFromLibrary(book);

        return new RedirectView("/books");
    }

}
