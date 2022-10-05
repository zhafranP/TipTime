package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

    void calculateTip() {
            String stringInTextField = binding.costOfServiceEditText.getText().toString();

            double cost;

            try {
                cost = Double.parseDouble(stringInTextField);
            }catch (NumberFormatException e){
                displayTip(0.0);
                return;
            }

            double tipPercentage = 0.20 ;

            switch (binding.tipOptions.getCheckedRadioButtonId()){
                case R.id.option_twenty_percent :
                    tipPercentage = 0.20;
                    break;
                case R.id.option_eighteen_percent :
                    tipPercentage = 0.18;
                    break;
                case R.id.option_fifteen_percent :
                    tipPercentage = 0.15;
                    break;
            }

            double tip = tipPercentage * cost;

            if(binding.roundUpSwitch.isChecked()){
                tip = Math.ceil(tip);
            }

            Toast toast= Toast.makeText(getApplicationContext(),Double.toString(tip),Toast.LENGTH_SHORT);
            toast.show();

            displayTip(tip);
        }

    void displayTip(double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(getResources().getString(R.string.tip_amount, formattedTip));
    }

}