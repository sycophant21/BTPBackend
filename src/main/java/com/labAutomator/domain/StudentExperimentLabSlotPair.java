package com.labAutomator.domain;

import com.labAutomator.Helpers.ExperimentStatus;
import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.StudentLabSlotExperimentPairID;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentExperimentLabSlotPair {

    @EmbeddedId
    private StudentLabSlotExperimentPairID studentLabSlotExperimentId;
    @OneToOne
    private User user;
    @OneToOne
    private Experiment experiment;
    @OneToOne
    private LabSlot labSlot;
    @Enumerated(EnumType.STRING)
    private ExperimentStatus experimentStatus;
    private int marksObtained;
    private MetaData metaData;

/*    public StudentExperimentLabSlotPair(User user, Experiment experiment, Lab lab, ExperimentStatus experimentStatus, int marksObtained) {
        this.user = user;
        this.experiment = experiment;
        this.lab = lab;
        this.studentLabExperimentId = new StudentLabSlotExperimentPairID(user.getUserID().getUserID() + "_" + lab.getLabID().getLabID() + "_" + experiment.getExperimentID().getExperimentID());
        this.experimentStatus = experimentStatus;
        this.marksObtained = marksObtained;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }*/

    public StudentExperimentLabSlotPair(User user, Experiment experiment, LabSlot labSlot) {
        this.user = user;
        this.experiment = experiment;
        this.labSlot = labSlot;
        this.studentLabSlotExperimentId = new StudentLabSlotExperimentPairID(user.getUserID().getUserID() + "_" + labSlot.getLabSlotId().getLabSlotID() + "_" + experiment.getExperimentID().getExperimentID());
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public StudentExperimentLabSlotPair(StudentLabSlotExperimentPairID studentLabSlotExperimentId) {
        this.studentLabSlotExperimentId = studentLabSlotExperimentId;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public StudentExperimentLabSlotPair() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public StudentExperimentLabSlotPair(User user, Experiment experiment, LabSlot labSlot, ExperimentStatus experimentStatus) {
        this.user = user;
        this.experiment = experiment;
        this.labSlot = labSlot;
        this.studentLabSlotExperimentId = new StudentLabSlotExperimentPairID(user.getUserID().getUserID() + "_" + labSlot.getLabSlotId().getLabSlotID() + "_" + experiment.getExperimentID().getExperimentID());
        this.experimentStatus = experimentStatus;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public StudentLabSlotExperimentPairID getStudentLabSlotExperimentId() {
        return studentLabSlotExperimentId;
    }

    public void setStudentLabSlotExperimentId(StudentLabSlotExperimentPairID studentLabExperimentId) {
        this.studentLabSlotExperimentId = studentLabExperimentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public LabSlot getLabSlot() {
        return labSlot;
    }

    public void setLabSlot(LabSlot lab) {
        this.labSlot = lab;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ExperimentStatus getExperimentStatus() {
        return experimentStatus;
    }

    public void setExperimentStatus(ExperimentStatus experimentStatus) {
        this.experimentStatus = experimentStatus;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }
}
