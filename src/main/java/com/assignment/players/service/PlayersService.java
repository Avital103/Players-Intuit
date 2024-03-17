package com.assignment.players.service;

import com.assignment.players.model.Player;

import java.util.Collection;

public interface PlayersService {
    Collection<Player> getAllPlayers();

    Player getPlayerById(String playerId);
}
