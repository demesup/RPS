package org.rps;


import org.rps.exception.ExitException;
import org.rps.exception.WrongChoiceCharacterException;

import java.io.IOException;
import java.util.Optional;

import static org.rps.RPSChoice.*;
import static org.utils.Read.readCharacter;

public class Game {
    private final String userName;
    private int rounds;
    private int currentRound = 0;
    private int userWins = 0;
    private int robotWins = 0;
    private int draw = 0;

    public Game(String userName, int rounds) throws IOException {
        this.userName = userName;
        this.rounds = rounds;
        this.start();
    }

    private void start() throws IOException {
        System.out.println("Game started: user=" + userName + " rounds=" + rounds);
        while (currentRound < rounds) {
            startRound();
            currentRound++;
        }
        System.out.println("Game ended: " +
                "\n\trounds passed = " + currentRound +
                "\n\tdraw=" + draw +
                "\n\tuser won=" + userWins +
                "\n\trobot won=" + robotWins);
    }

    private void startRound() throws IOException {
        try {
            startBattle();
        } catch (ExitException e) {
            rounds = 0;
        } catch (WrongChoiceCharacterException e) {
            System.out.println(e.getMessage() + "\nTry again");
            startBattle();
        }
    }

    private void startBattle() throws IOException {
        var userChoice = getUserChoice().orElseThrow(ExitException::new);
        var robotChoice = randomChoice();
        var result = battle(userChoice, robotChoice);
        System.out.println(userChoice + " " + robotChoice);
        if (result == 0) {
            System.out.println("draw");
            draw++;
        } else if (result < 0) {
            System.out.println("Robot wins");
            robotWins++;
        } else {
            System.out.println(userName + " wins");
            userWins++;
        }
    }

    private Optional<RPSChoice> getUserChoice() throws IOException {
        while (true) {
            var choice = readCharacter("""
                    Enter:
                    \ts - for scissors
                    \tp - for paper
                    \tr - for rock
                    \te - for exit""");
            if (choice == 'e') return Optional.empty();
            return Optional.of(RPSChoice.byCharacter(choice));
        }
    }
}
