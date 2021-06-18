package com.budger.exceptions;

public class GoalDoesNotExistsException extends RuntimeException{

    public GoalDoesNotExistsException(String message) {
        super(message);
    }
}
