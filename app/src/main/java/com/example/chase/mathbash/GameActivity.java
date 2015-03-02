package com.example.chase.mathbash;

import android.text.TextWatcher;
import android.text.Editable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;


public class GameActivity extends ActionBarActivity {

    private TimedProblemList tpl;
    private EditText answer;
    private TextView score;
    private TextView problem;
    private int scoreInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        answer = (EditText) findViewById(R.id.answer);
        score = (TextView) findViewById(R.id.score);
        problem = (TextView) findViewById(R.id.problem);

        tpl = new TimedProblemList(10);
        problem.setText(tpl.getProblem().toString());

        final Handler h = new Handler();
        h.postDelayed( new Runnable() {
            @Override
            public void run() {
                tpl.decrementAll(0.05);
                problem.setText(tpl.getProblem().toString());
                h.postDelayed(this, 1000);
            }
        }, 0);
        answer.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (getAnswer(answer)) {
                    scoreInt += 10;
                    score.setText("Score: " + scoreInt);
                    answer.setText("");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }

    public boolean getAnswer(EditText answer){
        try {
            return tpl.check(answer.getText().toString());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
