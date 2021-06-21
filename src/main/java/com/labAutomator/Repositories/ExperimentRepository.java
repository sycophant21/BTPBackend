package com.labAutomator.Repositories;

import com.labAutomator.domain.Experiment;
import com.labAutomator.domain.id.ExperimentID;
import org.springframework.data.repository.CrudRepository;

public interface ExperimentRepository extends CrudRepository<Experiment, ExperimentID> {
}
