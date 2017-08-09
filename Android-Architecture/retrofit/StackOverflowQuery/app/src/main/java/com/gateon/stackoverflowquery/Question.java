package com.gateon.stackoverflowquery;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim on 8/9/2017.
 */

public class Question {
    public String title;
    public String body;

    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return title;
    }
}
