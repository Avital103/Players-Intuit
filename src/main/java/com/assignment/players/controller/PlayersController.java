package com.assignment.players.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Get Players APIs")
@RequestMapping("api/players")
public class PlayersController {

    @GetMapping()
    public String getPlayers() {
        return "hi";
    }

    @GetMapping("/{playerID}")
    public String getPlayerById(@PathVariable String playerID) {
        return playerID;
    }

}
