package edu.sdr.dc.hibernate;

import edu.sdr.dc.model.Book;
import edu.sdr.dc.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Slf4j
public class HibernateMainApp {

    public static void main(String[] args) throws InterruptedException {

        Book book = new Book("no name", "author", 2000);

        org.hibernate.SessionFactory sessionFactory = SessionFactoryMaker.getFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.save(book);

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final Session session = sessionFactory.openSession();
        final BookRepository bookRepository = new BookRepository(session.getSession());

        List<Book> bookList = bookRepository.findAll();
        System.out.println("Initial number of books is: " + bookList.size());

        Book book2 = bookRepository.save(new Book("no name", "author2", 2002));

        Book book3 = bookRepository.save(new Book("no name", "author3", 2022));

        bookList = bookRepository.findAll();
        System.out.println("Current number of books is: " + bookList.size());

        Optional<Book> bookFindById = bookRepository.findById(2L);
        System.out.println("found the book: " + bookFindById.get());

        bookRepository.remove(book2);

        bookList = bookRepository.findAll();
        System.out.println("Current number of books is: " + bookList.size());
    }

}
