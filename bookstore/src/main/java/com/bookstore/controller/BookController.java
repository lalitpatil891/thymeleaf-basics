package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		
		List<Book> booklist = bookService.getAllBooks();
		//ModelAndView m = new ModelAndView();
		//m.setViewName("booklist");
		//m.addObject("book", booklist)
		return new ModelAndView("bookList","book", booklist ); 
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book) {
		  System.out.println("Book Saved: " + book.getName());
		bookService.saveBook(book);
		return "redirect:/available_books";
	}
	
	/*@PostMapping("/save")
	public String saveBook(@ModelAttribute Book book, Model model) {
	    // save logic
	    bookService.save(book);
	    model.addAttribute("message", "Book added successfully: " + book.getName());
	    return "redirect:/available_books";
	}*/
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		
		Book b = bookService.getBookById(id);
		
		MyBookList mb = new MyBookList(b.getId(),b.getName(), b.getAuthor(),b.getPrice());
				
		myBookService.saveMyBooks(mb);
		 
		return "redirect:/my_books";
	}
	
	
	@GetMapping("/editBook/{id}")
	public ModelAndView editBook(@PathVariable("id") int id) {
	    Book book = bookService.getBookById(id);  // fetch from DB
	    return new ModelAndView("edit-book", "book", book);
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		bookService.deleteBook(id);
		return "redirect:/available_books";
	}
	
	//Search feature
    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> result = bookService.searchBooksByStartingLetter(query);
        model.addAttribute("book", result);
        return "bookList";
    }

	
}
