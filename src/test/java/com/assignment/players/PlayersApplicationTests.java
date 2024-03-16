package com.assignment.players;

import com.assignment.players.controller.PlayersController;
import com.assignment.players.modal.Player;
import com.assignment.players.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlayersControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayersController playersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Player player1 = new Player("player1", 1877, 4, 15, "USA", "PA", "Latrobe", 1957,
            1, 6, "USA", "FL", "Fort Lauderdale",
            "Ed", "Abbaticchio", "Edward James", 170, 71, "R",
            "R", "1897-09-04", "1910-09-15", "abbae101", "abbated01");
    Player player2 = new Player("player2", 1877, 4, 15, "USA", "PA", "Latrobe", 1957,
            1, 6, "USA", "FL", "Fort Lauderdale",
            "Ed", "Abbaticchio", "Edward James", 170, 71, "R",
            "R", "1897-09-04", "1910-09-15", "abbae101", "abbated01");

    @Test
    void testGetPlayers() throws Exception {
        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        List<Player> playersResponse = new ArrayList<>();
        playersResponse.add(player1);
        playersResponse.add(player2);
        when(playerService.readCsvToBeanList(path, Player.class, players)).thenReturn(playersResponse);

        List<Player> result = playersController.getPlayers();

        assertEquals(2, result.size());
        assertEquals(player1.getPlayerID(), result.get(0).getPlayerID());
        assertEquals(player2.getPlayerID(), result.get(1).getPlayerID());
    }

    @Test
    void testGetPlayerById() throws Exception {
        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        List<Player> playersResponse = new ArrayList<>();
        playersResponse.add(player1);
        playersResponse.add(player2);
        when(playerService.readCsvToBeanList(path, Player.class, players)).thenReturn(playersResponse);

        Player result = playersController.getPlayerById(player1.getPlayerID());

        assertEquals(player1.getPlayerID(), result.getPlayerID());
    }

    @Test
    void testGetPlayerByIdNotFound() throws Exception {
        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerService.readCsvToBeanList(path, Player.class, players)).thenReturn(players);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            playersController.getPlayerById("NotFound");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Unable to find player", exception.getReason());
    }
}
