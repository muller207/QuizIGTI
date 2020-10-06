package com.example.igti.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentIndex = 0;
    int rightAnswers = 0;
    ArrayList<Question> questions;
    TextView txTitle;
    TextView txQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("questions.txt");
            String qs = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            Type qType = new TypeToken<ArrayList<Question>>() {}.getType();
            Gson gson = new GsonBuilder().create();
            questions = gson.fromJson(qs, qType);
        } catch (IOException e){
            e.printStackTrace();
        }

        final MotionLayout motionLayout = (MotionLayout) findViewById(R.id.motion_base);

        txTitle = (TextView) findViewById(R.id.txTitle);
        txQuestion = (TextView) findViewById(R.id.txQuestion);
        nextQuestion();

        Button btTrue = (Button) findViewById(R.id.btTrue);
        Button btFalse = (Button) findViewById(R.id.btFalse);
        btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motionLayout.transitionToState(R.id.verdadeiro);
                checkAnswer(true);
            }
        });
        btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motionLayout.transitionToState(R.id.falso);
                checkAnswer(false);
            }
        });
    }

    protected void nextQuestion(){
        if(currentIndex==questions.size()) {
            Intent i = new Intent(MainActivity.this, ResultsActivity.class);
            i.putExtra("percent",(double) rightAnswers/questions.size());
            startActivity(i);

        }else{
            txTitle.setText("Pergunta " + String.valueOf(questions.get(currentIndex).getId()));
            txQuestion.setText(questions.get(currentIndex).getContent());
        }
    }

    protected void checkAnswer(boolean answer){
        if(answer == questions.get(currentIndex).getAnswer()) {
            Toast.makeText(getBaseContext(), "Acertou!", Toast.LENGTH_SHORT).show();
            rightAnswers++;
        }else{
            Toast.makeText(getBaseContext(), "Errou!", Toast.LENGTH_SHORT).show();
        }
        currentIndex++;
        nextQuestion();
    }
}
