package org.rps.exception;

public class WrongChoiceException extends RuntimeException{
    public WrongChoiceException() {
        super("Wrong input");
    }
}
