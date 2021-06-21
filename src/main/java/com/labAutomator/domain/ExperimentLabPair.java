package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.ExperimentLabPairID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class ExperimentLabPair {
    @EmbeddedId
    private ExperimentLabPairID experimentLabPairID;
    @OneToOne
    private Experiment experiment;
    @OneToOne
    private Lab lab;
    private MetaData metaData;

    public ExperimentLabPair(Experiment experiment, Lab lab) {
        this.experimentLabPairID = new ExperimentLabPairID( lab.getLabID().getLabID() + "_" + experiment.getExperimentID().getExperimentID());
        this.experiment = experiment;
        this.lab = lab;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public ExperimentLabPair(ExperimentLabPairID experimentLabPairID) {
        this.experimentLabPairID = experimentLabPairID;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public ExperimentLabPair() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }


    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
