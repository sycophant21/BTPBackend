package com.labAutomator.Helpers;

import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.User;

import java.util.List;

public class LabSlotExperimentInfo {
    private final List<User> experimentCompletedBy;
    private final List<User> experimentPendingFor;
    private final Experiment experiment;

    public LabSlotExperimentInfo(List<User> experimentCompletedBy, List<User> experimentPendingFor, Experiment experiment) {
        this.experimentCompletedBy = experimentCompletedBy;
        this.experimentPendingFor = experimentPendingFor;
        this.experiment = experiment;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public List<User> getExperimentCompletedBy() {
        return experimentCompletedBy;
    }

    public List<User> getExperimentPendingFor() {
        return experimentPendingFor;
    }
}
