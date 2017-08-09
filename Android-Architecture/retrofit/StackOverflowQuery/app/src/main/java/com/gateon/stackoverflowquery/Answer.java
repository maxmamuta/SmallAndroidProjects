package com.gateon.stackoverflowquery;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim on 8/9/2017.
 */

public class Answer {

    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    public int score;

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", accepted=" + accepted +
                ", score=" + score +
                '}';
    }
}
