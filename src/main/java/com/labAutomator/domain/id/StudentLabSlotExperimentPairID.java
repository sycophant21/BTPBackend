package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentLabSlotExperimentPairID implements Serializable {
    private String studentLabExperimentPairID;

    public StudentLabSlotExperimentPairID(String studentLabExperimentPairID) {
        this.studentLabExperimentPairID = studentLabExperimentPairID;
    }

    public StudentLabSlotExperimentPairID() {
    }

    public String getStudentLabExperimentPairID() {
        return studentLabExperimentPairID;
    }

    public void setStudentLabExperimentPairID(String studentLabExperimentPairID) {
        this.studentLabExperimentPairID = studentLabExperimentPairID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentLabSlotExperimentPairID)) return false;
        StudentLabSlotExperimentPairID that = (StudentLabSlotExperimentPairID) o;
        return getStudentLabExperimentPairID().equals(that.getStudentLabExperimentPairID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentLabExperimentPairID());
    }
}
