package com.assignment.players.service;

import com.assignment.players.modal.Player;
import com.assignment.players.util.CSVUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class PlayerService {

    private final Map<String, Player> playersData = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        loadAllPlayersData();
    }

    private void loadAllPlayersData() throws Exception {
        URL resource = getClass().getClassLoader().getResource("player.csv");
        assert resource != null;
        Path path = Paths.get(resource.getPath());
        List<Player> players = new ArrayList<>();
        List<Player> allPlayers = CSVUtils.readCsvToBeanList(path, Player.class, players);
        for (Player player : allPlayers) {
            playersData.put(player.getPlayerID(), player);
        }
    }

    public Collection<Player> getAllPlayers() {
        return playersData.values();
    }


    public Player getPlayerById(String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            return null;
        }

        Player playerData = playersData.get(playerId);
        if (playerData == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find player with id: " + playerId);
        }

        return playerData;
    }

}
