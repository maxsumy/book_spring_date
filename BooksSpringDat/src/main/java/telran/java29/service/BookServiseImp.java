package telran.java29.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.java29.book.model.Author;
import telran.java29.book.model.Book;
import telran.java29.book.model.Publisher;
import telran.java29.dao.AuthorRepository;
import telran.java29.dao.BookRepository;
import telran.java29.dao.PublisherRepository;
import telran.java29.dto.AuthorDto;
import telran.java29.dto.BookDto;

@Service
public class BookServiseImp implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {

		Set<AuthorDto> authors = bookDto.getAuthors();
		if(authors == null) {
			return false;
		}
		
		
		Author author;
		for (AuthorDto authorDto : authors) {
			if(!authorRepository.existsById(authorDto.getName())) {
				author = new Author(authorDto.getName(), LocalDate.parse(authorDto.getBirtDate()));
				authorRepository.save(author);
			}
			
		}
//		
//		Set<Author> authorsForBook = new HashSet<Author>();
//		for (AuthorDto author1 : authors) {
//			authorsForBook.add(authorRepository.findById(author1.getName()).get());
//			
//		}
		
		
		
		if(bookDto.getPublisher() == null) {
			return false;
		}
		
		Publisher publisher;
		if(!publisherRepository.existsById(bookDto.getPublisher())) {
			publisher = new Publisher(bookDto.getPublisher());
			publisherRepository.save(publisher);
		}
		
		Book book = convertToBook(bookDto);
		bookRepository.save(book);		
		
		
		return true;
	}

	private Book convertToBook(BookDto bookDto) {
		
		return Book.builder()
				.isbn(bookDto.getIsbn())
				.title(bookDto.getTitle())
				.authors(ADtoToAuthor(bookDto.getAuthors()))
				.publisher(publisherRepository.findById(bookDto.getPublisher()).get())
				.build();
	}
	
	private Set<Author> ADtoToAuthor(Set<AuthorDto> authors){
		Set<Author> authorsForBook = new HashSet<Author>();
		for (AuthorDto author1 : authors) {
			authorsForBook.add(authorRepository.findById(author1.getName()).get());
			
		}
		
		return authorsForBook;
		
	}

}
