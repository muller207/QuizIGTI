package com.example.igti.quiz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    int id;
    String content;
    boolean answer;

    public boolean getAnswer() {
        return this.answer;
    }
}
