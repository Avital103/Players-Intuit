package com.assignment.players.service;

import com.assignment.players.exceptions.PlayerException;
import com.assignment.players.modal.Player;
import com.assignment.players.util.CSVUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final Map<String, Player> playersData = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        loadAllPlayersData();
    }

    private void loadAllPlayersData() {
        try {
            log.info("Start loading players info");
            URL resource = getClass().getClassLoader().getResource("player.csv");
            assert resource != null;
            Path path = Paths.get(resource.getPath());
            List<Player> players = new ArrayList<>();
            List<Player> allPlayers = CSVUtils.readCsvToBeanList(path, Player.class, players);
            for (Player player : allPlayers) {
                playersData.put(player.getPlayerID(), player);
            }
            log.info("Successfully loaded players info");
        } catch (Exception error){
            log.error("fail to call file");
            throw new PlayerException("ready file failed: " + error.getMessage());
        }
    }

    public Collection<Player> getAllPlayers() {
        return playersData.values();
    }


    public Player getPlayerById(String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            log.error("No player id found");
            return null;
        }

        Player playerData = playersData.get(playerId);
        if (playerData == null) {
            log.error("No player found for: " + playerId);
            throw new ResponseStatusException(NOT_FOUND, "Unable to find player with id: " + playerId);
        }

        return playerData;
    }

}
