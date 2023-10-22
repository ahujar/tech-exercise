package com.barrenjoey.java.psr.model.player;

public enum PlayerType {
    HUMAN(1), MACHINE(2);

    PlayerType(int id) {
        this.id = id;
    }

    private int id;

    public static PlayerType fromId(int id) {
        if (id == 2) {
            return MACHINE;
        }
        return HUMAN;
    }

}
