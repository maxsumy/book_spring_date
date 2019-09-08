package telran.java29.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java29.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

}
