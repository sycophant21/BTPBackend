package com.labAutomator.domain.id;

import com.labAutomator.Helpers.MetaData;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AttendanceID implements Serializable {

    private Long attendanceID;

    public AttendanceID(Long attendanceID) {
        this.attendanceID = attendanceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceID)) return false;
        AttendanceID that = (AttendanceID) o;
        return attendanceID.equals(that.attendanceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceID);
    }

    public AttendanceID() {
    }


}
