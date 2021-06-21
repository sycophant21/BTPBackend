package com.labAutomator.controllers;

import com.labAutomator.Handlers.AttendanceHandler;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.id.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceHandler attendanceHandler;

    @PutMapping("/markFirstAttendanceFromLabSlotID")
    public boolean markFirstAttendanceFromLabSlotID(@RequestParam("studentId") String studentID, @RequestParam("labSlotId") String labSlotID, @RequestParam("experimentId") String experimentID) {
        return attendanceHandler.markFirstAttendance(new UserID(studentID), new ExperimentID(experimentID), new LabSlotID(labSlotID));
    }
    @PutMapping("/markFirstAttendanceFromLabSlotCode")
    public boolean markFirstAttendanceFromLabSlotCode(@RequestParam("studentId") String studentID, @RequestParam("labCode") String labCode, @RequestParam("experimentId") String experimentID) {
        return attendanceHandler.markFirstAttendance(new UserID(studentID), new ExperimentID(experimentID), labCode);
    }

    @PutMapping("/markSecondAttendanceWithUserID")
    public boolean markSecondAttendance(@RequestParam("studentId") String studentID, @RequestParam("labSlotId") String labSlotID, @RequestParam("experimentId") String experimentID) {
        return attendanceHandler.markSecondAttendance(new UserID(studentID), new ExperimentID(experimentID), new LabSlotID(labSlotID));
    }
    @PutMapping("/markSecondAttendanceWithAttendanceID")
    public boolean markSecondAttendance(@RequestParam("attendanceId") Long attendanceID) {
        return attendanceHandler.markSecondAttendance(new AttendanceID(attendanceID));
    }
}
