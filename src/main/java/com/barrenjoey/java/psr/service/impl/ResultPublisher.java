package com.barrenjoey.java.psr.service.impl;

import com.barrenjoey.java.psr.model.game.GameContext;
import com.barrenjoey.java.psr.model.game.GameResult;
import com.barrenjoey.java.psr.model.game.GameStatus;
import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.PlayerResult;
import com.barrenjoey.java.psr.model.player.PlayerState;
import com.barrenjoey.java.psr.service.ResultsService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ResultPublisher implements ResultsService {

    public final static Logger logger = Logger.getLogger(ResultPublisher.class.getName());

    @Override
    public void publishResult(GameResult gameResult) {
        GameStatus gameStatus = gameResult.gameStatus();
        logger.log(Level.INFO, "Round number:" + gameResult.roundNumber() + " Game Status: " + gameStatus + " Player Results: " +
                gameResult.playerResults().stream().map(PlayerResult::toString).collect(Collectors.joining(",")));
    }

    @Override
    public void aggregate(GameContext context) {
        logger.log(Level.INFO, "Total Rounds : " + context.getTotalNumberOfRoundsPlayed());
        context.getPlayers().forEach(player -> {
            logger.log(Level.INFO, "Player: " + player.name() + " Wins: " + getByState(player, PlayerState.WIN, context.getGameResults())
                    + " Ties: " + getByState(player, PlayerState.TIE, context.getGameResults()) + " Losses: " + getByState(player, PlayerState.LOSS, context.getGameResults()));
        });
    }

    private int getByState(Player player, PlayerState playerState, List<GameResult> gameResults) {
        int total = 0;
        for (GameResult gameResult : gameResults) {
            for (PlayerResult playerResult : gameResult.playerResults()) {
                if (playerResult.gamePlayer().player().equals(player) && playerResult.state() == playerState) {
                    total++;
                }
            }
        }
        return total;
    }
}
