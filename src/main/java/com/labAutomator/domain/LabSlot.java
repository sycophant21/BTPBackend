package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.Utility.Generator;
import com.labAutomator.domain.id.LabSlotID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class LabSlot {
    @Id
    private LabSlotID labSlotId;
    @ManyToOne
    private Lab lab;
    private String labSlotCode;
    private LocalTime startTime;
    private LocalTime endTime;
    private MetaData metaData;
    private DayOfWeek dayOfWeek;
    @OneToOne
    private User instructor;
    private boolean running;
    private int labsCompleted;
    private int labsToBeDone;

    public LabSlot(LabSlotID labSlotId, Lab lab, String labSlotCode, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek, User instructor, boolean running, int labsCompleted, int labsToBeDone) {
        this.labSlotId = labSlotId;
        this.lab = lab;
        this.labSlotCode = labSlotCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.instructor = instructor;
        this.running = running;
        this.labsCompleted = labsCompleted;
        this.labsToBeDone = labsToBeDone;
        this.metaData = new MetaData();
    }
    public LabSlot(LabSlotID labSlotId, Lab lab, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek, User instructor, boolean running, int labsCompleted, int labsToBeDone) {
        this.labSlotId = labSlotId;
        this.lab = lab;
        this.dayOfWeek = dayOfWeek;
        this.instructor = instructor;
        this.running = running;
        this.labsCompleted = labsCompleted;
        this.labsToBeDone = labsToBeDone;
        this.labSlotCode = Generator.generateLabCode();
        this.startTime = startTime;
        this.endTime = endTime;
        this.metaData = new MetaData();
    }
    public LabSlot(Lab lab, String labSlotCode, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek, User instructor, boolean running, int labsCompleted, int labsToBeDone) {
        this.lab = lab;
        this.labSlotCode = labSlotCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.instructor = instructor;
        this.running = running;
        this.labsCompleted = labsCompleted;
        this.labsToBeDone = labsToBeDone;
        this.metaData = new MetaData();
    }
    public LabSlot(Lab lab, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek, User instructor, boolean running, int labsCompleted, int labsToBeDone) {
        this.lab = lab;
        this.dayOfWeek = dayOfWeek;
        this.instructor = instructor;
        this.running = running;
        this.labsCompleted = labsCompleted;
        this.labsToBeDone = labsToBeDone;
        this.labSlotCode = Generator.generateLabCode();;
        this.startTime = startTime;
        this.endTime = endTime;
        this.metaData = new MetaData();
    }
    public LabSlot(LabSlotID labSlotId) {
        this.labSlotId = labSlotId;
        this.metaData = new MetaData();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public LabSlot() {
        this.metaData = new MetaData();
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public void setLabSlotId(LabSlotID labTimingId) {
        this.labSlotId = labTimingId;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public LabSlotID getLabSlotId() {
        return labSlotId;
    }

    public String getLabSlotCode() {
        return labSlotCode;
    }

    public void setLabSlotCode(String labTimingCode) {
        this.labSlotCode = labTimingCode;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getLabsCompleted() {
        return labsCompleted;
    }

    public void setLabsCompleted(int labsCompleted) {
        this.labsCompleted = labsCompleted;
    }

    public int getLabsToBeDone() {
        return labsToBeDone;
    }

    public void setLabsToBeDone(int labsToBeDone) {
        this.labsToBeDone = labsToBeDone;
    }
}
