package edu.sdr.dc.repository;

import edu.sdr.dc.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class BookRepository {

    private final EntityManager entityManager;

    public BookRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> findAll() {
        return entityManager.createQuery("FROM Book ", Book.class).getResultList();
    }

    public Optional<Book> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public List<Book> findAllByAuthor(String author) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList()
                ;
    }

    public Book save(final Book book) {
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(book);
            transaction.commit();

            return book;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void remove(final Book book) {
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.remove(book);
            transaction.commit();

        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}
