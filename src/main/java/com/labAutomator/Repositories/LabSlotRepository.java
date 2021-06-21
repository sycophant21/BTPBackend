package com.labAutomator.Repositories;

import com.labAutomator.domain.Lab;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LabSlotRepository extends CrudRepository<LabSlot, LabSlotID> {
    @Query("select u from LabSlot u where u.lab.labID = :labID")
    List<LabSlot> getLabSlotsByLab_LabID(@Param("labID")LabID labID);

    @Query("select u.lab from LabSlot u where u.labSlotId = :labSlotID")
    Lab getLabByLabSlotID(@Param("labSlotID")LabSlotID labSlotID);

    @Query("select u.lab.labID from LabSlot u where u.instructor.userID = :instructorID")
    List<LabID> getLabSlotByInstructor_UserID(@Param("instructorID") UserID instructorID);

    @Query("select u from LabSlot u where u.labSlotCode = :labSlotCode")
    LabSlot getLabSlotByLabSlotCode(@Param("labSlotCode")String labSlotCode);

    @Query("select count (u) from LabSlot u where u.lab.labID = :labID")
    int getNumberOfLabSlotByLab_LabID(@Param("labID")LabID labID);

    @Transactional
    @Modifying
    @Query("UPDATE LabSlot u SET u.running = :labStatus where u.labSlotId = :labSlotID")
    void updateLabStatus(@Param("labSlotID")LabSlotID labSlotID, @Param("labStatus") boolean labStatus);

    @Transactional
    @Modifying
    @Query("UPDATE LabSlot u SET u.labsCompleted = :labsCompleted where u.labSlotId = :labSlotID")
    void updateLabsCompleted(@Param("labSlotID")LabSlotID labSlotID, @Param("labsCompleted") int labsCompleted);

    @Transactional
    @Modifying
    @Query("UPDATE LabSlot u SET u.labsToBeDone = :labsToBeDone where u.labSlotId = :labSlotID")
    void updateLabsToBeDone(@Param("labSlotID")LabSlotID labSlotID, @Param("labsToBeDone") int labsToBeDone);


}
