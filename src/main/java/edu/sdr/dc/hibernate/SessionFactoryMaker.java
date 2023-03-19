package edu.sdr.dc.hibernate;

import edu.sdr.dc.model.Book;
import org.hibernate.cfg.Configuration;

public class SessionFactoryMaker {
    private static org.hibernate.SessionFactory factory;

    private static void configureFactory()
    {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(Book.class)
                    .configure("h2.hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }

}