package com.assignment.players.service;

import com.assignment.players.modal.Player;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deathYear", source = "deathYear", defaultValue = "0")
    @Mapping(target = "deathMonth", source = "deathMonth", defaultValue = "0")
    @Mapping(target = "deathDay", source = "deathDay", defaultValue = "0")
    Player csvRecordToPlayer(CSVRecord csvRecord);
}
