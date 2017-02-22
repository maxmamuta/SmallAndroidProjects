package com.makasart.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private Button mCheatButton;

    private TextView mQuestionTextView;

    boolean isLogged = false;
    boolean isCheater = false;

    public static final String TAG = "QuizActivity";
    public static final String Index = "index";
    public static final String CheatsArray = "CheatsArray";
    public static final String IS_VALUE_TRUE = "com.makasart.geoquiz.isTrueQuestion";

    private int mCurrentIndex = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private boolean mCheaterIS[] = new boolean[mQuestionBank.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNewCheaterIS();
        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(Index, mCurrentIndex);
            mCheaterIS = savedInstanceState.getBooleanArray(CheatsArray);
        }
        if (isLogged) {
            Log.d(TAG, "Method (OnCreate) start!");
        }
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestions();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheats);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean ExtraPut = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(IS_VALUE_TRUE, ExtraPut);
                startActivityForResult(i, 0);
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                isCheater = false;
                updateQuestions();
            }
        });

        mBackButton = (Button)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                isCheater = false;
                updateQuestions();
            }
        });
        updateQuestions();
    }

    private void updateQuestions()
    {
        try {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
            mQuestionTextView.setText(question);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            Log.e(TAG, "Index was out of bounds", e);
        }
        if (isLogged) {
            Log.d(TAG, "Update question for the #" + mCurrentIndex, new Exception());
        }
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if(mCheaterIS[mCurrentIndex])
            isCheater = true;
        if(isCheater){
            messageResId = R.string.junging_toast;
            Toast.makeText(QuizActivity.this, messageResId,
            Toast.LENGTH_SHORT).show();
        }
        else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                Toast.makeText(QuizActivity.this, messageResId,
                        Toast.LENGTH_SHORT).show();
            } else {
                messageResId = R.string.incorrect_toast;
                Toast.makeText(QuizActivity.this, messageResId,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (isLogged) {
            Log.d(TAG, "method (OnStart) created!");
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (isLogged) {
            Log.d(TAG, "method (OnPause) created");
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (isLogged) {
            Log.d(TAG, "method (OnResume) created");
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (isLogged) {
            Log.d(TAG, "method (OnStop) created");
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (isLogged) {
            Log.d(TAG, "method (OnDestroy) created");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Index, mCurrentIndex);
        savedInstanceState.putBooleanArray(CheatsArray, mCheaterIS);
        if (isLogged) {
            Log.i("MeAPP", "In (SaveInstanceState)");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data == null) {
            return;
        }
        if (isLogged) {
            Log.i("MeAPP", Integer.toString(requestCode));
        }
        if (isLogged) {
            Log.i("MeAPP", Integer.toString(resultCode));
        }
        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOW, false);
        mCheaterIS[mCurrentIndex] = isCheater;
    }

    public void getNewCheaterIS()
    {
        for(int i = 0; i < mQuestionBank.length; i++) {
            mCheaterIS[i]=false;
        }
    }
}
