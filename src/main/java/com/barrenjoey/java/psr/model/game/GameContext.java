package com.barrenjoey.java.psr.model.game;

import com.barrenjoey.java.psr.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private List<Player> players;
    private int totalNumberOfRoundsPlayed;
    private List<GameResult> gameResults;

    public GameContext() {
        this.gameResults = new ArrayList<>();
        this.totalNumberOfRoundsPlayed = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTotalNumberOfRoundsPlayed() {
        return totalNumberOfRoundsPlayed;
    }

    public void setTotalNumberOfRoundsPlayed(int totalNumberOfRoundsPlayed) {
        this.totalNumberOfRoundsPlayed = totalNumberOfRoundsPlayed;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }
}
