package com.example.chase.mathbash;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextWatcher;
import android.text.Editable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;


import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.R.*;


public class GameActivity extends ActionBarActivity {

    private LocationTimedProblemList tpl;
    private EditText answer;
    private TextView score;
    private TextView problem;
    private int scoreInt;
    private int width;
    private int height;

    private final int MAX_PROBLEMS = 3; //Maximum problems on screen
    private final int TIMER_INTERVAL = 100; //Time between updates in milliseconds
    private final int PROBLEM_LIFETIME = 10000; //Time that problems are on screen, in milliseconds
    private final int PROBLEM_DELAY = 1000; //Time between problem creation, in milliseconds
    private final int PROBLEM_SCORE_MINIMUM = 10; //Minimum score per problem
    private int[] problemIds = {R.id.problem1, R.id.problem2,
            R.id.problem3, R.id.problem4, R.id.problem5};



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display display = getWindowManager().getDefaultDisplay();
        Point origin=new Point();
        display.getSize(origin);
        width = origin.x;
        height = origin.y;

        answer = (EditText) findViewById(R.id.answer);
        score = (TextView) findViewById(R.id.score);

        tpl = new LocationTimedProblemList(MAX_PROBLEMS);
        drawAllProblems(tpl);

        final Handler h = new Handler();
        h.postDelayed( new Runnable() {

            int count = 0;

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                tpl.decrementAll(TIMER_INTERVAL / PROBLEM_LIFETIME);
                drawAllProblems(tpl);
                count += TIMER_INTERVAL;
                if (count > PROBLEM_DELAY) {
                    tpl.addProblem(MAX_PROBLEMS);
                }
                h.postDelayed(this, TIMER_INTERVAL);
            }
        }, 0);
        answer.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (getAnswer(answer)) {
                    scoreInt += PROBLEM_SCORE_MINIMUM;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void drawAllProblems(LocationTimedProblemList tpl){
        int count=0;
        for (Problem p : tpl) {
            LocationTimedProblem tp = (LocationTimedProblem)p;
            if (count>=MAX_PROBLEMS){
                continue;
            }
            drawProblem(tp, count);
            count+=1;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void drawProblem(LocationTimedProblem prob, int count){
        String p = prob.toString().split(",")[0];
        Double position = prob.life();
        Drawable shape = getResources().getDrawable(R.drawable.gradient_box);
        problem = (TextView) findViewById(problemIds[count]);
        problem.setText(p);
        problem.setBackground(shape);
        problem.setX(width/prob.getX());
        //problem.setY(height*prob.getY());
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
