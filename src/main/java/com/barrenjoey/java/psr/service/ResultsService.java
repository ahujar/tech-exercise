package com.barrenjoey.java.psr.service;

import com.barrenjoey.java.psr.model.game.GameContext;
import com.barrenjoey.java.psr.model.game.GameResult;

import java.util.List;

public interface ResultsService {
    void publishResult(GameResult gameResult);

    void aggregate(GameContext context);
}
