package com.labAutomator.Handlers;

import com.labAutomator.Helpers.ExperimentComparator;
import com.labAutomator.Helpers.ExperimentStatus;
import com.labAutomator.Repositories.CurrentExperimentRepository;
import com.labAutomator.Repositories.ExperimentLabRepository;
import com.labAutomator.Repositories.LabSlotRepository;
import com.labAutomator.Repositories.StudentExperimentRepository;
import com.labAutomator.domain.*;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentExperimentHandler {

    @Autowired
    private StudentExperimentRepository studentExperimentRepository;
    @Autowired
    private ExperimentLabRepository experimentLabRepository;
    @Autowired
    private ExperimentHandler experimentHandler;
    @Autowired
    private CurrentExperimentRepository currentExperimentRepository;
    @Autowired
    private LabSlotRepository labSlotRepository;

    private List<StudentExperimentLabSlotPair> getAssignedExperiments(UserID userID, LabSlotID labSlotID) {
        return studentExperimentRepository.getAllByStudentIdAndLabSlotID(userID, labSlotID);
    }

    public List<Experiment> getCompletedExperiments(UserID userID, LabSlotID labSlotID) {
        List<StudentExperimentLabSlotPair> studentExperimentLabSlotPairs = getAssignedExperiments(userID, labSlotID);
        List<Experiment> completedExperiments = new ArrayList<>();
        for (StudentExperimentLabSlotPair studentExperimentLabSlotPair : studentExperimentLabSlotPairs) {
            if (studentExperimentLabSlotPair.getExperimentStatus() == ExperimentStatus.COMPLETED) {
                completedExperiments.add(studentExperimentLabSlotPair.getExperiment());
            }
        }
        return completedExperiments;
    }

    public List<Experiment> getPendingExperiments(UserID userID, LabSlotID labSlotID) {
        List<StudentExperimentLabSlotPair> studentExperimentLabSlotPairs = getAssignedExperiments(userID, labSlotID);
        List<Experiment> pendingExperiments = new ArrayList<>();
        for (StudentExperimentLabSlotPair studentExperimentLabSlotPair : studentExperimentLabSlotPairs) {
            if (studentExperimentLabSlotPair.getExperimentStatus() == ExperimentStatus.PENDING) {
                pendingExperiments.add(studentExperimentLabSlotPair.getExperiment());
            }
        }
        return pendingExperiments;
    }

/*    public StudentExperimentLabSlotPair assignExperimentToStudent(UserID userID, LabID labID, ExperimentID experimentID) {
        return studentExperimentRepository.save(new StudentExperimentLabSlotPair(new User(userID), new Experiment(experimentID), new Lab(labID), false, 0));
    }*/

/*    public List<StudentExperimentLabSlotPair> assignGivenExperimentsToStudents(List<StudentExperimentLabSlotPair> studentExperimentLabPairs) {
        List<StudentExperimentLabSlotPair> studentExperimentLabPairList = new ArrayList<>();
        for (StudentExperimentLabSlotPair studentExperimentLabPair : studentExperimentRepository.saveAll(studentExperimentLabPairs)) {
            studentExperimentLabPairList.add(studentExperimentLabPair);
        }
        return studentExperimentLabPairList;
    }*/

    public void assignExperimentsToStudents(List<User> users, LabSlot labSlot) {
        List<ExperimentLabPair> experimentLabPairs = experimentHandler.getAllExperimentsByLabIdAndInCurrentDirectionFromMidTermExamination(labSlot.getLab());
        for (User user : users) {
            List<StudentExperimentLabSlotPair> studentExperimentLabSlotPairs = studentExperimentRepository.getExperimentCompletedByStudent(user.getUserID(), labSlot.getLab().getLabID());
            List<ExperimentLabPair> upcomingExperiments = new ArrayList<>();
            for (ExperimentLabPair experimentLabPair : experimentLabPairs) {
                Experiment experiment = experimentLabPair.getExperiment();
                boolean found = false;
                for (StudentExperimentLabSlotPair studentExperimentLabSlotPair : studentExperimentLabSlotPairs) {
                    if (studentExperimentLabSlotPair.getExperiment().equals(experiment)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    upcomingExperiments.add(experimentLabPair);
                }
            }
            upcomingExperiments.sort(new ExperimentComparator());
            currentExperimentRepository.save(new StudentExperimentLabSlotPair(user, upcomingExperiments.get(0).getExperiment(), labSlot));
        }
    }


    public void assignPendingExperiments(LabSlotID labSlotID) {
        List<StudentExperimentLabSlotPair> studentExperimentLabSlotPairs = currentExperimentRepository.getStudentExperimentLabSlotsPairsByLabSlot_LabSlotID(labSlotID);
        List<StudentExperimentLabSlotPair> pendingExperiments = new ArrayList<>();
        for (StudentExperimentLabSlotPair studentExperimentLabSlotPair : studentExperimentLabSlotPairs) {
            pendingExperiments.add(new StudentExperimentLabSlotPair(studentExperimentLabSlotPair.getUser(), studentExperimentLabSlotPair.getExperiment(), studentExperimentLabSlotPair.getLabSlot(), ExperimentStatus.PENDING));
        }
        studentExperimentRepository.saveAll(pendingExperiments);

    }
}
