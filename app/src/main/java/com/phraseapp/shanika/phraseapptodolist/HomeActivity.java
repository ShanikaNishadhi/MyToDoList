package com.phraseapp.shanika.phraseapptodolist;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    ListView listView ;
    String[] taskList = {
            "Meet the Doctor",
            "Study for exam",
            "Meet Anne",
            "Watch the Movie",
            "Deposit Money",
            "Go to Grocery",
            "Have a bath"
    };
    ArrayList<String> taskTitles = new ArrayList<>();
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.task_list);
        db = new DatabaseHandler(this);

        List<Task> tasks = db.getAllTasks();
        for (Task task : tasks) {
            taskTitles.add(task.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, taskList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition     = position;
                String  itemValue    = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(HomeActivity.this, TaskDetailsActivity.class);
                startActivity(intent);
            }

        });
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
}

