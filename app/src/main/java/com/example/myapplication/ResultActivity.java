package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView res_s, res_l, len, sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        res_s = findViewById(R.id.text_square);
        res_l = findViewById(R.id.text_len);
        sq = findViewById(R.id.sq);
        len = findViewById(R.id.len);
        Bundle arguments=getIntent().getExtras();
        System.out.println(arguments);
        res_s.setText("");
        sq.setText("");
        res_l.setText("");
        len.setText("");
        if(arguments!=null)
        {
            if (arguments.containsKey("result_sq")) {
                res_s.setText(arguments.getString("result_sq").toString());
                sq.setText("Square:");
            }
            if (arguments.containsKey("result_len")) {
                res_l.setText(arguments.getString("result_len").toString());
                len.setText("Lenght:");
            }
        }
    }
}
