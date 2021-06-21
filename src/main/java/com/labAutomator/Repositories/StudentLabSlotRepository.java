package com.labAutomator.Repositories;

import com.labAutomator.domain.StudentLabSlotPair;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentLabSlotRepository extends CrudRepository<StudentLabSlotPair, Long> {
    @Query("Select u from StudentLabSlotPair u where u.user.userID = :userID")
    List<StudentLabSlotPair> getStudentLabSlotPairsByUserID(@Param("userID") UserID userID);

    @Query("Select u from StudentLabSlotPair u where u.labSlot.labSlotId = :labSlotID")
    List<StudentLabSlotPair> getAllByLabSlot_LabSlotID(@Param("labSlotID") LabSlotID labSlotID);

    @Query("Select u from StudentLabSlotPair u where u.labSlot.lab.labID = :labID")
    List<StudentLabSlotPair> getAllByLabID(@Param("labID") LabID labID);

    @Query("Select u from StudentLabSlotPair u where u.user.userID = :userID AND u.labSlot.labSlotId = :labSlotID")
    StudentLabSlotPair getStudentLabSlotPairByUserIDAndLabSlotID(@Param("userID") UserID userID, @Param("labSlotID") LabSlotID labSlotID);

    @Query("Select u from StudentLabSlotPair u where u.user.userID = :userID AND u.labSlot.labSlotCode = :labSlotCode")
    StudentLabSlotPair getStudentLabSlotPairByUserIDAndLabSlotLabSlotCode(@Param("userID") UserID userID, @Param("labSlotCode") String labSlotCode);
}
