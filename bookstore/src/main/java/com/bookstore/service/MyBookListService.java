package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.MyBookList;
import com.bookstore.repository.MyBookRepository;

@Service
public class MyBookListService {

		@Autowired
		private MyBookRepository myBookRepo;
		
		
		public void saveMyBooks(MyBookList book) {
			myBookRepo.save(book);
		}
		
		public List<MyBookList> getAllMyBooks() {
			return myBookRepo.findAll();
		}
		
		public void deleteMyBook(int id) {
	        myBookRepo.deleteById(id);
	    }
		
}
