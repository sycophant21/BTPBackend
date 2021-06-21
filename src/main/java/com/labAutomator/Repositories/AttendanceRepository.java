package com.labAutomator.Repositories;

import com.labAutomator.domain.Attendance;
import com.labAutomator.domain.id.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AttendanceRepository extends CrudRepository<Attendance, AttendanceID> {
    @Transactional
    @Modifying
    @Query("UPDATE Attendance SET secondAttendance = true where studentExperimentLabSlotPair.studentLabSlotExperimentId = :studentExperimentLabPairID")
    void markSecondAttendance(@Param("studentExperimentLabPairID") StudentLabSlotExperimentPairID studentLabSlotExperimentPairID);

    @Transactional
    @Modifying
    @Query("UPDATE Attendance SET secondAttendance = true where attendanceID = :attendanceID")
    void markSecondAttendance(@Param("attendanceID") AttendanceID attendanceID);

    @Query("SELECT u FROM Attendance u WHERE u.studentExperimentLabSlotPair.studentLabSlotExperimentId = :studentLabSlotExperimentPairID")
    Attendance getAttendanceByStudentExperimentLabSlotPair(@Param("studentLabSlotExperimentPairID") StudentLabSlotExperimentPairID studentLabSlotExperimentPairID);
}
