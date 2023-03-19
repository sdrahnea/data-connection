package edu.sdr.dc.jdbc;

import edu.sdr.dc.model.Currency;
import edu.sdr.dc.model.Rate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class H2Rate {

    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String H2_DB_URL = "jdbc:h2:file:./data/db";
    private static final String H2_DB_USER = "sa";
    private static final String H2_DB_PASSWORD = "password";

    public static void main(String[] args) {
        try {
            useH2();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void useH2() throws ClassNotFoundException {
        useDatabase(H2_DRIVER, H2_DB_URL, H2_DB_USER, H2_DB_PASSWORD);
    }

    private static void useDatabase(String driver, String url, String user, String password) throws ClassNotFoundException {
        Class.forName(driver);

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void createConnection(String driver, String url, String user, String password) throws ClassNotFoundException {
        Class.forName(driver);

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void drop(Connection connection) {
        try (Statement dropStatement = connection.createStatement()) {
            dropStatement.execute("DROP TABLE IF EXISTS rate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void create(Connection connection) {
        try (Statement createTableStatement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS rate (\n" +
                    "                    id INTEGER AUTO_INCREMENT, \n" +
                    "                    currency VARCHAR(255), \n" +
                    "                    date VARCHAR(255),\n" +
                    "                    rate VARCHAR(255), \n" +
                    "                    multiplier INTEGER, \n" +
                    "                    PRIMARY KEY (id));\n";
            createTableStatement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Connection connection) {
        try (Statement insertItemStatement = connection.createStatement();) {

            for(int date = 10; date < 30; date++) {
                List<Rate> rateList = new LinkedList<>();
                String dateString = "2023-03-" + date;
                rateList.add(new Rate(dateString, new Currency("AED"), "1.2599"));
                rateList.add(new Rate(dateString, new Currency("AUD"), "3.0962"));
                rateList.add(new Rate(dateString, new Currency("BGN"), "2.5156"));
                rateList.add(new Rate(dateString, new Currency("BRL"), "0.8845"));
                rateList.add(new Rate(dateString, new Currency("CAD"), "3.3745"));
                rateList.add(new Rate(dateString, new Currency("CHF"), "4.9896"));
                rateList.add(new Rate(dateString, new Currency("CNY"), "0.6713"));
                rateList.add(new Rate(dateString, new Currency("CZK"), "0.2054"));
                rateList.add(new Rate(dateString, new Currency("DKK"), "0.6608"));
                rateList.add(new Rate(dateString, new Currency("EGP"), "0.1502"));
                rateList.add(new Rate(dateString, new Currency("EUR"), "4.9200"));
                rateList.add(new Rate(dateString, new Currency("GBP"), "5.6132"));
                rateList.add(new Rate(dateString, new Currency("HUF"), "1.2444", 100));
                rateList.add(new Rate(dateString, new Currency("INR"), "0.0560"));
                rateList.add(new Rate(dateString, new Currency("JPY"), "3.4738", 100));
                rateList.add(new Rate(dateString, new Currency("KRW"), "0.3537", 100));

                for(Rate rate : rateList) {
                    String insertStatement = "INSERT INTO rate (date, currency, rate, multiplier) " +
                            "VALUES ('" + rate.getDate() + "', " +
                            "'" + rate.getCurrency()+ "', " +
                            "         '" + rate.getRate() + " '," +
                            "          " + rate.getMultiplier() + ")";
                    insertItemStatement.execute(insertStatement);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void update(Connection connection) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE employee SET job_title = ? WHERE id = ?")) {
            updateStatement.setString(1, "CTO");
            updateStatement.setInt(2, 1);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void delete(Connection connection) {
        try (Statement deleteItemStatement = connection.createStatement()) {
            String deleteItemQuery = "DELETE FROM employee WHERE id = 2";
            deleteItemStatement.execute(deleteItemQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void read(Connection connection) {
        try (Statement readItemsStatement = connection.createStatement()) {
            String readItemsQuery = "SELECT id, first_name, last_name, job_title, department, hire_year FROM employee";
            ResultSet rs = readItemsStatement.executeQuery(readItemsQuery);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String jobTitle = rs.getString("job_title");
                String department = rs.getString("department");
                int hireYear = rs.getInt("hire_year");

                System.out.println("#####################");
                System.out.println("Id: " + id);
                System.out.println("First name: " + firstName);
                System.out.println("Last name: " + lastName);
                System.out.println("Job title: " + jobTitle);
                System.out.println("Department: " + department);
                System.out.println("Hire year: " + hireYear);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}