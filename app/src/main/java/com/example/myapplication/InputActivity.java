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
    private Double rad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Bundle arguments=getIntent().getExtras();
        rad = arguments.getDouble("radius");
        text = findViewById(R.id.rad);
        text.setText(rad.toString());
        sq= findViewById(R.id.square);
        len = findViewById(R.id.len);
        sq.setChecked(arguments.getBoolean("check_sq"));
        len.setChecked(arguments.getBoolean("check_len"));
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                double result_sq = 0;
                double result_len = 0;
                try {
                    data.putExtra("check_sq", sq.isChecked());
                    data.putExtra("check_len", len.isChecked());
                    if (Double.parseDouble(text.getText().toString()) <= 0){
                        Toast.makeText(getApplicationContext(), "Радиус должен быть больше 0", Toast.LENGTH_SHORT).show();
                    }
                    else if (len.isChecked() && !sq.isChecked()) {
                        result_len = 2 * Math.PI * Double.parseDouble(text.getText().toString());
                        data.putExtra("radius", text.getText().toString());
                        data.putExtra("result_len", Double.toString(result_len));
                        setResult(RESULT_OK, data);
                        finish();
                    } else if (sq.isChecked() && !len.isChecked()) {
                        result_sq = Math.PI * Double.parseDouble(text.getText().toString()) * Double.parseDouble(text.getText().toString());
                        data.putExtra("radius", text.getText().toString());
                        data.putExtra("result_sq", Double.toString(result_sq));
                        setResult(RESULT_OK, data);
                        finish();
                    } else if (sq.isChecked() && len.isChecked()){
                        result_len = 2 * Math.PI * Double.parseDouble(text.getText().toString());
                        result_sq = Math.PI * Double.parseDouble(text.getText().toString()) * Double.parseDouble(text.getText().toString());
                        data.putExtra("radius", text.getText().toString());
                        data.putExtra("result_sq", Double.toString(result_sq));
                        data.putExtra("result_len", Double.toString(result_len));
                        setResult(RESULT_OK, data);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "Error: Выберите хотя бы 1 тип результата", Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error: Введите число", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
