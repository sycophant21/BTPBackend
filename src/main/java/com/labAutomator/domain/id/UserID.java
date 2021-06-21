package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserID implements Serializable {

    private String userID;

    public UserID(String userID) {
        this.userID = userID;
    }

    public UserID() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserID)) return false;
        UserID userID1 = (UserID) o;
        return getUserID().equals(userID1.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID());
    }
}
