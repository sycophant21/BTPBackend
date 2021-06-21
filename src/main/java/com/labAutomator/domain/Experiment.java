package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.domain.id.ExperimentID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.File;
import java.time.LocalDateTime;

@Entity
public class Experiment {
    @EmbeddedId
    private ExperimentID experimentID;
    private String experimentPdfAddress;
    private MetaData metaData;
    private int maxMarks;
    private boolean beforeMidSem;

    public Experiment(ExperimentID experimentID, String experimentPdfAddress, int maxMarks, boolean beforeMidSem) {
        this.experimentID = experimentID;
        this.experimentPdfAddress = experimentPdfAddress;
        this.maxMarks = maxMarks;
        this.beforeMidSem = beforeMidSem;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public Experiment(ExperimentID experimentID) {
        this.experimentID = experimentID;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public Experiment() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public boolean isBeforeMidSem() {
        return beforeMidSem;
    }

    public void setBeforeMidSem(boolean beforeMidSem) {
        this.beforeMidSem = beforeMidSem;
    }

    public ExperimentID getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(ExperimentID experimentID) {
        this.experimentID = experimentID;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public String getExperimentPdfAddress() {
        return experimentPdfAddress;
    }

    public void setExperimentPdfAddress(String experimentPdfAddress) {
        this.experimentPdfAddress = experimentPdfAddress;
    }
}
