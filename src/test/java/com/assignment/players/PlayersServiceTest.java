package com.assignment.players;

import com.assignment.players.exception.PlayersException;
import com.assignment.players.model.Player;
import com.assignment.players.service.PlayersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersServiceTest {

    @InjectMocks
    private PlayersServiceImpl playersService;

    @BeforeEach
    public void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        Method postConstruct = PlayersServiceImpl.class.getDeclaredMethod("init");
        postConstruct.setAccessible(true);
        postConstruct.invoke(playersService);
    }

    @Test
    void testGetPlayers() {
        Collection<Player> result = playersService.getAllPlayers();

        assertEquals(19370, result.size());
    }

    @Test
    void testGetPlayerById() {
        Player result = playersService.getPlayerById("aardsda01");

        assertEquals("aardsda01", result.getPlayerID());
    }

    @Test
    void testGetPlayerById_NotFoundError() {
        try {
            playersService.getPlayerById("notfound");
        } catch (PlayersException error) {
            assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
            assertEquals("Unable to find player with id: notfound", error.getMessage());
        }
    }

//    @Nested
//    public class NestedTestBlock {
//        @Test
//        void testLoadAllPlayersData_FileNotFoundError() throws Exception {
//            URL resource = getClass().getClassLoader().getResource("player.csv");
//            assert resource != null;
//            Path path = Paths.get(resource.getPath());
//            List<Player> players = new ArrayList<>();
//            when(CSVUtils.readCsvToBeanList(path, Player.class, players))
//                    .thenThrow(new RuntimeException("File not found"));
//
//            assertThrows(PlayersException.class, () -> playersService.loadAllPlayersData());
//        }
//
//        @Test
//        void testLoadAllPlayersData_WrongFileFormatError() throws Exception {
//            URL resource = getClass().getClassLoader().getResource("playerWrongFormat.csv");
//            assert resource != null;
//            Path path = Paths.get(resource.getPath());
//            List<Player> players = new ArrayList<>();
//            when(CSVUtils.readCsvToBeanList(path, Player.class, players))
//                    .thenThrow(new RuntimeException("wrong format"));
//
//            assertThrows(PlayersException.class, () -> playersService.loadAllPlayersData());
//        }
//    }
}
