package com.barrenjoey.java.psr.model.player;

import java.util.Objects;

public record PlayerResult(GamePlayer gamePlayer, PlayerState state) {
    @Override
    public String toString() {
        return " " + gamePlayer.player().name() + " used " + gamePlayer.gameChoice().name() + " " +
                "resulting in a " + state.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerResult that)) return false;

        if (!Objects.equals(gamePlayer, that.gamePlayer)) return false;
        return state == that.state;
    }

    @Override
    public int hashCode() {
        int result = gamePlayer != null ? gamePlayer.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
