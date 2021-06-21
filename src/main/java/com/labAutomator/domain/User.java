package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.UserID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class User {
    private String name;
    private String emailID;
    @EmbeddedId
    private UserID userID;
    private MetaData metaData;

    public User(String name, String emailID) {
        this.name = name;
        this.emailID = emailID;
        this.userID = new UserID(emailID.substring(0, emailID.indexOf('@')));
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public User(UserID userID) {
        this.userID = userID;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public User() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public UserID getUserID() {
        return userID;
    }

    public void setUserID(UserID userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }
}
