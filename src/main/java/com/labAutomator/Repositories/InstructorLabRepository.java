package com.labAutomator.Repositories;

import com.labAutomator.domain.InstructorLabPair;
import com.labAutomator.domain.id.InstructorLabPairID;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorLabRepository extends CrudRepository<InstructorLabPair, InstructorLabPairID> {

    @Query("SELECT u FROM InstructorLabPair u where u.createdBy.userID = :instructorId")
    List<InstructorLabPair> getAllByInstructor_UserID(@Param("instructorId") UserID instructorId);

    @Query("SELECT u FROM InstructorLabPair u where u.lab.labID = :labID")
    InstructorLabPair getAllByLabID(@Param("labID") LabID labID);
}
