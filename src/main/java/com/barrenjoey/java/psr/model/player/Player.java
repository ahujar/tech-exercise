package com.barrenjoey.java.psr.model.player;

import java.util.Objects;

public record Player(String name, PlayerType playerType) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;

        if (!Objects.equals(name, player.name)) return false;
        return playerType == player.playerType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (playerType != null ? playerType.hashCode() : 0);
        return result;
    }
}
