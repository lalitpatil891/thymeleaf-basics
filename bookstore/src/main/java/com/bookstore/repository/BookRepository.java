package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer > {
	
	// Finds books whose name starts with a specific alphabet (case-insensitive)
    List<Book> findByNameStartingWithIgnoreCase(String name);
}
