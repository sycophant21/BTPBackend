package com.labAutomator.controllers;

import com.labAutomator.Handlers.ExperimentHandler;
import com.labAutomator.Handlers.ExperimentLabHandler;
import com.labAutomator.Handlers.StudentExperimentHandler;
import com.labAutomator.Helpers.LabSlotExperimentInfo;
import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.ExperimentLabPair;
import com.labAutomator.domain.id.ExperimentID;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperimentController {
    @Autowired
    private StudentExperimentHandler studentExperimentHandler;
    @Autowired
    private ExperimentHandler experimentHandler;
    @Autowired
    private ExperimentLabHandler experimentLabHandler;

    @GetMapping("/completedExperiments")
    public List<Experiment> getCompletedExperiments(@RequestParam("studentId") String studentId, @RequestParam("labSlotId") String labSlotID) {
        return studentExperimentHandler.getCompletedExperiments(new UserID(studentId), new LabSlotID(labSlotID));
    }

    @GetMapping("/pendingExperiments")
    public List<Experiment> getPendingExperiments(@RequestParam("studentId") String studentId, @RequestParam("labSlotId") String labSlotID) {
        return studentExperimentHandler.getPendingExperiments(new UserID(studentId), new LabSlotID(labSlotID));
    }

    @GetMapping("/experiments")
    public List<Experiment> getAllExperiments(@RequestParam("labId") String labID) {
        return experimentHandler.getAllExperiments(new LabID(labID));
    }

/*    @PutMapping("/assignExperiment")
    public StudentExperimentLabSlotPair assignExperiment(@RequestParam("studentId") String studentID, @RequestParam("labId") String labID, @RequestParam("experimentId") String experimentID) {
        return studentExperimentHandler.assignExperimentToStudent(new UserID(studentID), new LabID(labID), new ExperimentID(experimentID));
    }*/

    @PutMapping("/addExperiment")
    public List<ExperimentLabPair> addNewExperiment(@RequestParam("labId") String labID, @RequestParam("maxMarks") int maxMarks, @RequestParam("beforeMidSem") boolean beforeMidSem, @RequestParam("experimentPdfAddress") String experimentPdfAddress) {
        return experimentLabHandler.addNewExperiment(new LabID(labID), maxMarks, beforeMidSem, experimentPdfAddress);
    }

    @PostMapping("/replaceExperiment")
    public void replaceExperiment() {

    }

    @GetMapping("/getLabSlotExperimentInfo")
    public LabSlotExperimentInfo getLabSlotExperimentInfo(@RequestParam("labID") String labID, @RequestParam("labSlotID") String labSlotID, @RequestParam("experimentID") String experimentID) {
        return experimentLabHandler.getLabSlotExperimentInfo(new LabID(labID), new LabSlotID(labSlotID), new ExperimentID(experimentID));
    }
}
