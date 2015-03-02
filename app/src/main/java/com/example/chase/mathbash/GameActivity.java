package com.example.chase.mathbash;

import android.text.TextWatcher;
import android.text.Editable;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class GameActivity extends ActionBarActivity {

    private TimedProblemList tpl;
    private EditText answer;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        answer = (EditText) findViewById(R.id.answer);
        score = (TextView) findViewById(R.id.scoreNum);
        tpl = new TimedProblemList(10);
        answer.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                getAnswer(answer);
                score.setText(Integer.toString(tpl.score()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }

    public void getAnswer(EditText answer){
        try {
            tpl.check(answer.getText().toString());
        } catch (NullPointerException e) {}
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
