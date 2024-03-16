package com.assignment.players.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
public class PlayerService {

    public <T> List<T> readCsvToBeanList(Path path, Class clazz, List<T> list) throws Exception {
        HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
        ms.setType(clazz);

        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean cb = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withMappingStrategy(ms)
                    .build();

            list = cb.parse();
        }
        return list;
    }
}
