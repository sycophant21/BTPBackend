package com.labAutomator.Repositories;

import com.labAutomator.Helpers.MidTermEnum;
import com.labAutomator.domain.ExperimentLabPair;
import com.labAutomator.domain.id.ExperimentLabPairID;
import com.labAutomator.domain.id.LabID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperimentLabRepository extends CrudRepository<ExperimentLabPair, ExperimentLabPairID> {
    @Query("Select u from ExperimentLabPair u where u.lab.labID = :labID")
    List<ExperimentLabPair> getAllByLabID(@Param("labID") LabID labID);

    @Query("Select u from ExperimentLabPair u where u.lab.labID = :labID and u.lab.midTermEnum = :midtermDirection")
    List<ExperimentLabPair> getAllByLabIDAndLab_MidSemEnum(@Param("labID") LabID labID, @Param("midtermDirection") MidTermEnum midTermEnum);
}
