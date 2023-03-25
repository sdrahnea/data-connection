package edu.sdr.dc.model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class EntityInfo {

    private Date createdDate;
    private String createdBy;
    private Date lastUpdatedDate;
    private String lastUpdatedBy;

}
