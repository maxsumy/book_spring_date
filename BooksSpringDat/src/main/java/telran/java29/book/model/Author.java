package telran.java29.book.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
public class Author {
	@Id
	String name;
	LocalDate birtDay;
	@ManyToMany(mappedBy = "authors")
	Set<Book> books;
	
	public Author(String name, LocalDate birtDay) {
		
		this.name = name;
		this.birtDay = birtDay;
	}

	
}
