package edu.sdr.dc.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.*;

@Slf4j
@SpringBootApplication
public class H2Main {

    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String H2_DB_URL = "jdbc:h2:file:./data/db";
    private static final String H2_DB_USER = "sa";
    private static final String H2_DB_PASSWORD = "password";

    public static void main(String[] args) {

        //JdbcRepository.save();

        System.out.println("" + createQuery("name", "AAAA", "district", "CA", "popilation"));

    }

    public static String createQuery(String... arg) {

        String columns = "";
        String values = "";
        if( arg.length > 0) {
            for(int index = 0; index < arg.length - 1; index += 2){
                columns += arg[index] + ", ";
                values += "'"  + arg[index + 1] + "'" + ", ";
            }
        }

        columns = columns.substring(0, columns.length() - 2);
        values = values.substring(0, values.length() - 2);

        String query = "INSERT INTO table(" + columns + ") VALUES(" + values + ")";
        return query;
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

    private static void drop(Connection connection) {
        try (Statement dropStatement = connection.createStatement()) {
            dropStatement.execute("DROP TABLE IF EXISTS employee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void create(Connection connection) {
        try (Statement createTableStatement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS employee (" +
                    "id INTEGER AUTO_INCREMENT, " +
                    "first_name VARCHAR(255), " +
                    "last_name VARCHAR(255), " +
                    "job_title VARCHAR(255), " +
                    "department VARCHAR(255), " +
                    "hire_year INTEGER, " +
                    "PRIMARY KEY (id))";
            createTableStatement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Connection connection) {
        try (Statement insertItemStatement = connection.createStatement();) {
            String insertStarWarsQuery = "INSERT INTO employee (first_name, last_name, job_title, department, hire_year) " +
                    "VALUES ('John', 'King', 'ceo', 'ADMIN', 2000)";
            String insertHarryPotterQuery = "INSERT INTO employee (first_name, last_name, job_title, department, hire_year) " +
                    "VALUES ('Matt', 'Green', 'developer', 'IT', 2001)";
            String insertRockyQuery = "INSERT INTO employee (first_name, last_name, job_title, department, hire_year) " +
                    "VALUES ('Mary', 'Red', 'tester', 'IT', 2002)";
            insertItemStatement.execute(insertStarWarsQuery);
            insertItemStatement.execute(insertHarryPotterQuery);
            insertItemStatement.execute(insertRockyQuery);
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