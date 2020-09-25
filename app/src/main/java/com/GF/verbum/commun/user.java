package com.GF.verbum.commun;

public class user {
    private String name;
    private int points;
    private int mode;
    private int difficulty;

    public user() {
    }

    public user(String name, int points, int mode, int difficulty) {
        this.name = name;
        this.points = points;
        this.mode = mode;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
