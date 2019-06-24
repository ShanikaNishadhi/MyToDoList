package com.phraseapp.shanika.phraseapptodolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime, inTask, inDesc;
    private int mYear, mMonth, mDay, mHour, mMinute;
    DateFormat formatDate, formatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        inTask   = (EditText)findViewById(R.id.in_task);
        inDesc   = (EditText)findViewById(R.id.in_description);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            formatDate =  DateFormat.getDateInstance();
                            c.set(year, monthOfYear, dayOfMonth);
                            txtDate.setText(formatDate.format(c.getTime()));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if (v == btnTimePicker) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            formatTime =  DateFormat.getTimeInstance();
                            c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                            c.set(Calendar.MINUTE,minute);
                            txtTime.setText(formatTime.format(c.getTime()));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void addTask(View view)
    {
        String title, desc, date, time;
        DatabaseHandler db = new DatabaseHandler(this);
        title = inTask.getText().toString();
        desc = inDesc.getText().toString();

        Calendar c = Calendar.getInstance();
        c.set(mYear, mMonth, mDay);
        date = formatDate.format(c.getTime());

        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,mHour);
        c.set(Calendar.MINUTE,mMinute);
        c.set(mYear, mMonth, mDay);
        time = formatTime.format(c.getTime());
//        date = mYear + ""+ mMonth +""+mDay;
//        time = mHour +""+mMinute;
        db.addTask(new Task(title, desc, date,time));

        Intent intent = new Intent(this, HomeActivity.class);
        new HomeActivity();
        startActivity(intent);
    }

}
