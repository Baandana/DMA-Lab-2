package com.example.xmllayouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText cost_of_service;
    private TextView tip_amount;
    private RadioGroup radio_group;
    private RadioButton rb_okay;
    private Button btn_calculate;
    private SwitchCompat round_up_tip;

    double cost = 0;
    private double tip_percentage = 0;
    private double tip_amount_in_double = 0;
    private int tip_amount_in_int = 0;

    private boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cost_of_service = findViewById(R.id.input_cos);
        tip_amount = findViewById(R.id.tv_tip_amount);
        btn_calculate = findViewById(R.id.btn_calculate);
        rb_okay = findViewById(R.id.rb_okay);
        round_up_tip = (SwitchCompat) findViewById(R.id.round_up_tip);
        radio_group = findViewById(R.id.radioGroup);

        // set event listener for button click
        btn_calculate.setOnClickListener(view -> {
            if(cost_of_service.getText().toString().equals("")) {
                cost_of_service.setError("Field cannot be empty!");
                return;
            }

            int checkedId = radio_group.getCheckedRadioButtonId();
            if(checkedId < 0) {
                rb_okay.setError("Check one option!");
                return;
            }

            try {
                cost = Double.parseDouble(cost_of_service.getText().toString());
            }
            catch(Exception ex) {
                cost_of_service.setError("Invalid input format!");
                return;
            }

            switch(checkedId) {
                case R.id.rb_amazing:
                    tip_percentage = 0.20;
                    break;
                case R.id.rb_good:
                    tip_percentage = 0.18;
                    break;
                case R.id.rb_okay:
                    tip_percentage = 0.15;
                    break;
            }

            round_up_tip.setOnCheckedChangeListener((compoundButton, b) -> {
                isChecked = b ? true : false;
            });

            // calculate tip amount and display in TextView
            if(isChecked) {
                tip_amount_in_int = (int) Math.round(cost * tip_percentage);
                tip_amount.setText(String.valueOf(tip_amount_in_int));
            }
            else {
                tip_amount_in_double = cost * tip_percentage;
                tip_amount.setText(String.valueOf(tip_amount_in_double));
            }
        });

        // set event listener for radio button selection
        radio_group.setOnCheckedChangeListener((radioGroup, i) -> {
            switch(i) {

                // show Toast message for selected radio button

                case R.id.rb_amazing:
                    Toast.makeText(MainActivity.this, "Amazing clicked!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_good:
                    Toast.makeText(MainActivity.this, "Good clicked!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_okay:
                    Toast.makeText(MainActivity.this, "Okay clicked!", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

}