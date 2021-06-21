package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentLabSlotPairID implements Serializable {
    private String studentLabSlotPairId;

    public StudentLabSlotPairID(String studentLabSlotPairId) {
        this.studentLabSlotPairId = studentLabSlotPairId;
    }

    public StudentLabSlotPairID() {

    }

    public String getStudentLabSlotPairId() {
        return studentLabSlotPairId;
    }

    public void setStudentLabSlotPairId(String studentLabPairId) {
        this.studentLabSlotPairId = studentLabPairId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentLabSlotPairID)) return false;
        StudentLabSlotPairID that = (StudentLabSlotPairID) o;
        return getStudentLabSlotPairId().equals(that.getStudentLabSlotPairId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentLabSlotPairId());
    }
}
