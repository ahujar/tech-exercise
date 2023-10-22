package com.barrenjoey.java.psr.manager;

import com.barrenjoey.java.psr.model.game.GameContext;
import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.GamePlayer;
import com.barrenjoey.java.psr.service.PSRService;
import com.barrenjoey.java.psr.service.PlayerService;

import java.util.List;
import java.util.stream.Collectors;

public class GameManager {
    private final PlayerService playerService;
    private final PSRService psrService;

    public GameManager(PlayerService playerService, PSRService psrService) {
        this.playerService = playerService;
        this.psrService = psrService;
    }

    public GameContext init() {
        List<Player> players = playerService.initPlayers();
        GameContext context = new GameContext();
        context.setPlayers(players);
        return context;
    }

    public GameContext simulate(GameContext context) {
        int roundNumber = context.getTotalNumberOfRoundsPlayed() + 1;
        List<GamePlayer> gamePlayers = context.getPlayers().stream().map(psrService::chooseOption).collect(Collectors.toList());
        context.getGameResults().addAll(psrService.play(gamePlayers, roundNumber));
        context.setTotalNumberOfRoundsPlayed(roundNumber);
        return context;
    }

}
