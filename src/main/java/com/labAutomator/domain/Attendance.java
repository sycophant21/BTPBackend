package com.labAutomator.domain;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "attendance_sequence"),
                    @Parameter(name = "initial_value", value = "10000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long attendanceID;
    @OneToOne
    private StudentExperimentLabSlotPair studentExperimentLabSlotPair;
    private boolean firstAttendance;
    private boolean secondAttendance;


    public Attendance(StudentExperimentLabSlotPair studentExperimentLabSlotPair) {
        this.studentExperimentLabSlotPair = studentExperimentLabSlotPair;
    }

    public Attendance(StudentExperimentLabSlotPair studentExperimentLabSlotPair, boolean firstAttendance, boolean secondAttendance) {
        this.studentExperimentLabSlotPair = studentExperimentLabSlotPair;
        this.firstAttendance = firstAttendance;
        this.secondAttendance = secondAttendance;
    }

    public Attendance() {

    }

    public StudentExperimentLabSlotPair getStudentLabExperimentPair() {
        return studentExperimentLabSlotPair;
    }

    public void setStudentLabExperimentPair(StudentExperimentLabSlotPair studentExperimentLabSlotPair) {
        this.studentExperimentLabSlotPair = studentExperimentLabSlotPair;
    }

    public boolean isFirstAttendance() {
        return firstAttendance;
    }

    public void setFirstAttendance(boolean firstAttendance) {
        this.firstAttendance = firstAttendance;
    }

    public boolean isSecondAttendance() {
        return secondAttendance;
    }

    public void setSecondAttendance(boolean secondAttendance) {
        this.secondAttendance = secondAttendance;
    }

}
