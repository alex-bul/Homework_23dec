package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button exit, calc, input;
    public Boolean check_sq, check_len;
    private Double result_sq, result_len, radius;
    private static final int REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.exit);
        calc = findViewById(R.id.calc);
        input = findViewById(R.id.input);
        check_len = false;
        check_sq = false;
        result_len = 0.0;
        result_sq = 0.0;
        radius = 0.0;
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                System.out.println(check_len);
                System.out.println(check_sq);
                intent.putExtra("radius", radius);
                intent.putExtra("check_sq", check_sq);
                intent.putExtra("check_len", check_len);
                startActivityForResult(intent, REQUEST);
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                if (result_sq != 0){ intent.putExtra("result_sq", result_sq.toString()); }
                if (result_len != 0){ intent.putExtra("result_len", result_len.toString()); }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST)
        {
            if(resultCode==RESULT_OK && data != null)
            {
                result_len = 0.0;
                result_sq = 0.0;
                if (data.hasExtra("result_len" )){
                        result_len = Double.parseDouble(data.getStringExtra("result_len"));
                }
                if (data.hasExtra("result_sq" )){
                    result_sq = Double.parseDouble(data.getStringExtra("result_sq"));
                }
                check_sq = data.getBooleanExtra("check_sq", false);
                check_len = data.getBooleanExtra("check_len", false);
                radius = Double.parseDouble(data.getStringExtra("radius"));
            }
            else Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
