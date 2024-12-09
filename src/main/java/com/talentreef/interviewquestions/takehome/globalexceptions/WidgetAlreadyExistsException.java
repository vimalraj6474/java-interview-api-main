package com.talentreef.interviewquestions.takehome.globalexceptions;

public class WidgetAlreadyExistsException extends RuntimeException {
    public WidgetAlreadyExistsException(String message) {
        super(message);
    }
}
