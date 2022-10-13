package org.rps.exception;

import static org.rps.RPSChoice.chars;

public class WrongChoiceCharacterException extends RuntimeException{
    public WrongChoiceCharacterException(char character) {
        super(character + " can not be transformed to RPSChoice{" + chars() + '}');
    }
}
