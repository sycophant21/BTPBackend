package com.labAutomator.Repositories;

import com.labAutomator.Helpers.MidTermEnum;
import com.labAutomator.domain.Lab;
import com.labAutomator.domain.id.LabID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LabRepository extends CrudRepository<Lab, LabID> {

    @Query("Select u from Lab u where u.labCode = :labCode")
    Lab getLabByLabCode(@Param("labCode") String labCode);

    @Transactional
    @Modifying
    @Query("UPDATE Lab u SET u.midTermEnum = :midTermStatus where u.labID = :labID")
    void updateLabTermStatus(@Param("labID") LabID labID, @Param("midTermStatus") MidTermEnum midTermEnum);

    @Transactional
    @Modifying
    @Query("UPDATE Lab u SET u.labSlots = :labSlots where u.labID = :labID")
    void updateLabSlotsAmount(@Param("labID") LabID labID, @Param("labSlots") int labSlots);

}
