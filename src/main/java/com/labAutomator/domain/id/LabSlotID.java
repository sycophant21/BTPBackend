package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LabSlotID implements Serializable {
    private String labSlotID;

    public LabSlotID(String labSlotID) {
        this.labSlotID = labSlotID;
    }

    public LabSlotID() {
    }

    public String getLabSlotID() {
        return labSlotID;
    }

    public void setLabSlotID(String labSlotID) {
        this.labSlotID = labSlotID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabSlotID)) return false;
        LabSlotID labSlotID1 = (LabSlotID) o;
        return getLabSlotID().equals(labSlotID1.getLabSlotID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabSlotID());
    }
}
