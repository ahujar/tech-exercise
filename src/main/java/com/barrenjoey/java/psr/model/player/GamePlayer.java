package com.barrenjoey.java.psr.model.player;

import com.barrenjoey.java.psr.model.game.GameChoice;

public record GamePlayer(Player player, GameChoice gameChoice) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePlayer that)) return false;

        if (!player.equals(that.player)) return false;
        return gameChoice == that.gameChoice;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + gameChoice.hashCode();
        return result;
    }
}
