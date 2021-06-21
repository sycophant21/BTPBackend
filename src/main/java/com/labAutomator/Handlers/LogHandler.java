/*
package com.labAutomator.Handlers;

import com.labAutomator.Helpers.Log;
import com.labAutomator.Helpers.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class LogHandler {


    public void logRequest(String ipAddress, String requestType, String requestURL, Map<String, String> params) throws InvocationTargetException, IllegalAccessException {
        Logger logger = new Logger();
        for (Method method : Log.class.getDeclaredMethods()) {
            String methodName = method.getName();
            String methodNameInCamelCasing = String.valueOf(methodName.charAt(3)).toLowerCase().concat(methodName.substring(4));
            if (methodName.contains("set") && params.containsKey(methodNameInCamelCasing)) {
                method.invoke(params.get(methodNameInCamelCasing));
            }
        }
    }
}
*/
