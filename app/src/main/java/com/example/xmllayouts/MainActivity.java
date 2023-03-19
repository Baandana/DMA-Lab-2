package com.example.xmllayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calculate_btn;
    private RadioGroup selected_rg;
    private EditText value_et;

    private TextView resultMeasurement;

    private Switch reverse_calc;
    private RadioButton option_one, option_two, option_three, option_four;

    private boolean checked_one = false, checked_two=false, checked_three=false, checked_four =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value_et = findViewById(R.id.measurement_et);
        selected_rg=findViewById(R.id.measurement_options_rg);
        calculate_btn = findViewById(R.id.calculate_btn);
        resultMeasurement = findViewById(R.id.result_measurement_tv);

        reverse_calc = findViewById(R.id.reverse_measurement_switch);


        /*
         * Radio Button Options
         */
        option_one = findViewById(R.id.ml_fluid_ounces_option);
        option_two = findViewById(R.id.grams_to_cups_option);
        option_three = findViewById(R.id.cups_to_tbsp_option);
        option_four = findViewById(R.id.cups_to_ml_option);


        /* Created a listener for switch such that once its is changed,
         * the radio Button options are also changed accordingly.*/
        CompoundButton.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isTurnedOn) {
                if(isTurnedOn)
                {
                    option_one.setText(R.string.ounces_fluid_ml_option);
                    option_two.setText(R.string.grams_to_cups_option);
                    option_three.setText(R.string.tbsp_to_cups_option);
                    option_four.setText(R.string.ml_to_cups_option);

                }
                else
                {
                    option_one.setText(R.string.ml_fluid_ounces_option);
                    option_two.setText(R.string.grams_to_cups_option);
                    option_three.setText(R.string.cups_to_tbsp_option);
                    option_four.setText(R.string.cups_to_ml_option);
                }
            }
        };

        reverse_calc.setOnCheckedChangeListener(switchListener);

        /*
         * On click listener in button.
         * */
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioChecked = selected_rg.getCheckedRadioButtonId();
                if(reverse_calc.isChecked())
                {
                    try {
                        switch (radioChecked) {
                            case R.id.cups_to_ml_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())*29.57));
                                break;
                            case R.id.cups_to_tbsp_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())*16));
                                break;
                            case R.id.grams_to_cups_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())*250));
                                break;
                            case R.id.ml_fluid_ounces_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())*0.033814));
                                break;
                        }
                    }catch(Exception e)
                    {
                        Toast.makeText(MainActivity.this, "Please enter measurement.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    try {
                        switch (radioChecked) {
                            case R.id.cups_to_ml_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())/29.57));
                                break;
                            case R.id.cups_to_tbsp_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())/16));
                                break;
                            case R.id.grams_to_cups_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())/250));
                                break;
                            case R.id.ml_fluid_ounces_option:
                                resultMeasurement.setText(String.valueOf(Double.parseDouble(value_et.getText().toString())/0.033814));
                                break;
                        }
                    }catch(Exception e)
                    {
                        Toast.makeText(MainActivity.this, "Please enter measurement.", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}