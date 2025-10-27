package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.service.MyBookListService;

@Controller
public class MyBookListController {
	
	@Autowired
	public MyBookListService myBookService;
	
	@GetMapping("/deleteMyBook/{id}")
	public String deleteMyBook(@PathVariable("id") int id) {
 
		myBookService.deleteMyBook(id);	
		return "redirect:/my_books";
        
    }
}
