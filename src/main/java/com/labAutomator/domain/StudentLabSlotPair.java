package com.labAutomator.domain;

import com.labAutomator.Helpers.EnrollmentStatus;
import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.StudentLabSlotPairID;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentLabSlotPair {
    @EmbeddedId
    private StudentLabSlotPairID studentLabID;
    @OneToOne
    private User user;
    @OneToOne
    private LabSlot labSlot;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;
    private MetaData metaData;


    public StudentLabSlotPair(User user, LabSlot labSlot, EnrollmentStatus enrollmentStatus) {
        this();
        this.studentLabID = new StudentLabSlotPairID(user.getUserID().getUserID() + "_" + labSlot.getLabSlotId().getLabSlotID());
        this.user = user;
        this.labSlot = labSlot;
        this.enrollmentStatus = enrollmentStatus;
    }

    public StudentLabSlotPair(StudentLabSlotPairID studentLabSlotPairID) {
        this.studentLabID = studentLabSlotPairID;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public StudentLabSlotPair() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public LabSlot getLabSlot() {
        return labSlot;
    }

    public void setLab(LabSlot labSlot) {
        this.labSlot = labSlot;
    }

    public StudentLabSlotPairID getStudentLabID() {
        return studentLabID;
    }

    public void setStudentLabID(StudentLabSlotPairID studentLabID) {
        this.studentLabID = studentLabID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
