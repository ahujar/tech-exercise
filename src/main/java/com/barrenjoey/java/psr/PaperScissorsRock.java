package com.barrenjoey.java.psr;

import com.barrenjoey.java.psr.manager.GameManager;
import com.barrenjoey.java.psr.model.game.GameContext;
import com.barrenjoey.java.psr.service.*;
import com.barrenjoey.java.psr.service.impl.PSRServiceImpl;
import com.barrenjoey.java.psr.service.impl.PlayerServiceImpl;
import com.barrenjoey.java.psr.service.impl.ResultPublisher;
import com.barrenjoey.java.psr.service.impl.ScannerInputService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaperScissorsRock {
    public final static Logger logger = Logger.getLogger(PaperScissorsRock.class.getName());
    private static InputService inputService;
    private static ResultsService resultsService;
    private static PlayerService playerService;
    private static PSRService psrService;
    private static GameManager manager;

    public static void main(String[] args) {
        initialize();
        try {
            boolean play = true;
            GameContext context = manager.init();
            while (play) {
                manager.simulate(context);
                logger.log(Level.INFO, "Do you wish to continue ? (y/n)");
                play = inputService.readBoolean();
            }
            logger.log(Level.INFO, "Aggregate Results for all rounds:");
            resultsService.aggregate(context);
        } finally {
            inputService.close();
        }

    }

    private static void initialize() {
        inputService = new ScannerInputService();
        resultsService = new ResultPublisher();
        playerService = new PlayerServiceImpl(inputService);
        psrService = new PSRServiceImpl(inputService, resultsService);
        manager = new GameManager(playerService, psrService);
    }
}
