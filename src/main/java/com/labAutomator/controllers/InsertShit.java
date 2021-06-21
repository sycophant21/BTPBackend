/*
package com.labAutomator.controllers;

import com.labAutomator.Helpers.MidTermEnum;
import com.labAutomator.Repositories.InstructorLabRepository;
import com.labAutomator.Repositories.LabRepository;
import com.labAutomator.Repositories.LabSlotRepository;
import com.labAutomator.Repositories.UserRepository;
import com.labAutomator.domain.InstructorLabPair;
import com.labAutomator.domain.Lab;
import com.labAutomator.domain.LabSlot;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.LabSlotID;
import com.labAutomator.domain.id.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalTime;

@RestController
public class InsertShit {
    @Autowired
    private LabSlotRepository labSlotRepository;
    @Autowired
    private LabRepository labRepository;
    @Autowired
    private InstructorLabRepository instructorLabRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/insertShit")
    public void insertShit() {
        Lab lab = new Lab("Physics", "PHY113", MidTermEnum.BEFORE_MIDTERM, 0);
        //labRepository.save(lab);
        User user = userRepository.findById(new UserID("18ucs209")).orElse(null);
        if (user != null) {
            instructorLabRepository.save(new InstructorLabPair(user, lab));
            //Optional<Lab> optionalLab = labRepository.findById(new LabID("PHY113"));
            LabSlot labSlot = new LabSlot(new LabSlotID(lab.getLabID().getLabID() + "_01"), lab, LocalTime.of(3, 30), LocalTime.of(5, 30), DayOfWeek.MONDAY, user, false, 0, 10);
            labSlotRepository.save(labSlot);
            //optionalLab.ifPresent(k-> labSlotRepository.save(new LabSlot(new LabSlotID(k.getLabID() + "_01"), k, LocalTime.of(3,30), LocalTime.of(5,30))));
            //experimentRepository.save(experiment);
            //experimentLabRepository.save(new ExperimentLabPair(experiment, lab));
        }
    }
}
//        Pattern studentEmailPattern = Pattern.compile("^\\d{2}(?i)[UMP](?i)[CM](?i)[CSE]@lnmiit.ac.in$");
//        Pattern teacherEmailPattern = Pattern.compile("^\\D+[.]?\\D+@lnmiit.ac.in$");
//        Matcher studentEmailPatternMatcher = studentEmailPattern.matcher(userID);
//        Matcher teacherEmailPatternMatcher = teacherEmailPattern.matcher(userID);
//        if (studentEmailPatternMatcher.matches() || teacherEmailPatternMatcher.matches()) {
//        }
//        else {
//            throw new InputMismatchException();
//        }
*/
