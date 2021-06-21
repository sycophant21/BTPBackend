package com.labAutomator.domain.id;

import com.labAutomator.Helpers.MetaData;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LabID implements Serializable {
    private String labID;

    public LabID(String labID) {
        this.labID = labID;
    }

    public LabID() {
    }

    public String getLabID() {
        return labID;
    }

    public void setLabID(String labID) {
        this.labID = labID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabID)) return false;
        LabID labID1 = (LabID) o;
        return getLabID().equals(labID1.getLabID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabID());
    }
}
