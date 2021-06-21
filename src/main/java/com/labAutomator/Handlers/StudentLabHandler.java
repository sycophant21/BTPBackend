package com.labAutomator.Handlers;

import com.labAutomator.Helpers.EnrollmentStatus;
import com.labAutomator.Repositories.LabRepository;
import com.labAutomator.Repositories.LabSlotRepository;
import com.labAutomator.Repositories.StudentLabSlotRepository;
import com.labAutomator.Repositories.UserRepository;
import com.labAutomator.domain.Lab;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.StudentLabSlotPair;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentLabHandler {
    @Autowired
    private StudentLabSlotRepository studentLabSlotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private LabSlotRepository labSlotRepository;
    @Autowired
    private StudentExperimentHandler studentExperimentHandler;

    public List<LabSlot> getAllLabsOfUser(UserID userID) {
        List<LabSlot> allLabs = new ArrayList<>();
        List<StudentLabSlotPair> studentLabSlotPairs = studentLabSlotRepository.getStudentLabSlotPairsByUserID(userID);
        for (StudentLabSlotPair studentLabSlotPair : studentLabSlotPairs) {
            allLabs.add(studentLabSlotPair.getLabSlot());
        }
        return allLabs;
    }
/*    public List<Lab> getAllLabs() {
        List<Lab> allLabs = new ArrayList<>();
        Iterable<Lab> studentLabPairs = labRepository.findAll();
        for (Lab lab : studentLabPairs) {
            allLabs.add(lab);
        }
        return allLabs;
    }*/

/*    public void assignExperimentsToStudentsInLabWithLabID(LabID labID) {
        Optional<Lab> optionalLab = labRepository.findById(labID);
        optionalLab.ifPresent(lab -> studentExperimentHandler.assignExperimentsToStudents(studentLabSlotRepository.getAllByLabSlot_LabSlotID(labID).stream().map(StudentLabSlotPair::getUser).collect(Collectors.toList()), labID));
    }*/

    public void assignExperimentsToStudentsInLabSlot(LabSlotID labSlotID) {
        Optional<LabSlot> optionalLabSlot = labSlotRepository.findById(labSlotID);
        optionalLabSlot.ifPresent(labSlot -> studentExperimentHandler.assignExperimentsToStudents(studentLabSlotRepository.getAllByLabSlot_LabSlotID(labSlotID).stream().map(StudentLabSlotPair::getUser).collect(Collectors.toList()), labSlot));
    }

    public boolean enrollStudentInLabSlot(UserID userID, LabSlotID labSlotID) {
        if (studentLabSlotRepository.getStudentLabSlotPairByUserIDAndLabSlotID(userID, labSlotID) != null) {
            return false;
        } else {
            Optional<User> optionalUser = userRepository.findById(userID);
            if (optionalUser.isPresent()) {
                Optional<LabSlot> optionalLabSlot = labSlotRepository.findById(labSlotID);
                if (optionalLabSlot.isPresent()) {
                    studentLabSlotRepository.save(new StudentLabSlotPair(optionalUser.get(), optionalLabSlot.get(), EnrollmentStatus.INVITED));
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public List<User> getAllEnrolledStudents(LabID labID) {
        return studentLabSlotRepository.getAllByLabID(labID).stream().map(StudentLabSlotPair::getUser).collect(Collectors.toList());
    }

    public int getNumberOfLabSlotsActive(LabID labID) {
        return labSlotRepository.getNumberOfLabSlotByLab_LabID(labID);
    }

    public List<User> getAllEnrolledStudents(LabSlotID labSlotID) {
        return studentLabSlotRepository.getAllByLabSlot_LabSlotID(labSlotID).stream().map(StudentLabSlotPair::getUser).collect(Collectors.toList());
    }

    public boolean enrollMeInLabSlot(UserID userID, String labSlotCode) {
        if (studentLabSlotRepository.getStudentLabSlotPairByUserIDAndLabSlotLabSlotCode(userID, labSlotCode) != null) {
            return false;
        } else {
            Optional<User> optionalUser = userRepository.findById(userID);
            if (optionalUser.isPresent()) {
                LabSlot tempLabSlot = labSlotRepository.getLabSlotByLabSlotCode(labSlotCode);
                if (tempLabSlot != null) {
                    studentLabSlotRepository.save(new StudentLabSlotPair(optionalUser.get(), tempLabSlot, EnrollmentStatus.ACCEPTED));
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    public void markAbsentees(LabSlotID labSlotID) {
        studentExperimentHandler.assignPendingExperiments(labSlotID);
    }

}
