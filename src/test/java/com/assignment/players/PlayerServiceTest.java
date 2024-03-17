package com.assignment.players;

import com.assignment.players.modal.Player;
import com.assignment.players.service.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    public void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        Method postConstruct = PlayerServiceImpl.class.getDeclaredMethod("init");
        postConstruct.setAccessible(true);
        postConstruct.invoke(playerService);
    }

    @Test
    void testGetPlayers() {
        Collection<Player> result = playerService.getAllPlayers();

        assertEquals(19370, result.size());
    }

    @Test
    void testGetPlayerById() {
        Player result = playerService.getPlayerById("aardsda01");

        assertEquals("aardsda01", result.getPlayerID());
    }

    @Test
    void testGetPlayerById_NotFoundError() {
        try {
            playerService.getPlayerById("notfound");
        } catch (ResponseStatusException error) {
            assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
            assertEquals("Unable to find player with id: notfound", error.getReason());
        }
    }
}
