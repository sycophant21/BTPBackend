package com.labAutomator.Handlers;

import com.labAutomator.Helpers.ExperimentStatus;
import com.labAutomator.Helpers.LabSlotExperimentInfo;
import com.labAutomator.Repositories.*;
import com.labAutomator.domain.*;
import com.labAutomator.domain.id.ExperimentID;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExperimentLabHandler {
    @Autowired
    private ExperimentRepository experimentRepository;
    @Autowired
    private ExperimentLabRepository experimentLabRepository;
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private LabSlotRepository labSlotRepository;
    @Autowired
    private StudentExperimentRepository studentExperimentRepository;

    public List<ExperimentLabPair> addNewExperiment(LabID labID, int maxMarks, boolean beforeMidSem, String experimentPdfAddress) {
        Optional<Lab> optionalLab = labRepository.findById(labID);
        Experiment experiment;
        List<ExperimentLabPair> experimentLabPairs = new ArrayList<>();
        if (optionalLab.isPresent()) {
            experimentLabPairs.addAll(experimentLabRepository.getAllByLabID(labID));
            if (experimentLabPairs.isEmpty()) {
                experiment = new Experiment(new ExperimentID(labID.getLabID() + "_1"), experimentPdfAddress, maxMarks, beforeMidSem);
            }
            else {
                experiment = new Experiment(new ExperimentID(labID.getLabID() + "_" + (experimentLabPairs.size() + 1)), experimentPdfAddress, maxMarks, beforeMidSem);
            }
            experimentRepository.save(experiment);
            ExperimentLabPair experimentLabPair = new ExperimentLabPair(experiment, optionalLab.get());
            experimentLabPairs.add(experimentLabPair);
            experimentLabRepository.save(experimentLabPair);
            for (LabSlot labSlot : labSlotRepository.getLabSlotsByLab_LabID(labID)) {
                labSlotRepository.updateLabsToBeDone(labSlot.getLabSlotId(), labSlot.getLabsToBeDone() + 1);
            }
        }
        return experimentLabPairs;
    }

    public LabSlotExperimentInfo getLabSlotExperimentInfo(LabID labID, LabSlotID labSlotID, ExperimentID experimentID) {
        List<StudentExperimentLabSlotPair> studentExperimentLabSlotPairs = studentExperimentRepository.getStudentsAssociatedWithExperimentIDAndLabSlotID(labID, labSlotID, experimentID);
        List<User> experimentCompletedBy = new ArrayList<>();
        List<User> experimentPendingFor = new ArrayList<>();
        if (!studentExperimentLabSlotPairs.isEmpty()) {
            for (StudentExperimentLabSlotPair studentExperimentLabSlotPair : studentExperimentLabSlotPairs) {
                if (studentExperimentLabSlotPair.getExperimentStatus() == ExperimentStatus.COMPLETED) {
                    experimentCompletedBy.add(studentExperimentLabSlotPair.getUser());
                }
                else if (studentExperimentLabSlotPair.getExperimentStatus() == ExperimentStatus.PENDING){
                    experimentPendingFor.add(studentExperimentLabSlotPair.getUser());
                }
            }
        }
        return new LabSlotExperimentInfo(experimentCompletedBy, experimentPendingFor, experimentRepository.findById(experimentID).orElse(null));
    }
}
