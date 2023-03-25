package edu.sdr.dc.repository;

import edu.sdr.dc.model.Currency;
import edu.sdr.dc.model.Rate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RateSqlRepository implements JdbcRepository<Rate> {

    public List<Rate> findAllByDate(Connection connection, String date) {
        List<Rate> rateList = new LinkedList<>();

        try (Statement readItemsStatement = connection.createStatement()) {
            String readItemsQuery = "SELECT id, currency, rate, date, multiplier " +
                                    " FROM rate " +
                                    " WHERE date = '" + date + "' ";
            ResultSet rs = readItemsStatement.executeQuery(readItemsQuery);
            while (rs.next()) {
                int id = rs.getInt("id");
                String currency = rs.getString("currency");
                String rate = rs.getString("rate");
                String dateFromTable = rs.getString("date");
                Integer multiplier = rs.getInt("multiplier");

                rateList.add(new Rate(id, dateFromTable, new Currency(), rate, multiplier));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rateList;
    }

    @Override
    public Rate findById(Integer id) {
        return null;
    }

    @Override
    public Rate updateById(Integer id, String rate, String date) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public Rate save(Integer id, String currency, String rate, String date, Integer multiplier) {
        return null;
    }

    @Override
    public void createTable() {

    }
}
