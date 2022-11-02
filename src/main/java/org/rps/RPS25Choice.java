package org.rps;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.stream.Collectors;

public enum RPS25Choice {
    GUN,
    DYNAMITE,
    NUKE,
    LIGHTING,
    DEVIL,
    DRAGON,
    ALIEN,
    WATER,
    BOWL,
    AIR,
    MOON,
    PAPER,
    SPONGE,
    WOLF,
    COCKROACH,
    TREE,
    MAN,
    WOMAN,
    MONKEY,
    SNAKE,
    AXE,
    SCISSORS,
    FIRE,
    SUN,
    ROCK;

    static final int lastIndex = RPS25Choice.values().length - 1;
    static final int victoryGap = SUN.ordinal() - SPONGE.ordinal();
    static final Random random = new Random();

    public static int battle(RPS25Choice choice1, RPS25Choice choice2) {

        int ordinal1 = choice1.ordinal();
        int ordinal2 = choice2.ordinal();

        if (ordinal2 == ordinal1) return 0;
        if (ordinal1 > ordinal2) {
            if (ordinal1 - ordinal2 <= victoryGap) return 1;
        } else {
            var i = victoryGap - ordinal1;
            if (ordinal2 + i >= 24) return 1;
        }
        return -1;
    }

    public int battle(RPS25Choice choice) {
        return battle(this, choice);
    }

    public static RPS25Choice randomChoice() {
        int i = random.nextInt(lastIndex);
        return RPS25Choice.values()[i];
    }

    private static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>(lastIndex + 1);
        Arrays.stream(RPS25Choice.values()).forEach(s -> map.put(s.toString(), s.victoryCasesString()));
        return map;
    }

    public static JTable jTable(){
        var model = getTableModel();
        return new JTable(model);
    }

    private static DefaultTableModel getTableModel() {
        var map = getMap();

        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Key", "Value" }, 0
        );
        for (Map.Entry<?,?> entry : map.entrySet()) {
            model.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
        return model;
    }

    public String victoryCasesString() {
        return Arrays.stream(RPS25Choice.values()).filter(choice1 -> battle(this, choice1) == 1).map(RPS25Choice::toString).collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
    public static Optional<RPS25Choice> optionalValueOf(String str){
        try {
            return Optional.of(valueOf(str.toUpperCase()));
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }
}
