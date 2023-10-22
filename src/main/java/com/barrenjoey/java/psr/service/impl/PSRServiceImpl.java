package com.barrenjoey.java.psr.service.impl;

import com.barrenjoey.java.psr.model.game.GameChoice;
import com.barrenjoey.java.psr.model.game.GameResult;
import com.barrenjoey.java.psr.model.game.GameStatus;
import com.barrenjoey.java.psr.model.player.*;
import com.barrenjoey.java.psr.service.InputService;
import com.barrenjoey.java.psr.service.PSRService;
import com.barrenjoey.java.psr.service.ResultsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.barrenjoey.java.psr.model.game.GameStatus.COMPLETED;
import static com.barrenjoey.java.psr.model.game.GameStatus.TIE;

public class PSRServiceImpl implements PSRService {

    public final static Logger logger = Logger.getLogger(PlayerServiceImpl.class.getName());

    private final InputService inputService;

    private final ResultsService resultsService;

    public PSRServiceImpl(InputService inputService, ResultsService resultsService) {
        this.inputService = inputService;
        this.resultsService = resultsService;
    }

    @Override
    public List<GameResult> play(List<GamePlayer> gamePlayers, int roundNumber) {
        if (gamePlayers.size() < 2) {
            throw new RuntimeException("There should be atleast 2 players !!");
        }
        List<GameResult> gameResults = new ArrayList<>();
        for (int i = 0; i < gamePlayers.size(); i++) {
            for (int j = i + 1; j < gamePlayers.size(); j++) {
                GameResult gameResult = fight(gamePlayers.get(i), gamePlayers.get(j), roundNumber);
                resultsService.publishResult(gameResult);
                gameResults.add(gameResult);
            }
        }
        return gameResults;
    }

    private GameResult fight(GamePlayer playerOne, GamePlayer playerTwo, int roundNumber) {
        GameChoice p1choice = playerOne.gameChoice();
        GameChoice p2choice = playerTwo.gameChoice();
        if (p1choice.getId() == p2choice.getId()) {
            return new GameResult(TIE, getPlayerResults(TIE, playerOne, playerTwo), roundNumber);
        } else if (p1choice.getWinsOver().contains(p2choice.getId())) {
            return new GameResult(GameStatus.COMPLETED, getPlayerResults(COMPLETED, playerOne, playerTwo), roundNumber);
        } else if (p2choice.getWinsOver().contains(p1choice.getId())) {
            return new GameResult(GameStatus.COMPLETED, getPlayerResults(COMPLETED, playerTwo, playerOne), roundNumber);
        } else {
            logger.log(Level.SEVERE, "");
            throw new RuntimeException("Error, while resolving Players : missing fight Information !!");
        }

    }

    private List<PlayerResult> getPlayerResults(GameStatus gameStatus, GamePlayer winner, GamePlayer loser) {
        switch (gameStatus) {
            case TIE -> {
                return List.of(new PlayerResult(winner, PlayerState.TIE), new PlayerResult(loser, PlayerState.TIE));
            }
            case COMPLETED -> {
                return List.of(new PlayerResult(winner, PlayerState.WIN), new PlayerResult(loser, PlayerState.LOSS));
            }
            default -> {
                throw new RuntimeException("");
            }
        }
    }

    @Override
    public GamePlayer chooseOption(Player player) {
        if (player.playerType() == PlayerType.MACHINE) {
            return new GamePlayer(player, GameChoice.randomChoice());
        } else {
            logger.log(Level.INFO, "Player : " + player.name() + " choose an Id from Options:");
            logger.log(Level.INFO, Arrays.stream(GameChoice.values())
                    .map(GameChoice::toString).collect(Collectors.joining(",")));
            int option = inputService.readInt();
            return new GamePlayer(player, GameChoice.fromId(option));
        }
    }
}
