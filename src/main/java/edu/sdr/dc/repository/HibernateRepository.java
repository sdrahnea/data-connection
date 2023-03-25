package edu.sdr.dc.repository;

public interface HibernateRepository<T> {

    // select ...
    T findById(Integer id);

    // update ...
    T updateById(Integer id, String rate, String date);

    // delete ...
    boolean deleteById(Integer id) ;

    // insert ...
    T save(Integer id, String currency, String rate, String date, Integer multiplier);

    void createTable();

}
