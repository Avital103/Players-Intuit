package com.assignment.players.controller;

import com.assignment.players.exception.PlayersException;
import com.assignment.players.model.Player;
import com.assignment.players.service.PlayersService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Api("Get Players APIs")
@RequestMapping("api/players")
public class PlayersController {

    private final PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Player>> getPlayers() {
        try {
            return new ResponseEntity<>(playersService.getAllPlayers(), HttpStatus.OK);
        } catch (PlayersException error) {
            return new ResponseEntity<>(error.getStatusCode());
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String playerId) {
        try {
            return new ResponseEntity<>(playersService.getPlayerById(playerId), HttpStatus.OK);
        } catch (PlayersException error) {
            return new ResponseEntity<>(error.getStatusCode());
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
