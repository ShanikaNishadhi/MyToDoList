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

        String taskTitle = getIntent().getStringExtra("taskTitle");
        DatabaseHandler db = new DatabaseHandler(this);
        Task task = db.getTask(taskTitle);

        et_title.setText(task.getTitle());
        et_date.setText(task.getDate());
        et_time.setText(task.getTime());
        et_desc.setText(task.getDescription());
    }
}
