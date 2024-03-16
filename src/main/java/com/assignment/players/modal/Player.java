package com.assignment.players.modal;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Player {

    @CsvBindByName(column = "playerID")
    private String playerID;

    @CsvBindByName(column = "birthYear")
    private int birthYear;

    @CsvBindByName(column = "birthMonth")
    private int birthMonth;

    @CsvBindByName(column = "birthDay")
    private int birthDay;

    @CsvBindByName(column = "birthCountry")
    private String birthCountry;

    @CsvBindByName(column = "birthState")
    private String birthState;

    @CsvBindByName(column = "birthCity")
    private String birthCity;

    @CsvBindByName(column = "deathYear")
    private int deathYear;

    @CsvBindByName(column = "deathMonth")
    private int deathMonth;

    @CsvBindByName(column = "deathDay")
    private int deathDay;

    @CsvBindByName(column = "deathCountry")
    private String deathCountry;

    @CsvBindByName(column = "deathState")
    private String deathState;

    @CsvBindByName(column = "deathCity")
    private String deathCity;

    @CsvBindByName(column = "nameFirst")
    private String nameFirst;

    @CsvBindByName(column = "nameLast")
    private String nameLast;

    @CsvBindByName(column = "nameGiven")
    private String nameGiven;

    @CsvBindByName(column = "weight")
    private int weight;

    @CsvBindByName(column = "height")
    private int height;

    @CsvBindByName(column = "bats")
    private String bats;

    @CsvBindByName(column = "throws")
    private String side;

    @CsvBindByName(column = "debut")
    private String debut;

    @CsvBindByName(column = "finalGame")
    private String finalGame;

    @CsvBindByName(column = "retroID")
    private String retroID;

    @CsvBindByName(column = "bbrefID")
    private String bbrefID;
}
