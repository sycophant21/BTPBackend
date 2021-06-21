package com.labAutomator.domain.id;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExperimentLabPairID implements Serializable {
    private String experimentLabPairID;

    public ExperimentLabPairID(String experimentLabPairID) {
        this.experimentLabPairID = experimentLabPairID;
    }

    public ExperimentLabPairID() {

    }

    public String getExperimentLabPairID() {
        return experimentLabPairID;
    }

    public void setExperimentLabPairID(String experimentLabPairID) {
        this.experimentLabPairID = experimentLabPairID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperimentLabPairID)) return false;
        ExperimentLabPairID that = (ExperimentLabPairID) o;
        return getExperimentLabPairID().equals(that.getExperimentLabPairID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExperimentLabPairID());
    }
}
