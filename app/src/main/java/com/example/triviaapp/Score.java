package com.example.triviaapp;

public class Score {
    private String subject;
    private String date;
    private int time;
    private int score;


    public Score(String subject, String date, int time, int score) {
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }
}
