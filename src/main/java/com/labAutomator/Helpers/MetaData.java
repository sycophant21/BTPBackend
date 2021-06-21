package com.labAutomator.Helpers;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class MetaData {
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;

    public MetaData(LocalDateTime createdDate, LocalDateTime lastUpdatedDate) {
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public MetaData() {
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedDate = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
