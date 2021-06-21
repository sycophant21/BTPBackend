package com.labAutomator.Helpers;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Log {
    @Id
    @GeneratedValue(generator = "sequence-generator2")
    @GenericGenerator(
            name = "sequence-generator2",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "log_sequence"),
                    @Parameter(name = "initial_value", value = "100000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long logId;
    private String ipAddress;
    private String requestType;
    private String requestURL;
    private String requestDetails;
    private MetaData metaData;


    public Log(String ipAddress, String requestType, String requestURL, String requestDetails) {
        this.ipAddress = ipAddress;
        this.requestType = requestType;
        this.requestURL = requestURL;
        this.requestDetails = requestDetails;
        this.metaData = new MetaData();
    }

    public Log(Long logId, String ipAddress, String requestType, String requestURL, String requestDetails) {
        this.logId = logId;
        this.ipAddress = ipAddress;
        this.requestType = requestType;
        this.requestURL = requestURL;
        this.requestDetails = requestDetails;
        this.metaData = new MetaData();
    }

    public Log(Long logId) {
        this.logId = logId;
        this.metaData = new MetaData();
    }

    public Log() {
        this.metaData = new MetaData();
    }
}
