package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InstructorLabPairID implements Serializable {
    private String instructorLabPairID;

    public InstructorLabPairID(String instructorLabPairID) {
        this.instructorLabPairID = instructorLabPairID;
    }
    public InstructorLabPairID() {
    }

    public String getInstructorLabPairID() {
        return instructorLabPairID;
    }

    public void setInstructorLabPairID(String instructorLabPairID) {
        this.instructorLabPairID = instructorLabPairID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstructorLabPairID)) return false;
        InstructorLabPairID that = (InstructorLabPairID) o;
        return getInstructorLabPairID().equals(that.getInstructorLabPairID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstructorLabPairID());
    }
}
