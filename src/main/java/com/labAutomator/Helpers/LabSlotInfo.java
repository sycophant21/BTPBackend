package com.labAutomator.Helpers;

import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.ExperimentID;

import java.util.ArrayList;
import java.util.List;

public class LabSlotInfo {
    private List<User> enrolledStudents;
    private LabSlot labSlot;
    private List<Experiment> experiments;

    public LabSlotInfo(List<User> enrolledStudents, LabSlot labSlot, List<Experiment> experiments) {
        this.enrolledStudents = enrolledStudents;
        this.labSlot = labSlot;
        this.experiments = experiments;
    }

    public LabSlotInfo() {
        enrolledStudents = new ArrayList<>();
        experiments = new ArrayList<>();
    }

    public void setEnrolledStudents(List<User> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setLabSlot(LabSlot labSlot) {
        this.labSlot = labSlot;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    public List<User> getEnrolledStudents() {
        return enrolledStudents;
    }

    public LabSlot getLabSlot() {
        return labSlot;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }
}
