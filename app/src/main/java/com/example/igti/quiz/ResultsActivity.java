package com.example.igti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        NumberFormat pctf = new DecimalFormat("##0%", new DecimalFormatSymbols(new Locale("pt", "BR")));


        Intent i = getIntent();
        double percent = i.getDoubleExtra("percent",0d);

        TextView txResult = (TextView) findViewById(R.id.txResult);
        txResult.setText(pctf.format(percent) + " de acertos");

        Button btRestart = (Button) findViewById(R.id.btRestart);
        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
