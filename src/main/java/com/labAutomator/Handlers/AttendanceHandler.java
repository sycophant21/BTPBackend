package com.labAutomator.Handlers;

import com.labAutomator.Helpers.ExperimentStatus;
import com.labAutomator.Repositories.AttendanceRepository;
import com.labAutomator.Repositories.CurrentExperimentRepository;
import com.labAutomator.Repositories.StudentExperimentRepository;
import com.labAutomator.domain.Attendance;
import com.labAutomator.domain.StudentExperimentLabSlotPair;
import com.labAutomator.domain.id.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AttendanceHandler {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private StudentExperimentRepository studentExperimentRepository;
    @Autowired
    private CurrentExperimentRepository currentExperimentRepository;

    public boolean markFirstAttendance(UserID userID, ExperimentID experimentID, LabSlotID labSlotID) {
        StudentExperimentLabSlotPair studentExperimentLabSlotPair = getStudentExperimentLabSlotPairFromLabSlotID(userID, experimentID, labSlotID);
        return markIncomingAttendance(studentExperimentLabSlotPair);
    }

    public boolean markFirstAttendance(UserID userID, ExperimentID experimentID, String labCode) {
        StudentExperimentLabSlotPair studentExperimentLabSlotPair = getStudentExperimentLabSlotPairFromLabSlotCode(userID, experimentID, labCode);
        return markIncomingAttendance(studentExperimentLabSlotPair);
    }

    private boolean markIncomingAttendance(StudentExperimentLabSlotPair studentExperimentLabSlotPair) {
        if (studentExperimentLabSlotPair != null) {
            Attendance attendance = attendanceRepository.getAttendanceByStudentExperimentLabSlotPair(studentExperimentLabSlotPair.getStudentLabSlotExperimentId());
            if (attendance == null) {
                attendanceRepository.save(new Attendance(studentExperimentLabSlotPair, true, false));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean markSecondAttendance(UserID userID, ExperimentID experimentID, LabSlotID labSlotID) {
        StudentExperimentLabSlotPair studentExperimentLabSlotPair = getStudentExperimentLabSlotPairFromLabSlotID(userID, experimentID, labSlotID);
        if (studentExperimentLabSlotPair != null) {
            Attendance attendance = attendanceRepository.getAttendanceByStudentExperimentLabSlotPair(studentExperimentLabSlotPair.getStudentLabSlotExperimentId());
            if (attendance != null && !attendance.isSecondAttendance()) {
                StudentExperimentLabSlotPair currentExperiment = currentExperimentRepository.getStudentExperimentLabSlotPairsByUser_UserID(userID);
                if (currentExperiment != null) {
                    StudentExperimentLabSlotPair completedExperiment = new StudentExperimentLabSlotPair(currentExperiment.getUser(), currentExperiment.getExperiment(), currentExperiment.getLabSlot(), ExperimentStatus.COMPLETED);
                    studentExperimentRepository.save(completedExperiment);
                    attendanceRepository.markSecondAttendance(new StudentLabSlotExperimentPairID(userID.getUserID() + "_" + labSlotID.getLabSlotID() + "_" + experimentID.getExperimentID()));
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean markSecondAttendance(AttendanceID attendanceID) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceID);
        if (optionalAttendance.isPresent() && !optionalAttendance.get().isSecondAttendance()) {
            attendanceRepository.markSecondAttendance(attendanceID);
            return true;
        } else {
            return false;
        }
    }

    private StudentExperimentLabSlotPair getStudentExperimentLabSlotPairFromLabSlotID(UserID userID, ExperimentID experimentID, LabSlotID labSlotID) {
        return studentExperimentRepository.getAllByUserUserIDAndExperimentExperimentIDAndLabSlot_LabSlotId(userID, experimentID, labSlotID);
    }

    private StudentExperimentLabSlotPair getStudentExperimentLabSlotPairFromLabSlotCode(UserID userID, ExperimentID experimentID, String labCode) {
        return studentExperimentRepository.getAllByUserUserIDAndExperimentExperimentIDAndLabSlot_LabSlotCode(userID, experimentID, labCode);
    }
}
