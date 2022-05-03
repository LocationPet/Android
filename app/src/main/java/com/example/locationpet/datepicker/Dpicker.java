package com.example.locationpet.datepicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.locationpet.R;
import com.example.locationpet.RegisterActivity;

public class Dpicker extends AppCompatActivity {
    DatePicker datePicker;
    Button pickerbtn;
    public static String yy,mm,dd;
    public static boolean check = false;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.picker);

        pickerbtn = (Button) findViewById(R.id.pickerBtn);
        datePicker = (DatePicker) findViewById(R.id.datepicker);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int monthOfYear,
                                              int dayOfMonth) {
                        yy=Integer.toString(year);
                        mm=Integer.toString(monthOfYear);
                        dd=Integer.toString(dayOfMonth);
                        check = true;
                    }
                });
        pickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("yy", yy);
                intent.putExtra("mm", mm);
                intent.putExtra("dd", dd);
                startActivityForResult(intent, 1000);

            }
        });
    }
}
