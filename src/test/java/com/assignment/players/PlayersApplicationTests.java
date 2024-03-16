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
import java.util.Random;

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

    private List<Player> configureMocks(int numberOfPlayers) throws Exception {
        List<Player> playersResponse = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            String playerId = "player" + i;
            int birthYear = 1877;
            int birthMonth = 4;
            int birthDay = 15;
            String birthCountry = "USA";
            String birthState = "PA";
            String birthCity = "Latrobe";
            int debutYear = 1957;
            int debutMonth = 1;
            int debutDay = 6;
            String finalGameCountry = "USA";
            String finalGameState = "FL";
            String finalGameCity = "Fort Lauderdale";
            String nameFirst = "Ed";
            String nameLast = "Abbaticchio";
            String nameGiven = "Edward James";
            int height = 170;
            int weight = 71;
            String bats = "R";
            String throwsHand = "R";
            String debut = "1897-09-04";
            String finalGame = "1910-09-15";
            String retroID = "abbae101";
            String bbrefID = "abbated01";

            Player player = new Player(playerId, birthYear, birthMonth, birthDay, birthCountry, birthState, birthCity,
                    debutYear, debutMonth, debutDay, finalGameCountry, finalGameState, finalGameCity,
                    nameFirst, nameLast, nameGiven, height, weight, bats, throwsHand, debut, finalGame,
                    retroID, bbrefID);
            playersResponse.add(player);
        }

        Path path = Paths.get("/Users/aarvivo/Documents/player.csv");
        List<Player> players = new ArrayList<>();
        when(playerService.readCsvToBeanList(path, Player.class, players)).thenReturn(playersResponse);
        return playersResponse;
    }


    @Test
    void testGetPlayers() throws Exception {
        List<Player> players = configureMocks(3);
        List<Player> result = playersController.getPlayers();

        assertEquals(3, result.size());
        assertEquals(players.get(0), result.get(0));
        assertEquals(players.get(1), result.get(1));
        assertEquals(players.get(2), result.get(2));
    }

    @Test
    void testGetPlayerById() throws Exception {
        List<Player> players = configureMocks(4);
        Player result = playersController.getPlayerById(players.get(3).getPlayerID());

        assertEquals(players.get(3), result);
    }

    @Test
    void testGetPlayerByIdNotFound() throws Exception {
        List<Player> players = configureMocks(4);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            playersController.getPlayerById("NotFound");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Unable to find player", exception.getReason());
    }
}
