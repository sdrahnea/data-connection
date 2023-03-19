package edu.sdr.dc.model;

import lombok.Getter;

@Getter
public class Currency {

    private Integer id;
    private String name;
    private String description;

    public Currency(){}

    public Currency(String name) {
        this.name = name;
    }
}
