package com.labAutomator.Helpers;

public class LoggerUtils {

    public static void logRequest(String ipAddress, String requestType, String requestURL, String[] requestDetails) {

        String detailsToSave = "\"";
        for (String string : requestDetails) {
            detailsToSave = detailsToSave.concat(string + "\n");
        }
        detailsToSave = detailsToSave.substring(0, detailsToSave.length() - 1).concat("\"");
        //logReq(new Log(ipAddress, requestType, requestURL, detailsToSave));
    }
}
