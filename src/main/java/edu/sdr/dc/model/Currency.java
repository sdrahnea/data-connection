package edu.sdr.dc.model;

import lombok.Getter;

import javax.persistence.Embedded;
import java.util.Date;

@Getter
public class Currency {

    private Integer id;
    private String name;
    private String description;

    @Embedded
    private EntityInfo entityInfo;

    public Currency(){}

    public Currency(String name) {
        this.name = name;
    }
}
