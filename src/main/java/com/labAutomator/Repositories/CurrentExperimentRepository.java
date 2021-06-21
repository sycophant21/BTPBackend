package com.labAutomator.Repositories;

import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.StudentExperimentLabSlotPair;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CurrentExperimentRepository extends CrudRepository<StudentExperimentLabSlotPair, Long> {

    @Query("select u from StudentExperimentLabSlotPair u where u.user.userID = :userID")
    StudentExperimentLabSlotPair getStudentExperimentLabSlotPairsByUser_UserID(@Param("userID")UserID userID);

    @Query("select u from StudentExperimentLabSlotPair u where u.labSlot.labSlotId = :labSlotID")
    List<StudentExperimentLabSlotPair> getStudentExperimentLabSlotsPairsByLabSlot_LabSlotID(@Param("labSlotID") LabSlotID labSlotID);
}
