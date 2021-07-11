package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRespository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRespository bookRespository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRespository bookRespository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRespository = bookRespository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher mh = new Publisher("McGraw Hill", "1000 Rodeo Drive", "Hollywood", "California", "22222");
        publisherRepository.save(mh);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(mh);
        mh.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRespository.save(ddd);
        publisherRepository.save(mh);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "321654987");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(mh);
        mh.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRespository.save(noEJB);
        publisherRepository.save(mh);


        System.out.println("Number of Books: " + bookRespository.count());
        System.out.println("Publisher Number of Books: " + mh.getBooks().size());


    }
}
