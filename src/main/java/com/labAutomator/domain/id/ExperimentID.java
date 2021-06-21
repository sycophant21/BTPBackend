package com.labAutomator.domain.id;

import com.labAutomator.Helpers.MetaData;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ExperimentID implements Serializable {
    private String experimentID;

    public ExperimentID() {
    }

    public ExperimentID(String experimentID) {
        this.experimentID = experimentID;
    }

    public String getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(String experimentID) {
        this.experimentID = experimentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperimentID)) return false;
        ExperimentID that = (ExperimentID) o;
        return getExperimentID().equals(that.getExperimentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExperimentID());
    }
}
