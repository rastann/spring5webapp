package com.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.spring5webapp.model.Author;
import com.spring5webapp.model.Book;
import com.spring5webapp.model.Publisher;
import com.spring5webapp.repositories.AuthorRepository;
import com.spring5webapp.repositories.BookRepository;
import com.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        Author john = new Author("Ion", "Creanga");
        Book b1 = new Book("Amintiri din copilarie", "1234", publisher);
        john.getBooks().add(b1);
        b1.getAuthors().add(john);

        authorRepository.save(john);
        bookRepository.save(b1);

        Author rod = new Author("Rod", "Johnosn");
        Book noEJB = new Book("JJ2JEE Developmen withoud EJB", "32445", publisher);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
