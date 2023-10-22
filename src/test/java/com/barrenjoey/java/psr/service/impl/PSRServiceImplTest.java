package com.barrenjoey.java.psr.service.impl;

import com.barrenjoey.java.psr.model.game.GameChoice;
import com.barrenjoey.java.psr.model.game.GameResult;
import com.barrenjoey.java.psr.model.player.GamePlayer;
import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.PlayerState;
import com.barrenjoey.java.psr.model.player.PlayerType;
import com.barrenjoey.java.psr.service.InputService;
import com.barrenjoey.java.psr.service.PSRService;
import com.barrenjoey.java.psr.service.ResultsService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.barrenjoey.java.psr.model.game.GameStatus.COMPLETED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PSRServiceImplTest {
    private final InputService inputService = new ScannerInputService();
    private final ResultsService resultsService = new ResultPublisher();
    private final PSRService psrService = new PSRServiceImpl(inputService, resultsService);

    @Test
    void playTwoPlayers() {
        GamePlayer gamePlayerA = new GamePlayer(new Player("A", PlayerType.HUMAN), GameChoice.ROCK);
        GamePlayer gamePlayerB = new GamePlayer(new Player("B", PlayerType.HUMAN), GameChoice.PAPER);
        List<GameResult> play = psrService.play(List.of(gamePlayerB, gamePlayerA), 1);
        assertEquals(1, play.size());
        assertEquals(COMPLETED, play.get(0).gameStatus());
        assertEquals(PlayerState.WIN, play.get(0).
                playerResults().stream()
                .filter(playerResult -> playerResult.gamePlayer().player().name().equals("B"))
                .findFirst().get().state());
    }

    @Test
    void playThreePlayers() {
        GamePlayer gamePlayerA = new GamePlayer(new Player("A", PlayerType.HUMAN), GameChoice.ROCK);
        GamePlayer gamePlayerB = new GamePlayer(new Player("B", PlayerType.HUMAN), GameChoice.PAPER);
        GamePlayer gamePlayerC = new GamePlayer(new Player("C", PlayerType.HUMAN), GameChoice.SCISSORS);
        List<GameResult> play = psrService.play(List.of(gamePlayerB, gamePlayerA, gamePlayerC), 1);
        assertEquals(3, play.size());
        play.forEach(gameResult -> {
            assertEquals(COMPLETED, gameResult.gameStatus());
        });
    }

    @Test
    void playFourPlayers() {
        GamePlayer gamePlayerA = new GamePlayer(new Player("A", PlayerType.HUMAN), GameChoice.ROCK);
        GamePlayer gamePlayerB = new GamePlayer(new Player("B", PlayerType.HUMAN), GameChoice.PAPER);
        GamePlayer gamePlayerC = new GamePlayer(new Player("C", PlayerType.HUMAN), GameChoice.SCISSORS);
        GamePlayer gamePlayerD = new GamePlayer(new Player("D", PlayerType.HUMAN), GameChoice.ROCK);
        List<GameResult> play = psrService.play(List.of(gamePlayerB, gamePlayerA, gamePlayerC, gamePlayerD), 1);
        assertEquals(6, play.size());
    }


    @Test
    void chooseOption() {
        Player player = new Player("A", PlayerType.MACHINE);
        GamePlayer gamePlayer = psrService.chooseOption(player);
        assertEquals("A", gamePlayer.player().name());
        assertTrue(gamePlayer.gameChoice().getId() <= 3 && gamePlayer.gameChoice().getId()>=1);
    }
}