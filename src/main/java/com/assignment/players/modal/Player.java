package com.assignment.players.modal;

import lombok.Data;

@Data
public class Player {

    private String playerID;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private int deathYear;
    private int deathMonth;
    private int deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private int weight;
    private int height;
    private String bats;
    private String side;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}
