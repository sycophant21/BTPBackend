package com.labAutomator.domain;

import com.labAutomator.Helpers.MetaData;
import com.labAutomator.Helpers.MidTermEnum;
import com.labAutomator.Utility.Generator;
import com.labAutomator.domain.id.LabID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Lab {
    private String subName;
    @EmbeddedId
    private LabID labID;
    private String labCode;
    private MidTermEnum midTermEnum;
    private MetaData metaData;
    private int labSlots;


    public Lab(LabID labID) {
        this.labID = labID;
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public Lab(String subName, String subCode, MidTermEnum midTermEnum, int labSlots) {
        this.subName = subName;
        this.labID = new LabID(subCode);
        this.midTermEnum = midTermEnum;
        this.labSlots = labSlots;
        this.labCode = Generator.generateLabCode();
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public Lab() {
        this.metaData = new MetaData(LocalDateTime.now(), LocalDateTime.now());
    }

    public MidTermEnum getMidTermEnum() {
        return midTermEnum;
    }

    public void setMidTermEnum(MidTermEnum midTermEnum) {
        this.midTermEnum = midTermEnum;
    }

    public int getLabSlots() {
        return labSlots;
    }

    public void setLabSlots(int labSlots) {
        this.labSlots = labSlots;
    }

    public MidTermEnum getMidSemEnum() {
        return midTermEnum;
    }

    public void setMidSemEnum(MidTermEnum midTermEnum) {
        this.midTermEnum = midTermEnum;
    }

    public String getLabCode() {
        return labCode;
    }

    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }

    public LabID getLabID() {
        return labID;
    }

    public void setLabID(LabID labID) {
        this.labID = labID;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lab)) return false;
        Lab lab = (Lab) o;
        return getLabID().equals(lab.getLabID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabID());
    }
}
