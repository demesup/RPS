package org.rps;

import lombok.Getter;
import lombok.ToString;
import org.rps.exception.WrongChoiceCharacterException;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@ToString
public enum RPSChoice {
    PAPER('p'),
    SCISSORS('s'),
    ROCK('r');


    final char character;
    public static final int lastIndex = RPSChoice.values().length;
    static final Random random = new Random();

    RPSChoice(char q) {
        character = q;
    }

    static RPSChoice byCharacter(char character) {
        switch (character) {
            case 'r' -> {
                return ROCK;
            }
            case 's' -> {
                return SCISSORS;
            }
            case 'p' -> {
                return PAPER;
            }
            default -> throw new WrongChoiceCharacterException(character);
        }
    }

    static int battle(RPSChoice choice1, RPSChoice choice2) {
        int ordinal1 = choice1.ordinal();
        int ordinal2 = choice2.ordinal();
        if (ordinal1 == 0 && ordinal2 == lastIndex - 1) return 1;
        if (ordinal1 == lastIndex - 1 && ordinal2 == 0) return -1;
        return Integer.compare(ordinal1, ordinal2);
    }

    public static String chars() {
        return Arrays.stream(RPSChoice.values()).map(c -> String.valueOf(c.getCharacter())).collect(Collectors.joining(","));
    }

    public static RPSChoice randomChoice() {
        int i = random.nextInt(lastIndex);
        System.out.println(i);
        return RPSChoice.values()[i];
    }
}