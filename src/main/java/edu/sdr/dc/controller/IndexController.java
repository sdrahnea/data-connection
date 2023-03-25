package edu.sdr.dc.controller;

import edu.sdr.dc.service.RateService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class IndexController {

    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String H2_DB_URL = "jdbc:h2:file:./data/db";
    private static final String H2_DB_USER = "sa";
    private static final String H2_DB_PASSWORD = "password";

    /**
     * Sample of simple end-point
     */
    @GetMapping(value = "/hello")
    public String getRates() {
        return "hello!!!";
    }

    /**
     * Try to reproduce a copy of this link: https://www.bnr.ro/nbrfxrates.xml
     * Open the browser, copy & past http://localhost:8081/nbrfxrates.xml, press enter
     * @return rates
     */
    @GetMapping(value = "/nbrfxrates.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getRatesAsXmlFormat() throws ClassNotFoundException {
        //call a service which will return rates into XML format.

        String xmlFormat = null;
        RateService rateService = new RateService();

        Class.forName(H2_DRIVER);
        try (Connection connection = DriverManager.getConnection(H2_DB_URL, H2_DB_USER, H2_DB_PASSWORD)) {
            xmlFormat = rateService.getRatesByDateAsXmlType(connection,"2023-03-17");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xmlFormat;
    }

}
