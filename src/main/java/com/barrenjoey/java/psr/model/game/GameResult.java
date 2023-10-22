package com.barrenjoey.java.psr.model.game;

import com.barrenjoey.java.psr.model.player.GamePlayer;
import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.PlayerResult;

import java.util.List;

public record GameResult (GameStatus gameStatus, List<PlayerResult> playerResults,int roundNumber) {

}
