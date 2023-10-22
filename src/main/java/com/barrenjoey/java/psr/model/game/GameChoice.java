package com.barrenjoey.java.psr.model.game;

import java.util.*;

public enum GameChoice {
    ROCK(1, List.of(3)), PAPER(2, List.of(1)), SCISSORS(3, List.of(2));
    int id;
    List<Integer> winsOver;

    GameChoice(int id, List<Integer> winsOver) {
        this.id = id;
        this.winsOver = winsOver;
    }

    public static GameChoice randomChoice() {
        Random random = new Random();
        int id = random.nextInt(1, GameChoice.values().length + 1);
        return fromId(id);
    }

    public static GameChoice fromId(int id) {
        Optional<GameChoice> first = Arrays.stream(GameChoice.values()).filter(gameChoice -> gameChoice.getId() == id).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException("Game Choice not found with id:" + id));
    }

    public int getId() {
        return id;
    }

    public List<Integer> getWinsOver() {
        return winsOver;
    }

    @Override
    public String toString() {
        return "GameChoice{" +
                this.name() +
                ", id=" + id +
                ", winsOver=" + winsOver +
                '}';
    }
}
