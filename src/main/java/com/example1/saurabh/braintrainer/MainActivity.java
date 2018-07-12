package com.example1.saurabh.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btngo;
    TextView sumView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timeView;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    int locationOfCorrect;
    int score = 0;
    TextView correcttext;
    TextView pointText;
    int noOfQn = 0;
    Button playButton;
    ArrayList<String> signarray = new ArrayList<String>();

    public void clickGo (View view) {

        btngo.setVisibility(View.INVISIBLE);
        sumView.setVisibility(View.VISIBLE);
        pointText.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        timeView.setVisibility(View.VISIBLE);

        clickPlay((Button) findViewById(R.id.playButton));

    }

    public void clickPlay(View view) {

        pointText.setText("0/0");
        timeView.setText("30s");
        correcttext.setText("");
        playButton.setVisibility(View.INVISIBLE);
        score = 0;
        noOfQn = 0;

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                timeView.setText("0s");
                correcttext.setText("Your score:" + Integer.toString(score) + "/" + Integer.toString(noOfQn));
                playButton.setVisibility(View.VISIBLE);

            }
        }.start();


    }

    public void generateQuestion() {

        Random r =new Random();

        int a = r.nextInt(21);
        int b = r.nextInt(21);
        int s = r.nextInt(4);
        int incorrectAns;
        int ans;

        arrayList.clear();

        sumView.setText(Integer.toString(a) + " "+ signarray.get(s) + " " + Integer.toString(b));

        locationOfCorrect = r.nextInt(4);

        if (signarray.get(s) == "+")
            ans = a + b ;
        else if (signarray.get(s) == "-")
            ans = a - b;
        else if (signarray.get(s) == "*")
            ans = a * b;
        else
            ans = a / b;

        for (int i = 0; i < 4; i++){

            if (i == locationOfCorrect)
                arrayList.add(ans);
            else {
                incorrectAns = r.nextInt(401);

                while (incorrectAns == ans)
                    incorrectAns = r.nextInt(401);
                arrayList.add(incorrectAns);

            }

        }

        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));

    }

    public void chooseAnswer (View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrect))){

            score++;
            correcttext.setText("Correct!");

        }
        else
            correcttext.setText("Incorrect!");

        noOfQn++;
        pointText.setText(Integer.toString(score) + "/" + Integer.toString(noOfQn));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngo = (Button) findViewById(R.id.goButton);
        sumView = (TextView) findViewById(R.id.sumView);
        correcttext = (TextView) findViewById(R.id.Correcttext);
        pointText = (TextView) findViewById(R.id.pointsView);
        timeView = (TextView) findViewById(R.id.timeView);
        playButton = (Button) findViewById(R.id.playButton);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        signarray.add("+");
        signarray.add("-");
        signarray.add("*");
        signarray.add("/");

    }
}
