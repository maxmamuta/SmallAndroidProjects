package com.makasart.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Maxim on 09.09.2016.
 */
public class CheatActivity extends AppCompatActivity {

    private TextView mAnswerTextView;
    private TextView mBuildVersionTextView;

    public static final String EXTRA_ANSWER_SHOW = "com.makasart.geoquiz.answer_shown";

    private Button mCheatButton;

    private boolean IsTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        SetAnswerShowResult(false);
        IsTrue = getIntent().getBooleanExtra(QuizActivity.IS_VALUE_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.set_answer);

        mCheatButton = (Button)findViewById(R.id.show_answer);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (IsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                SetAnswerShowResult(true);
            }
        });

        mBuildVersionTextView = (TextView)findViewById(R.id.version_build);
        mBuildVersionTextView.append("Api level"+Integer.toString(Build.VERSION.SDK_INT));
    }

    public void SetAnswerShowResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOW, isAnswerShow);
        setResult(RESULT_OK, data);
    }
}
