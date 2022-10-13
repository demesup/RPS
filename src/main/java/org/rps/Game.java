package org.rps;


import lombok.extern.slf4j.Slf4j;
import org.rps.exception.ExitException;
import org.rps.exception.WrongChoiceCharacterException;

import java.io.IOException;
import java.util.Optional;

import static org.rps.RPSChoice.*;
import static org.utils.Read.readCharacter;

@Slf4j
public class Game {
    private final String userName;
    private int rounds;
    private int currentRound = 0;
    private int userWins = 0;
    private int computerWins = 0;
    private int draw = 0;

    public Game(String userName, int rounds) throws IOException {
        this.userName = userName;
        this.rounds = rounds;
        this.start();
    }

    private void start() throws IOException {
        log.info("Game started: user=" + userName + " rounds=" + rounds);
        while (++currentRound <= rounds) {
            startRound();
        }
        log.info("Game ended: " +
                "\n\trounds passed = " + --currentRound +
                "\n\tdraw=" + draw +
                "\n\tuser won=" + userWins +
                "\n\trobot won=" + computerWins);
    }

    private void startRound() throws IOException {
        try {
            startBattle();
        } catch (ExitException e) {
            log.info("Game ended after round " + --currentRound + " of " + rounds);
            rounds = 0;
        } catch (WrongChoiceCharacterException e) {
            log.debug(e.getMessage() + "\nTry again");
            startRound();
        }
    }

    private void startBattle() throws IOException {
        var userChoice = getUserChoice().orElseThrow(ExitException::new);
        var computerChoice = randomChoice();
        var message = userChoice + " VS " + computerChoice + " => " + getResult(userChoice, computerChoice);
        log.info(message);
    }

    private String getResult(RPSChoice userChoice, RPSChoice computerChoice) {
        var result = battle(userChoice, computerChoice);

        if (result == 0) {
            draw++;
            return "draw";
        } else if (result < 0) {
            computerWins++;
            return "computer won";
        } else {
            userWins++;
            return userName + " won";
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
            if (choice == 'e' || choice == 'E'){
                return Optional.empty();
            }
            return Optional.of(RPSChoice.byCharacter(choice));
        }
    }
}
