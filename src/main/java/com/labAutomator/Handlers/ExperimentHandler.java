package com.labAutomator.Handlers;

import com.labAutomator.Repositories.ExperimentLabRepository;
import com.labAutomator.Repositories.ExperimentRepository;
import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.ExperimentLabPair;
import com.labAutomator.domain.Lab;
import com.labAutomator.domain.id.LabID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExperimentHandler {
    @Autowired
    private ExperimentRepository experimentRepository;
    @Autowired
    private ExperimentLabRepository experimentLabRepository;

    public List<Experiment> getExperimentsInLab(LabID labID) {
        return experimentLabRepository.getAllByLabID(labID).stream().map(ExperimentLabPair::getExperiment).collect(Collectors.toList());
    }

    public List<Experiment> getAllExperiments(LabID labID) {
        List<ExperimentLabPair> experimentLabPairs = experimentLabRepository.getAllByLabID(labID);
        List<Experiment> experiments = new ArrayList<>();
        for (ExperimentLabPair experimentLabPair : experimentLabPairs) {
            experiments.add(experimentLabPair.getExperiment());
        }
        return experiments;
    }

    public List<ExperimentLabPair> getAllExperimentsByLabIdAndInCurrentDirectionFromMidTermExamination(Lab lab) {
        return experimentLabRepository.getAllByLabIDAndLab_MidSemEnum(lab.getLabID(), lab.getMidSemEnum());
    }



}
