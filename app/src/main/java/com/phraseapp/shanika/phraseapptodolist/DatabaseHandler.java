package com.phraseapp.shanika.phraseapptodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "toDoList";
    private static final String TABLE_TASK = "task";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "create table task (id integer primary key autoincrement, " +
                "title text,description text,date text,time text);";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);

        // Create tables again
        onCreate(db);
    }

    void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESC, task.getDescription());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_TIME, task.getTime());

        db.insert(TABLE_TASK, null, values);
        db.close();
    }

    Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASK, new String[] { KEY_ID,
                        KEY_TITLE, KEY_DESC, KEY_DATE, KEY_TIME  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
        // return contact
        return task;
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setDate(cursor.getString(3));
                task.setTime(cursor.getString(4));
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        return taskList;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESC, task.getDescription());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_TIME, task.getTime());

        return db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
        db.close();
    }

    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM task";
        db.execSQL(clearDBQuery);
    }

}
