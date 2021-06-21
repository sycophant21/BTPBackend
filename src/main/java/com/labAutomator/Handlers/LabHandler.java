package com.labAutomator.Handlers;

import com.labAutomator.Helpers.LabInfo;
import com.labAutomator.Helpers.LabSlotInfo;
import com.labAutomator.Helpers.MidTermEnum;
import com.labAutomator.Repositories.InstructorLabRepository;
import com.labAutomator.Repositories.LabRepository;
import com.labAutomator.Repositories.LabSlotRepository;
import com.labAutomator.Repositories.UserRepository;
import com.labAutomator.domain.*;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LabHandler {
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstructorLabRepository instructorLabRepository;
    @Autowired
    private StudentLabHandler studentLabHandler;
    @Autowired
    private LabSlotRepository labSlotRepository;
    @Autowired
    private ExperimentHandler experimentHandler;

    public List<Lab> getAllLabs() {
        Iterable<Lab> labs = labRepository.findAll();
        List<Lab> labList = new ArrayList<>();
        for (Lab lab : labs) {
            labList.add(lab);
        }
        return labList;
    }

    public List<Lab> getAllLabs(List<LabID> labIDS) {
        Iterable<Lab> labs = labRepository.findAllById(labIDS);
        List<Lab> labList = new ArrayList<>();
        for (Lab lab : labs) {
            labList.add(lab);
        }
        return labList;
    }

    public LabInfo getLabInfo(LabID labID) {
        LabInfo labInfo = new LabInfo();
        Optional<Lab> optionalLab = labRepository.findById(labID);
        if (optionalLab.isPresent()) {
            List<LabSlot> labSlots = labSlotRepository.getLabSlotsByLab_LabID(labID);
            if (labSlots != null && !labSlots.isEmpty()) {
                labInfo.setLabSlots(labSlots);
                List<List<User>> usersInDifferentSlots = new ArrayList<>();
                for (LabSlot labSlot : labSlots) {
                    List<User> enrolledStudents = studentLabHandler.getAllEnrolledStudents(labSlot.getLabSlotId());
                    if (enrolledStudents != null && !enrolledStudents.isEmpty()) {
                        usersInDifferentSlots.add(enrolledStudents);
                    }
                }
                labInfo.setEnrolledStudents(usersInDifferentSlots);
                List<Experiment> experiments = experimentHandler.getExperimentsInLab(optionalLab.get().getLabID());
                if (experiments != null && !experiments.isEmpty()) {
                    labInfo.setExperiments(experiments);
                }
            }
        }
        return labInfo;
    }

/*    public LabSlotInfo getLabSlotInfo(LabID labID, Long labTimingID) {
        LabSlotInfo labSlotInfo = new LabSlotInfo();
        Optional<Lab> optionalLab = labRepository.findById(labID);
        if (optionalLab.isPresent()) {
            Optional<LabSlot> optionalLabTiming = labSlotRepository.findById(labTimingID);
            if (optionalLabTiming.isPresent()) {
                labSlotInfo.setLabTiming(optionalLabTiming.get());
                List<User> enrolledStudents = studentLabHandler.getAllEnrolledStudents(optionalLab.get().getLabID());
                if (enrolledStudents != null && !enrolledStudents.isEmpty()) {
                    labSlotInfo.setEnrolledStudents(enrolledStudents);
                }
                List<Experiment> experiments = experimentHandler.getExperimentsInLab(optionalLab.get().getLabID());
                if (experiments != null && !experiments.isEmpty()) {
                    labSlotInfo.setExperiments(experiments);
                }
            }
        }
        return labSlotInfo;
    }*/

    public LabSlotInfo getLabSlotInfo(LabSlotID labSlotID) {
        LabSlotInfo labSlotInfo = new LabSlotInfo();
        Optional<LabSlot> optionalLabSlot = labSlotRepository.findById(labSlotID);
        if (optionalLabSlot.isPresent()) {
            labSlotInfo.setLabSlot(optionalLabSlot.get());
            List<User> enrolledStudents = studentLabHandler.getAllEnrolledStudents(optionalLabSlot.get().getLabSlotId());
            if (enrolledStudents != null && !enrolledStudents.isEmpty()) {
                labSlotInfo.setEnrolledStudents(enrolledStudents);
            }
            List<Experiment> experiments = experimentHandler.getExperimentsInLab(optionalLabSlot.get().getLab().getLabID());
            if (experiments != null && !experiments.isEmpty()) {
                labSlotInfo.setExperiments(experiments);
            }
        }
        return labSlotInfo;
    }

    public List<LabSlot> getLabSlots(LabID labID) {
        return labSlotRepository.getLabSlotsByLab_LabID(labID);
    }

    public List<LabSlot> addNewLabSlot(LabID labID, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek, UserID instructorID) {
        List<LabSlot> labSlots = new ArrayList<>();
        Optional<User> optionalInstructor = userRepository.findById(instructorID);
        if (optionalInstructor.isPresent()) {
            Optional<Lab> optionalLab = labRepository.findById(labID);
            if (optionalLab.isPresent()) {
                labSlotRepository.save(new LabSlot(optionalLab.get(), startTime, endTime, dayOfWeek, optionalInstructor.get(), false, 0, 0));
                labRepository.updateLabSlotsAmount(labID, optionalLab.get().getLabSlots() + 1);
                labSlots.addAll(labSlotRepository.getLabSlotsByLab_LabID(optionalLab.get().getLabID()));
            }
        }
        return labSlots;
    }

    public void startLabSlot(LabSlotID labSlotID) {
        labSlotRepository.updateLabStatus(labSlotID, true);
        studentLabHandler.assignExperimentsToStudentsInLabSlot(labSlotID);
    }

    public void stopLabSlot(LabSlotID labSlotID) {
        labSlotRepository.updateLabStatus(labSlotID, false);
        labSlotRepository.findById(labSlotID).ifPresent(k -> labSlotRepository.updateLabsCompleted(labSlotID, k.getLabsCompleted() + 1));
        studentLabHandler.markAbsentees(labSlotID);
    }

    public List<Lab> addLab(UserID userID, String subName, String subCode) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()) {
            Optional<Lab> optionalLab = labRepository.findById(new LabID(subCode));
            if (optionalLab.isEmpty()) {
                Lab lab = new Lab(subName, subCode, MidTermEnum.BEFORE_MIDTERM, 0);
                labRepository.save(lab);
                instructorLabRepository.save(new InstructorLabPair(optionalUser.get(), lab));
                return instructorLabRepository.getAllByInstructor_UserID(userID).stream().map(InstructorLabPair::getLab).collect(Collectors.toList());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void updateLabTermStatus(LabID labID, MidTermEnum midTermEnum) {
        labRepository.updateLabTermStatus(labID, midTermEnum);
    }

    public void setMidtermStatusToBeforeMidTerm(LabID labID) {
        updateLabTermStatus(labID, MidTermEnum.BEFORE_MIDTERM);
    }

    public void setMidtermStatusToAfterMidTerm(LabID labID) {
        updateLabTermStatus(labID, MidTermEnum.AFTER_MIDTERM);
    }


}
