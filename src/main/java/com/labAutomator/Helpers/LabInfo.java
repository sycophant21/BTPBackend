package com.labAutomator.Helpers;

import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.User;

import java.util.List;

public class LabInfo {
    private List<List<User>> enrolledStudents;
    private List<LabSlot> labSlots;
    private List<User> labInCharges;
    private List<User> instructors;
    private List<Experiment> experiments;

    public LabInfo(List<List<User>> enrolledStudents, List<LabSlot> labSlots, List<User> labInCharges, List<User> instructors, List<Experiment> experiments) {
        this.enrolledStudents = enrolledStudents;
        this.labSlots = labSlots;
        this.labInCharges = labInCharges;
        this.instructors = instructors;
        this.experiments = experiments;
    }

    public LabInfo() {
    }

    public void setEnrolledStudents(List<List<User>> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setLabSlots(List<LabSlot> labSlots) {
        this.labSlots = labSlots;
    }

    public void setLabInCharges(List<User> labInCharges) {
        this.labInCharges = labInCharges;
    }

    public void setInstructors(List<User> instructors) {
        this.instructors = instructors;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }

    public List<List<User>> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<LabSlot> getLabSlots() {
        return labSlots;
    }

    public List<User> getLabInCharges() {
        return labInCharges;
    }

    public List<User> getInstructors() {
        return instructors;
    }

    public List<Experiment> getExperiments() {
        return experiments;
    }
}
