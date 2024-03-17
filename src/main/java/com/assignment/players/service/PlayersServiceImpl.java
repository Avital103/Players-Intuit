package com.assignment.players.service;

import com.assignment.players.exception.PlayersException;
import com.assignment.players.model.Player;
import com.assignment.players.util.CSVUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
@Slf4j
public class PlayersServiceImpl implements PlayersService {

    private final Map<String, Player> playersData = new HashMap<>();
    String fileName = "player.csv";

    @PostConstruct
    private void init() {
        loadAllPlayersData();
    }

    private void loadAllPlayersData() {
        try {
            log.info("Start loading players info");
            URL resource = getClass().getClassLoader().getResource(fileName);
            assert resource != null;
            Path path = Paths.get(resource.getPath());
            List<Player> players = new ArrayList<>();
            List<Player> allPlayers = CSVUtils.readCsvToBeanList(path, Player.class, players);
            for (Player player : allPlayers) {
                playersData.put(player.getPlayerID(), player);
            }
            log.info("Successfully loaded players info");
        } catch (Exception error) {
            log.error("fail to load file: " + error.getMessage());
            throw new PlayersException(INTERNAL_SERVER_ERROR, "fail to load file: " + error.getMessage());
        }
    }

    public Collection<Player> getAllPlayers() {
        return playersData.values();
    }

    public Player getPlayerById(String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            log.error("No player id found");
            throw new PlayersException(BAD_REQUEST, "Missing player id");
        }

        Player playerData = playersData.get(playerId);
        if (playerData == null) {
            log.error("No player found for: " + playerId);
            throw new PlayersException(NOT_FOUND, "Unable to find player with id: " + playerId);
        }

        return playerData;
    }

}
