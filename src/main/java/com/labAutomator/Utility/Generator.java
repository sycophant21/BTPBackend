package com.labAutomator.Utility;

import com.labAutomator.domain.Lab;
import com.labAutomator.domain.id.LabID;

import java.util.Random;
import java.util.stream.IntStream;

public class Generator {

    public static String generateLabCode() {
        Random random = new Random();
        StringBuilder labCode = new StringBuilder();
        IntStream.range(0, 1).forEach(i -> labCode.append(generateRandomString(random)));
        return labCode.toString();
    }


    private static String generateRandomString(Random random) {
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
