package com.assignment.players.service;

import com.assignment.players.modal.Player;

import java.util.Collection;

public interface PlayerService {
    Collection<Player> getAllPlayers();

    Player getPlayerById(String playerId);
}
