package com.assignment.players;

import com.assignment.players.service.PlayerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.assignment.players.modal.Player;
import com.assignment.players.controller.PlayersController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PlayersControllerTest.class})
public class PlayersControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Player> playersData = new HashMap<>(0);

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayersController playersController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(playersController).build();

        for (int i = 1; i <= 4; i++) {
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
            playersData.put(player.getPlayerID(), player);
        }
    }

    @Test
    public void getPlayersSuccesses() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(playersData.values());
        String responseStr = String.valueOf(mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse());

        Collection<Player> jsonResponse = objectMapper.readValue(responseStr, new TypeReference<>() {
        });
        assertEquals(3, jsonResponse.size());
    }

    @Test
    public void getPlayerById() throws Exception {
        String playerId = "player1";
        when(playerService.getPlayerById(playerId)).thenReturn(playersData.get(playerId));
        String responseStr = String.valueOf(mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/players/" + playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse());

        Player player = objectMapper.readValue(responseStr, Player.class);
        assertEquals(playersData.get(playerId), player);
    }

    @Test
    public void getPlayerByIdNotFound() throws Exception {
        String playerId = "notfound";
        when(playerService.getPlayerById(playerId)).thenThrow(new ResponseStatusException(NOT_FOUND, "Unable to find player with id: " + playerId));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/players/" + playerId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Unable to find player with id: " + playerId));
        }
    }

    @Test
    public void getPlayerByIdInternalError() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/players/")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andReturn();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Required path variable 'id' is not present."));
        }

    }
}
