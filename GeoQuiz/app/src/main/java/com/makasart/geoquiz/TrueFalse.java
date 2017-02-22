package com.makasart.geoquiz;

import android.util.Log;

/**
 * Created by Maxim on 04.09.2016.
 */
public class TrueFalse {
    private int mQuestion;

    boolean isLogged = true; //Логи логи

    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion)
    {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        if (isLogged) {
            Log.i("MeAPP", Integer.toString(mQuestion));
        }
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion()
    {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion)
    {
        mTrueQuestion = trueQuestion;
    }
}
