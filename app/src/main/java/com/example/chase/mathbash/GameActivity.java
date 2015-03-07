package com.example.chase.mathbash;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextWatcher;
import android.text.Editable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.R.*;

public class GameActivity extends ActionBarActivity {

    private LocationTimedProblemList tpl;
    private EditText answer;
    private TextView score;
    private HealthBar healthBar;
    private int health;
    private int scoreInt;
    private int width;
    private int height;

    private final int MAX_PROBLEMS = 5; //Maximum problems on screen
    private final int TIMER_INTERVAL = 20; //Time between updates in milliseconds
    private final int PROBLEM_LIFETIME = 20000; //Time that problems are on screen, in milliseconds
    private final int PROBLEM_DELAY = 350; //Time between problem creation, in milliseconds //Currently causes bugs if not 0
    private final int PROBLEM_SCORE_MINIMUM = 10; //Minimum score per problem
    private int[] problemIds = {R.id.problem1, R.id.problem2, R.id.problem3, R.id.problem4, R.id.problem5};
    private TextView[] problems;
    private int ANSWER_YPOS;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Point windowSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(windowSize);
        width = windowSize.x;
        height = windowSize.y;
        ANSWER_YPOS=height/3;

        //lifeForce=(ProgressBar)findViewById(R.id.lifeForce);
        score = (TextView) findViewById(R.id.score);
        answer = (EditText) findViewById(R.id.answer);

        answer.setY(ANSWER_YPOS);
        health=100;
        healthBar=new HealthBar(this, height, width);
        tpl = new LocationTimedProblemList(MAX_PROBLEMS);
        tpl.addProblem(MAX_PROBLEMS);

        problems = new TextView[MAX_PROBLEMS];
        for (int i = 0; i < problems.length; i++) {
            problems[i] = (TextView) findViewById(problemIds[i]);
            problems[i].setVisibility(View.INVISIBLE);

        }



        healthBar.setHealth(health);

        final Handler h = new Handler();
        h.postDelayed( new Runnable() {
            int count = PROBLEM_DELAY;
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                tpl.decrementAll((double)(TIMER_INTERVAL) / PROBLEM_LIFETIME);
                LocationTimedProblemList.updateProblems(tpl);
                drawAllProblems(tpl);

                count += TIMER_INTERVAL;
                if (count >= PROBLEM_DELAY && tpl.addProblem(MAX_PROBLEMS)) {
                    count = 0;
                }
                h.postDelayed(this, TIMER_INTERVAL);
            }
        }, 0);
        answer.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (getAnswer(answer)) {
                    incrementHealth();
                    answer.setText("");
                    scoreInt += PROBLEM_SCORE_MINIMUM;
                    score.setText("Score: " + scoreInt);

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


    public void endGame(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void drawAllProblems(LocationTimedProblemList tpl){
        int count=0;
        for (Problem p : tpl) {
            LocationTimedProblem tp = (LocationTimedProblem)p;
            drawProblem(tp, count);
            count+=1;
        }
        LocationTimedProblemList.updateProblems(tpl);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean drawProblem(LocationTimedProblem prob, int count){
        String p = prob.toString().split(",")[0];
        Drawable shape = getResources().getDrawable(R.drawable.gradient_box);
        float ypos=(float)(height-(float)(height * prob.life()));
        problems[count].setText(p);
        problems[count].setBackground(shape);
        problems[count].setVisibility(View.VISIBLE);
        problems[count].setX((float)((prob.getX() + 0.5) * width/7.0));
        problems[count].setY((height-(float)(height * prob.life())));

        if (ypos>=ANSWER_YPOS){
            decrementHealth();
            prob=tpl.removeReplace(count);
            drawProblem(prob, count);
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    public void decrementHealth(){
        health-=10;
        if (health<=0){
            endGame();
        }
        healthBar.setHealth(health);
    }

    public void incrementHealth() {
        if (health < 100) {
            health += 1;
            healthBar.setHealth(health);
        }
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

    public void drawHealth(){
        float life=(float)(health)/100;

    }
}
