package org.rps;

import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.rps.RPS25Choice.*;


public class GameTest {
    static Game game = new Game("test", 5);

    @SneakyThrows
    @Test
    public void getUserChoiceReturnEmptyOptionalWhenChoiceNotFound() {
        Method method = Game.class.getDeclaredMethod("getUserChoice", String.class);
        method.setAccessible(true);
        assertEquals(Optional.empty(), method.invoke(game, "haha"));
    }

    @SneakyThrows
    @Test
    public void exitInGetUserChoiceCanEndGame() {
        Method method = Game.class.getDeclaredMethod("getUserChoice", String.class);
        method.setAccessible(true);
        System.out.println("invoked");

        assertThrows(InvocationTargetException.class, () -> method.invoke(game, "exit"));
    }

    @SneakyThrows
    @Test
    public void drawIfBattleResultEqualsZero() {
        Method method = Game.class.getDeclaredMethod("getResultMessage", int.class);
        method.setAccessible(true);
        assertEquals("draw", method.invoke(game, 0));
    }

    @SneakyThrows
    @Test
    public void computerWonIfBattleResultEqualsMinusOne() {
        Method method = Game.class.getDeclaredMethod("getResultMessage", int.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, -1));
    }

    @SneakyThrows
    @Test
    public void playerWonIfBattleResultEqualsOne() {
        Method method = Game.class.getDeclaredMethod("getResultMessage", int.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, 1));
    }

    @SneakyThrows
    @Test
    public void sameChoicesBattleResultInDraw() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("draw", method.invoke(game, GUN, GUN));
    }

    @SneakyThrows
    @Test
    public void gunVsRockBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, GUN, ROCK));
    }

    @SneakyThrows
    @Test
    public void gunVsDynamiteBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, GUN, DYNAMITE));
    }

    @SneakyThrows
    @Test
    public void gunVsWolfBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, GUN, WOLF));
    }

    @SneakyThrows
    @Test
    public void gunVsSpongeBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, GUN, SPONGE));
    }

    @SneakyThrows
    @Test
    public void DynamiteVsRockBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, DYNAMITE, ROCK));
    }

    @SneakyThrows
    @Test
    public void DynamiteVsNukeBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, DYNAMITE,NUKE));
    }

    @SneakyThrows
    @Test
    public void dynamiteVsCockroachBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, DYNAMITE, COCKROACH));
    }

    @SneakyThrows
    @Test
    public void dynamiteVsWolfBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, DYNAMITE, WOLF));
    }

    @SneakyThrows
    @Test
    public void airVsBowlBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, AIR, BOWL));
    }

    @SneakyThrows
    @Test
    public void airVsFireBattleResultInTestWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("test won", method.invoke(game, AIR, FIRE));
    }

    @SneakyThrows
    @Test
    public void airVsMoonBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, AIR, MOON));
    }

    @SneakyThrows
    @Test
    public void airVsWomanBattleResultInComputerWon() {
        Method method = Game.class.getDeclaredMethod("getResult", RPS25Choice.class, RPS25Choice.class);
        method.setAccessible(true);
        assertEquals("computer won", method.invoke(game, AIR,WOMAN));
    }
}