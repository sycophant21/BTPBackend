package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.InstructorLabPairID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class InstructorLabPair {
    @Id
    private InstructorLabPairID instructorLabPairID;
    @OneToOne
    private User createdBy;
    @OneToOne
    private Lab lab;
    private MetaData metaData;

    public InstructorLabPair(User createdBy, Lab lab) {
        this.createdBy = createdBy;
        this.lab = lab;
        this.instructorLabPairID = new InstructorLabPairID(this.createdBy.getUserID().getUserID() + "" + this.lab.getLabID().getLabID());
        this.metaData = new MetaData();
    }
    public InstructorLabPair(InstructorLabPairID instructorLabPairID) {
        this.instructorLabPairID = instructorLabPairID;
        this.metaData = new MetaData();
    }
    public InstructorLabPair() {
        this.metaData = new MetaData();
    }

    public InstructorLabPairID getInstructorLabPairID() {
        return instructorLabPairID;
    }

    public void setInstructorLabPairID(InstructorLabPairID instructorLabPairID) {
        this.instructorLabPairID = instructorLabPairID;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User instructor) {
        this.createdBy = instructor;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
