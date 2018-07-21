package com.example.jabari.myapplication.model;

import com.google.gson.annotations.SerializedName;


public class Questionnaire {
    @SerializedName("Question")
    String Question;
    @SerializedName("Answers")
    String[] Answers;

    public Questionnaire(String question, String[] answers) {
        Question = question;
        Answers = answers;
    }

    public String getQuestion() {
        return Question;
    }

    public String[] getAnswers() {
        return Answers;
    }
}
