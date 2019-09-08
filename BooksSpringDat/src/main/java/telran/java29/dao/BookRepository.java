package telran.java29.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java29.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
