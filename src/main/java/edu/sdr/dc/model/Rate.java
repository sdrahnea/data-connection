package edu.sdr.dc.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Rate {

    private Integer id;
    private Currency currency;
    private String rate;
    private String date;
    private Integer multiplier;

    private Date createdDate;
    private String createdBy;
    private Date lastUpdatedDate;
    private String lastUpdatedBy;


    public Rate(){}

    public Rate(String date, Currency currency, String rate, Integer multiplier) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.multiplier = multiplier;
    }

    public Rate(String date, Currency currency, String rate) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.multiplier = 1;
    }

    public Rate(Integer id, String date, Currency currency, String rate, Integer multiplier) {
        this.id = id;
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.multiplier = multiplier;
    }
}
