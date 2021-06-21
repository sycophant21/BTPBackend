package com.labAutomator.Handlers;

import com.labAutomator.Repositories.InstructorLabRepository;
import com.labAutomator.Repositories.LabSlotRepository;
import com.labAutomator.domain.InstructorLabPair;
import com.labAutomator.domain.id.LabID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InstructorLabHandler {

    @Autowired
    private InstructorLabRepository instructorLabRepository;
    @Autowired
    private LabSlotRepository labSlotRepository;


    public List<InstructorLabPair> getLabsCreatedByInstructor(UserID instructorId) {
        return instructorLabRepository.getAllByInstructor_UserID(instructorId);
    }
    public List<InstructorLabPair> getLabsAssociatedWithInstructor(UserID instructorId) {
        Set<InstructorLabPair> instructorLabPairs = new HashSet<>(instructorLabRepository.getAllByInstructor_UserID(instructorId));
        for (LabID labID : labSlotRepository.getLabSlotByInstructor_UserID(instructorId)) {
            instructorLabPairs.add(instructorLabRepository.getAllByLabID(labID));
        }
        instructorLabPairs.removeIf(Objects::isNull);
        return new ArrayList<>(instructorLabPairs);
    }

}
