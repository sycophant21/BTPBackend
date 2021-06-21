package com.labAutomator.Helpers;

import com.labAutomator.domain.ExperimentLabPair;

import java.util.Comparator;

public class ExperimentComparator implements Comparator<ExperimentLabPair> {
    @Override
    public int compare(ExperimentLabPair o1, ExperimentLabPair o2) {
        return o1.getExperiment().getExperimentID().getExperimentID().compareTo(o2.getExperiment().getExperimentID().getExperimentID());
    }
}
