package telran.java29.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java29.book.model.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
