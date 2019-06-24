package com.phraseapp.shanika.phraseapptodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    EditText et_title, et_desc, et_date, et_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        et_title = (EditText) findViewById(R.id.et_title);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_date = (EditText) findViewById(R.id.et_date);
        et_time = (EditText) findViewById(R.id.et_time);

        et_title.setText("Meet the doctor");
        et_date.setText("23 Jun 2019");
        et_time.setText("05:13:00");
        et_desc.setText("I have to meet the doctor to ask about my reports. My number is 23. ");
    }
}
