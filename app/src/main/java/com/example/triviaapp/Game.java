package com.example.triviaapp;

public class Game {
    // Question(question TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT, correctAnswer TEXT, pic INT)");

    private int codeSuject;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String correctAns;
    private int pic;


    public Game(int codeSuject, String question, String ans1, String ans2, String ans3, String ans4, String correctAns, int pic) {
        this.codeSuject = codeSuject;
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.correctAns = correctAns;
        this.pic = pic;
    }

    public Game(String question, String ans1, String ans2, String ans3, String ans4, String correctAns, int pic) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.correctAns = correctAns;
        this.pic = pic;
    }

    public int getCodeSuject() {
        return codeSuject;
    }

    public String getQuestion() {
        return question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public int getPic() {
        return pic;
    }
}
