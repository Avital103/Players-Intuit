package com.assignment.players.controller;

import com.assignment.players.modal.Player;
import com.assignment.players.service.PlayerService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api("Get Players APIs")
@RequestMapping("api/players")
public class PlayersController {

    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getPlayers() throws Exception {
        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        return playerService.readCsvToBeanList(path, Player.class, players);
    }

    @GetMapping("/{playerID}")
    public Player getPlayerById(@PathVariable String playerID) throws Exception {
        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        List<Player> allPlayers = playerService.readCsvToBeanList(path, Player.class, players);
        for (Player player : allPlayers) {
            if (player.getPlayerID().equals(playerID)) {
                return player;
            }
        }
        throw new ResponseStatusException(NOT_FOUND, "Unable to find player");
    }

}
