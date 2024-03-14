package com.assignment.players.service;

import com.assignment.players.modal.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class PlayerService {

    public List<Player> readCsvFile(String filePath) throws IOException {
        List<Player> players = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Player player = PlayerMapper.INSTANCE.csvRecordToPlayer(csvRecord);
                players.add(player);
            }
        }

        return players;
    }
}
