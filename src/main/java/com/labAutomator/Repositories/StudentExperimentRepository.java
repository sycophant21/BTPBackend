package com.labAutomator.Repositories;

import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.StudentExperimentLabSlotPair;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.ExperimentID;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentExperimentRepository extends CrudRepository<StudentExperimentLabSlotPair, Long> {
    @Query("Select u from StudentExperimentLabSlotPair u where u.user.userID = :userID AND u.labSlot.labSlotId = :labSlotID")
    List<StudentExperimentLabSlotPair> getAllByStudentIdAndLabSlotID(@Param("userID")UserID userID, @Param("labSlotID")LabSlotID labSlotID);

    @Query("Select u from StudentExperimentLabSlotPair u where u.labSlot.lab.labID = :labID")
    List<StudentExperimentLabSlotPair> getAllByLabID(LabID labID);

    @Query("Select u from StudentExperimentLabSlotPair u where u.user.userID = :userID AND u.experiment.experimentID = :experimentID AND u.labSlot.labSlotId = :labSlotID")
    StudentExperimentLabSlotPair getAllByUserUserIDAndExperimentExperimentIDAndLabSlot_LabSlotId(@Param("userID") UserID userID, @Param("experimentID") ExperimentID experimentID, @Param("labSlotID") LabSlotID labSlotID);

    @Query("Select u from StudentExperimentLabSlotPair u where u.user.userID = :userID AND u.experiment.experimentID = :experimentID AND u.labSlot.labSlotCode = :labSlotCode")
    StudentExperimentLabSlotPair getAllByUserUserIDAndExperimentExperimentIDAndLabSlot_LabSlotCode(@Param("userID") UserID userID, @Param("experimentID") ExperimentID experimentID, @Param("labSlotCode") String labSlotCode);
    /*@Transactional
    @Modifying
    @Query("UPDATE StudentExperimentLabSlotPair SET isCompleted = true where user.userID = :userID AND experiment.experimentID = :experimentID AND lab.labID = :labID")
    void updateExperimentCompletion(@Param("userID") UserID userID, @Param("experimentID") ExperimentID experimentID, @Param("labID") LabID labID);
*/
    @Query("SELECT s from StudentExperimentLabSlotPair s where s.user.userID = :userID and s.labSlot.lab.labID = :labID order by s.metaData.lastUpdatedDate desc")
    List<StudentExperimentLabSlotPair> getExperimentCompletedByStudent(@Param("userID") UserID userID, @Param("labID") LabID labID);

    @Query("select s from StudentExperimentLabSlotPair s where s.labSlot.lab.labID = :labID and s.labSlot.labSlotId = :labSlotID and s.experiment.experimentID = :experimentID")
    List<StudentExperimentLabSlotPair> getStudentsAssociatedWithExperimentIDAndLabSlotID(@Param("labID")LabID labID, @Param("labSlotID")LabSlotID labSlotID, @Param("experimentID") ExperimentID experimentID);
}
