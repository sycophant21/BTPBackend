package com.labAutomator.controllers;

import com.labAutomator.Handlers.InstructorLabHandler;
import com.labAutomator.Handlers.LabHandler;
import com.labAutomator.Handlers.StudentLabHandler;
import com.labAutomator.Helpers.LabInfo;
import com.labAutomator.Helpers.LabSlotInfo;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.InstructorLabPair;
import com.labAutomator.domain.Lab;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LabsController {

    @Autowired
    private StudentLabHandler studentLabHandler;
    @Autowired
    private LabHandler labHandler;
    @Autowired
    private InstructorLabHandler instructorLabHandler;

    @GetMapping("/enrolledLabSlot")
    public List<LabSlot> getEnrolledLabSlots(@RequestParam("studentId") String studentID) {
        return studentLabHandler.getAllLabsOfUser(new UserID(studentID));
    }

    @GetMapping("/enrolledLabs")
    public List<Lab> getEnrolledLabs(@RequestParam("studentId") String studentID) {
        return studentLabHandler.getAllLabsOfUser(new UserID(studentID)).stream().map(LabSlot::getLab).collect(Collectors.toList());
    }

    @GetMapping("/createdLabs")
    public List<Lab> getCreatedLabs(@RequestParam("instructorId") String instructorID) {
        List<InstructorLabPair> instructorLabPairs = instructorLabHandler.getLabsCreatedByInstructor(new UserID(instructorID));
        return instructorLabPairs.stream().map(InstructorLabPair::getLab).collect(Collectors.toList());
    }

    @GetMapping("/associatedLabs")
    public List<InstructorLabPair> getAssociatedLabs(@RequestParam("instructorId") String instructorID) {
        return instructorLabHandler.getLabsAssociatedWithInstructor(new UserID(instructorID));
    }

/*    @GetMapping("/createdLabSlots")
    public List<Lab> getCreatedLabSlots(@RequestParam("inChargeID") String inChargeID) {
        List<InstructorLabPair> instructorLabPairs = instructorLabHandler.getLabsCreatedByInstructor(new UserID(instructorID));
        return instructorLabPairs.stream().map(InstructorLabPair::getLab).collect(Collectors.toList());
    }*/

    @GetMapping("/labs")
    public List<Lab> getAllLabs() {
        return labHandler.getAllLabs();
    }

    @GetMapping("/labSlots")
    public List<LabSlot> getLabSlotsInfo(@RequestParam("labID") String labID) {
        return labHandler.getLabSlots(new LabID(labID));
    }

    @GetMapping("/labInfo")
    public LabInfo getLabInfo(@RequestParam("labID") String labID) {
        return labHandler.getLabInfo(new LabID(labID));
    }

    @GetMapping("/labSlotInfo")
    public LabSlotInfo getLabSlotInfo(@RequestParam("labSlotID") String labSlotID) {
        return labHandler.getLabSlotInfo(new LabSlotID(labSlotID));
    }

    @PutMapping("/enrollStudentInLab")
    public boolean enrollStudentInLab(@RequestParam("studentId") String studentID, @RequestParam("labSlotId") String labSlotID) {
        return studentLabHandler.enrollStudentInLabSlot(new UserID(studentID), new LabSlotID(labSlotID));
    }

    @PutMapping("/enrollMeLab")
    public boolean enrollMeInLab(@RequestParam("studentId") String studentID, @RequestParam("labCode") String labCode) {
        return studentLabHandler.enrollMeInLabSlot(new UserID(studentID), labCode);
    }

    @PutMapping("/addLab")
    public List<Lab> addNewLab(@RequestParam("instructorId") String instructorID, @RequestParam("subjectName") String subName, @RequestParam("subCode") String subCode) {
        return labHandler.addLab(new UserID(instructorID), subName, subCode);
    }
    @PutMapping("/addNewLabSlot")
    public List<LabSlot> addNewLabSlot(@RequestParam("labID") String labID, @RequestParam("startTime") LocalTime startTime, @RequestParam("endTime") LocalTime endTime, @RequestParam("instructorId") String instructorID, @RequestParam("dayOfWeek") DayOfWeek dayOfWeek) {
        return labHandler.addNewLabSlot(new LabID(labID), startTime, endTime, dayOfWeek, new UserID(instructorID));
    }

    @PostMapping("/startLabSlot")
    public void startLabSlot(@RequestParam("labSlotID") String labSlotID) {
        labHandler.startLabSlot(new LabSlotID(labSlotID));
    }
    @PostMapping("/stopLabSlot")
    public void stopLabSlot(@RequestParam("labSlotID") String labSlotID) {
        labHandler.stopLabSlot(new LabSlotID(labSlotID));
    }
}
