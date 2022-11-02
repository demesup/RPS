package org.rps;


import lombok.extern.slf4j.Slf4j;
import org.rps.exception.ExitException;
import org.rps.exception.WrongChoiceException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

import static org.rps.RPS25Choice.*;
import static org.utils.Read.read;

@Slf4j
public class Game {
    private final String userName;
    private int rounds;
    private int currentRound = 0;
    private int userWins = 0;
    private int computerWins = 0;
    private int draw = 0;

    public Game(String userName, int rounds)  {
        this.userName = userName;
        this.rounds = rounds;
    }

    public void start() throws IOException {
        showChoiceTable();

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

    private static void showChoiceTable() {
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(jTable(), BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void startRound() throws IOException {
        try {
            startBattle();
        } catch (ExitException e) {
            log.info("Game ended after round " + --currentRound + " of " + rounds);
            rounds = 0;
        } catch (WrongChoiceException e) {
            log.info(e.getMessage() + "\nTry again");
            startRound();
        }
    }

    private void startBattle() throws IOException {
        var userChoice = getUserChoice(
                read("Enter your choice/ e - for exit")
        ).orElseThrow(WrongChoiceException::new);
        var computerChoice = randomChoice();
        var message = userChoice + " VS " + computerChoice + " => " + getResult(userChoice, computerChoice);
        log.info(message);
    }

    private String getResult(RPS25Choice userChoice, RPS25Choice computerChoice) {
        var result = battle(userChoice, computerChoice);
        return getResultMessage(result);
    }

    private String getResultMessage(int result) {
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

    private Optional<RPS25Choice> getUserChoice(String str) {
        if (str.equalsIgnoreCase("e") || str.equalsIgnoreCase("exit")) {
            throw new ExitException();
        }
        return RPS25Choice.optionalValueOf(str);
    }
}
