package edu.sdr.dc.service;

import edu.sdr.dc.model.Rate;
import edu.sdr.dc.repository.RateRepository;

import java.sql.Connection;
import java.util.List;

public class RateService {

    public List<Rate> getAllRateForDate(Connection connection, String date) {
        RateRepository rateRepository = new RateRepository();

        return rateRepository.findAllByDate(connection, date);
    }

    public String getRatesByDateAsXmlType(Connection connection, String date) {
        RateRepository rateRepository = new RateRepository();
        List<Rate> rateList = rateRepository.findAllByDate(connection, date);

        String xmlFormat = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<DataSet xmlns=\"http://www.bnr.ro/xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.bnr.ro/xsd nbrfxrates.xsd\">\n" +
                "\t<Header>\n" +
                "\t\t<Publisher>National Bank of Romania</Publisher>\n" +
                "\t\t<PublishingDate>" + date + "</PublishingDate>\n" +
                "\t\t<MessageType>DR</MessageType>\n" +
                "\t</Header>\n" +
                "\t<Body>\n" +
                "\t\t<Subject>Reference rates</Subject>\n" +
                "\t\t<OrigCurrency>RON</OrigCurrency>\n" +
                "\t\t<Cube date=\"" + date + "\">\n";

        for(Rate rate : rateList) {
            xmlFormat +="\t\t\t<Rate currency=\"" + rate.getCurrency().getName() + "\" multiplier=\"" + rate.getMultiplier() + "\">" + rate.getRate() + "</Rate>\n";
        }

        xmlFormat += "\t\t</Cube>\n" +
                "\t</Body>\n" +
                "</DataSet>";

        return xmlFormat;
    }

}
