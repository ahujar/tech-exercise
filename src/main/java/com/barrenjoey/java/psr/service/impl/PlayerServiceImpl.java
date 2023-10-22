package com.barrenjoey.java.psr.service.impl;

import com.barrenjoey.java.psr.model.player.Player;
import com.barrenjoey.java.psr.model.player.PlayerType;
import com.barrenjoey.java.psr.service.InputService;
import com.barrenjoey.java.psr.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class PlayerServiceImpl implements PlayerService {

    public final static Logger logger = Logger.getLogger(PlayerServiceImpl.class.getName());

    private final InputService inputService;

    public PlayerServiceImpl(InputService inputService) {
        this.inputService = inputService;
    }

    @Override
    public List<Player> initPlayers() {
        List<Player> players = new ArrayList<>();
        logger.log(Level.INFO, "Enter the number of Players: ");
        int totalPlayers = inputService.readInt();
        IntStream.range(0, totalPlayers).forEach(i -> {
            logger.log(Level.INFO, "Enter name of Player " + (i + 1) + ":");
            String name = inputService.readString();
            logger.log(Level.INFO, "Enter the PlayerType : 1 for HUMAN, 2 for MACHINE : ");
            int playerType = inputService.readInt();
            Player player = new Player(name, PlayerType.fromId(playerType));
            players.add(player);
        });
        return players;
    }
}
