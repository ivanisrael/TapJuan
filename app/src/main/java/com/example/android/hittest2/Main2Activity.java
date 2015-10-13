package com.example.android.hittest2;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    private int score = 0;
    private int live = 3;
    static double speed = 1100;
    private static Button gg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //onClickButtonListener();

    }
    public void onClickButtonListener() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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

    public void addScore(View view){
        TextView num_text = (TextView) findViewById(R.id.text_num);
        int x = Integer.parseInt(num_text.getText().toString());

        int value = x%10;
        if (value == 1 || (x >9 && x <20)) {
            score += 1;
            displayScore(score);
            int y = score % 7;
            if(y == 0 && score <22){
                tapjuan(view);
            }

        }else {
            live -=1;
            if(live == 1){
                gg= (Button) findViewById(R.id.button_tap);
                gg.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Main2Activity.this, Main5Activity.class);
                                intent.putExtra("fscore", score);
                                startActivity(intent);
                            }
                        }
                );
            }
            displayLive(live);


        }


    }

    private void displayLive(int live){
        TextView live_text= (TextView) findViewById(R.id.text_live);
        live_text.setText("Life:  " + Integer.toString(live));
    }

    private void displayScore(int score){
        TextView score_text= (TextView) findViewById(R.id.text_score);
        score_text.setText("Score: "+ Integer.toString(score));
    }

    public void tapjuan(View view){
        Button b = (Button) findViewById(R.id.button_start);
        b.setVisibility(View.GONE);
        new Thread(
                new Runnable() {
                    public void run() {

                        new Timer().scheduleAtFixedRate(new TimerTask() {
                            public void run()   {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        TextView t = (TextView) findViewById(R.id.text_num);
                                        Random r = new Random();

                                        int num = r.nextInt(40);
                                        t.setText(String.format("%02d", num));
                                    }
                                });
                            }
                        }, 0, (long)speed);
                    }
                }).start();

    }
}
