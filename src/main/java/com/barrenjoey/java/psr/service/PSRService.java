package com.barrenjoey.java.psr.service;

import com.barrenjoey.java.psr.model.game.GameResult;
import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.GamePlayer;

import java.util.List;

public interface PSRService {
    List<GameResult> play(List<GamePlayer> gamePlayers, int roundNumber);

    GamePlayer chooseOption(Player player);
}
