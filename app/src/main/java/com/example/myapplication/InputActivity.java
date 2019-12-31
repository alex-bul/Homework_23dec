package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    private Button save;
    private CheckBox len, sq;
    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        text = findViewById(R.id.rad);
        sq= findViewById(R.id.square);
        len = findViewById(R.id.len);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                double result_sq = 0;
                double result_len = 0;
                try {
                    if (len.isChecked() && !sq.isChecked()) {
                        result_len = 2 * Math.PI * Double.parseDouble(text.getText().toString());
                        data.putExtra("result_len", Double.toString(result_len));
                        setResult(RESULT_OK, data);
                        finish();
                    } else if (sq.isChecked() && !len.isChecked()) {
                        result_sq = Math.PI * Double.parseDouble(text.getText().toString()) * Double.parseDouble(text.getText().toString());
                        data.putExtra("result_sq", Double.toString(result_sq));
                        setResult(RESULT_OK, data);
                        finish();
                    } else if (sq.isChecked() && len.isChecked()){
                        result_len = 2 * Math.PI * Double.parseDouble(text.getText().toString());
                        result_sq = Math.PI * Double.parseDouble(text.getText().toString()) * Double.parseDouble(text.getText().toString());
                        data.putExtra("result_sq", Double.toString(result_sq));
                        data.putExtra("result_len", Double.toString(result_len));
                        setResult(RESULT_OK, data);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "Error: Выберите хотя бы 1 тип результата", Toast.LENGTH_LONG).show();
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error: Введите число", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
