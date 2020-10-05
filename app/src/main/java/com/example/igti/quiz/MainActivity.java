package com.example.igti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btTrue = (Button) findViewById(R.id.btTrue);
        Button btFalse = (Button) findViewById(R.id.btFalse);
        btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                //startActivity(new Intent(getBaseContext(),ResultsActivity.class));
            }
        });
        btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    protected void checkAnswer(boolean answer){
        //"Errou!"
        if(answer) {
            Toast.makeText(getBaseContext(), "Acertou!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "Errou!", Toast.LENGTH_SHORT).show();
        }
    }
}
